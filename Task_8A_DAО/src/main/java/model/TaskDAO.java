package model;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(int id);

    // Новый метод для сохранения всех задач (опционально)
    default void saveAllTasks(List<Task> tasks) {
        // Реализация по умолчанию - последовательное добавление
        tasks.forEach(this::addTask);
    }
}