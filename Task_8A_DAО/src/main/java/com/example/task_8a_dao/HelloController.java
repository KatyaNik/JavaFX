package com.example.task_8a_dao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelloController {

    @FXML
    private TableView<Task> fxtable;

    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField messageTextField;

    private ObservableList<Task> fxlist = FXCollections.observableArrayList();
    private TaskDAO taskDAO; // Интерфейс DAO

    private Task selectedTask = null; // Выбранная задача для редактирования

    private int id = 0;
    @FXML
    public void DBButtonOnAction() {
        // Получаем текущие задачи из таблицы
        List<Task> currentTasks = fxtable.getItems();

        // Создаем DAO для работы с БД
        PostgresTaskDAO dbDao = new PostgresTaskDAO();

        // Сохраняем все задачи в БД (с полной перезаписью)
        dbDao.saveAllTasks(currentTasks);

        // Загружаем задачи из БД для отображения
        taskDAO = dbDao;
        loadTasksFromDAO();
    }
    @FXML
    public void initialize() {
        // Инициализация DAO с хранением в памяти по умолчанию
        taskDAO = TaskFabrica.createTaskDAO(TaskFabrica.RAM);

        // Инициализация колонок таблицы
        TableColumn<Task, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Task, String> loginColumn = new TableColumn<>("Логин");
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());

        TableColumn<Task, String> passwordColumn = new TableColumn<>("Пароль");
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());

        TableColumn<Task, String> timeColumn = new TableColumn<>("Время");
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        TableColumn<Task, String> messageColumn = new TableColumn<>("Сообщение");
        messageColumn.setCellValueFactory(cellData -> cellData.getValue().messageProperty());

        fxtable.getColumns().addAll(idColumn, loginColumn, passwordColumn, timeColumn, messageColumn);
        fxtable.setItems(fxlist);

        // Загружаем начальные данные
        loadTasksFromDAO();
    }
    @FXML
    public void deleteButtonOnAction() {
        Task selectedTask = fxtable.getSelectionModel().getSelectedItem();
        taskDAO.deleteTask(selectedTask.getId());
        loadTasksFromDAO();
        clearFields();
    }
    private int getMaxId() {
        List<Task> tasks = taskDAO.getAllTasks();
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(-1); // Если список пуст, вернуть -1
    }
    @FXML
    public void saveButtonOnAction() {
        String newLogin = loginTextField.getText();
        String newPassword = passwordTextField.getText();
        String newMessage = messageTextField.getText();
        selectedTask.setLogin(newLogin);
        selectedTask.setPassword(newPassword);
        selectedTask.setMessage(newMessage);
        taskDAO.updateTask(selectedTask);
        loadTasksFromDAO();
        clearFields();
        selectedTask = null;
    }
    @FXML
    public void editButtonOnAction(){
        selectedTask = fxtable.getSelectionModel().getSelectedItem();
        loginTextField.setText(selectedTask.getLogin());
        passwordTextField.setText(selectedTask.getPassword());
        messageTextField.setText(selectedTask.getMessage());
    }
    private void clearFields() {
        loginTextField.clear();
        passwordTextField.clear();
        messageTextField.clear();
    }
    @FXML
    public void TXTButtonOnAction() {
        // Получаем текущие задачи из таблицы
        List<Task> currentTasks = fxtable.getItems();

        // Создаем DAO для работы с файлом
        FileTaskDAO fileDao = new FileTaskDAO("src/data/tasks.txt");

        // Сохраняем все задачи в файл (с полной перезаписью)
        fileDao.saveAllTasks(currentTasks);

        // Загружаем задачи из файла для отображения
        taskDAO = fileDao;
        loadTasksFromDAO();
    }

    private void loadTasksFromDAO() {
        List<Task> tasks = taskDAO.getAllTasks();
        fxtable.getItems().clear(); 
        fxtable.setItems(FXCollections.observableArrayList(tasks));
    }
    public void RAMButtonOnAction(){
        taskDAO = TaskFabrica.createTaskDAO(TaskFabrica.RAM);
        loadTasksFromDAO();
    }
    @FXML
    public void addButtonOnAction() {
        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        String message = messageTextField.getText();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        int newId = taskDAO.getAllTasks().size();

        newId++;
        Task newTask = new Task(newId, login, password, formattedTime, message);
        taskDAO.addTask(newTask);
        loadTasksFromDAO();
        clearFields();
    }
}