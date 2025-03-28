package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListTaskDAO implements TaskDAO {
    private List<Task> tasks;

    public ListTaskDAO(int size) {
        tasks = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            Task task = new Task(
                    i + 1,
                    "Login " + (i + 1),
                    "Password " + (i + 1),
                    randomTime(random),
                    "Context for task " + (i + 1)
            );
            tasks.add(task);
        }
    }

    private String randomTime(Random random) {
        int hours = random.nextInt(24);
        int minutes = random.nextInt(60);
        int seconds = random.nextInt(60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        // Генерируем уникальный ID
        int maxId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0); // Если список пуст, начнем с 0
        task.setId(maxId + 1);

        tasks.add(task);
    }

    @Override
    public void updateTask(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.set(i, updatedTask);
                return;
            }
        }
        System.out.println("Ошибка: Задача с ID " + updatedTask.getId() + " не найдена.");
    }

    @Override
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
}