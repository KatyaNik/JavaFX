package model;

public class TaskFabrica {
    public static final String DB = "база данных";
    public static final String FILE = "файл";
    public static final String RAM = "временно";

    public static TaskDAO createTaskDAO(String type) {
        System.out.println("Переданный тип источника данных: " + type);
        if (type.equalsIgnoreCase(DB)) {
            return new PostgresTaskDAO();
        } else if (type.equalsIgnoreCase(FILE)) {
            return new FileTaskDAO("src/data/tasks.txt");
        } else if (type.equalsIgnoreCase(RAM)) {
            return new ListTaskDAO(10);
        } else {
            throw new IllegalArgumentException("Неверный тип источника данных!");
        }
    }
}