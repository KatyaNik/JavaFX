package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresTaskDAO implements TaskDAO {
    private static final String URL = "jdbc:postgresql://localhost:5434/Task8DAO";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public PostgresTaskDAO() {
        initDatabase();
    }

    private void initDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
                id SERIAL PRIMARY KEY,
                login VARCHAR(50) NOT NULL,
                password VARCHAR(255) NOT NULL,
                time VARCHAR(20) NOT NULL,
                message TEXT
            )
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Ошибка при инициализации БД:");
            e.printStackTrace();
        }
    }
    // Новый метод для полной перезаписи таблицы
    public void saveAllTasks(List<Task> tasks) {
        String clearTableSql = "TRUNCATE TABLE tasks RESTART IDENTITY";
        String insertSql = "INSERT INTO tasks (login, password, time, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Очищаем таблицу
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(clearTableSql);
            }

            // Вставляем новые данные
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                for (Task task : tasks) {
                    pstmt.setString(1, task.getLogin());
                    pstmt.setString(2, task.getPassword());
                    pstmt.setString(3, task.getTime());
                    pstmt.setString(4, task.getMessage());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении задач в БД:");
            e.printStackTrace();
        }
    }
    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("time"),
                        rs.getString("message")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении задач:");
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Task(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("time"),
                            rs.getString("message")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении задачи по ID:");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (login, password, time, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, task.getLogin());
            pstmt.setString(2, task.getPassword());
            pstmt.setString(3, task.getTime());
            pstmt.setString(4, task.getMessage());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        task.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении задачи:");
            e.printStackTrace();
        }
    }

    @Override
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET login = ?, password = ?, time = ?, message = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getLogin());
            pstmt.setString(2, task.getPassword());
            pstmt.setString(3, task.getTime());
            pstmt.setString(4, task.getMessage());
            pstmt.setInt(5, task.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении задачи:");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении задачи:");
            e.printStackTrace();
        }
    }
}