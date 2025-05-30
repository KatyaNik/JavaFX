package model;

public class TaskFabrica {
    public static String BD = "база данных";
    public static String FILE = "файл";
    public static String RAM = "временно";

    public static TaskDAO createTaskDAO(String type) {
        System.out.println("Переданный тип источника данных: " + type);
        if (type.equalsIgnoreCase(BD)) {
            throw new UnsupportedOperationException("База данных пока не поддерживается");
        } else if (type.equalsIgnoreCase(FILE)) {
            return new FileTaskDAO("src/data/tasks.txt");
        } else if (type.equalsIgnoreCase(RAM)) {
            return new ListTaskDAO(10);
        } else {
            throw new IllegalArgumentException("Неверный тип источника данных!");
        }
    }
}