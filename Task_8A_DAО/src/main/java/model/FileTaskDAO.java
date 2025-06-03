package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTaskDAO implements TaskDAO {
    private String fileName;

    public FileTaskDAO(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String login = parts[1];
                    String password = parts[2];
                    String time = parts[3];
                    String message = parts[4];
                    tasks.add(new Task(id, login, password, time, message));
                } else {
                    System.out.println("Ошибка формата данных в файле: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из файла: " + e.getMessage());
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        List<Task> tasks = getAllTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        // Проверяем уникальность ID
        if (getTaskById(task.getId()) != null) {
            System.out.println("Ошибка: Задача с ID " + task.getId() + " уже существует.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(task.getId() + "," + task.getLogin() + "," + task.getPassword() + "," +
                    task.getTime() + "," + task.getMessage());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    @Override
    public void updateTask(Task updatedTask) {
        List<Task> tasks = getAllTasks();
        boolean isUpdated = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.set(i, updatedTask);
                isUpdated = true;
                break;
            }
        }

        if (isUpdated) {
            saveTasksToFile(tasks);
        } else {
            System.out.println("Ошибка: Задача с ID " + updatedTask.getId() + " не найдена.");
        }
    }

    @Override
    public void deleteTask(int id) {
        List<Task> tasks = getAllTasks();
        boolean isDeleted = tasks.removeIf(task -> task.getId() == id);

        if (isDeleted) {
            saveTasksToFile(tasks);
        } else {
            System.out.println("Ошибка: Задача с ID " + id + " не найдена.");
        }
    }

    @Override
    public void saveAllTasks(List<Task> tasks) {
        saveTasksToFile(tasks);
    }

    private void saveTasksToFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                writer.write(task.getId() + "," + task.getLogin() + "," + task.getPassword() + "," +
                        task.getTime() + "," + task.getMessage());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}