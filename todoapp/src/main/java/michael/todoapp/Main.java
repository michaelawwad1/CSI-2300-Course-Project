package michael.todoapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class Main extends Application {

    private final TaskManager taskManager = new TaskManager();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Initialize the main task list scene
        Scene mainScene = createMainTaskListScene();

        // Configure the stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("To-Do List App");
        primaryStage.show();
    }

    private Scene createMainTaskListScene() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Create a ListView for pending tasks
        VBox pendingTasksContainer = new VBox(10);

        // Buttons at the top of the page
        HBox buttonsBox = new HBox(10);
        buttonsBox.setPadding(new Insets(0, 0, 10, 0));
        buttonsBox.setSpacing(10); // Add spacing between buttons

        // "Add Task" button
        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(e -> showAddTaskDialog(pendingTasksContainer));

        // "View Completed Tasks" button
        Button completedTasksButton = new Button("View Completed Tasks");
        completedTasksButton.setOnAction(e -> switchToCompletedTasksScene());

        // Align buttons side by side
        buttonsBox.getChildren().addAll(addTaskButton, completedTasksButton);

        // Layout
        root.getChildren().addAll(buttonsBox, new Label("Pending Tasks:"), pendingTasksContainer);

        // Initialize the pending tasks list
        updatePendingTasksListView(pendingTasksContainer);

        return new Scene(root, 400, 600);
    }

    private Scene createCompletedTasksScene() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Create a ListView for completed tasks
        VBox completedTasksContainer = new VBox(10);

        // Button to return to the main task list at the top
        Button backButton = new Button("Back to Main List");
        backButton.setOnAction(e -> primaryStage.setScene(createMainTaskListScene()));

        // Button to clear all tasks at the bottom
        Button clearAllButton = new Button("Clear All Tasks");
        clearAllButton.setOnAction(e -> {
            taskManager.clearCompletedTasks();
            updateCompletedTasksListView(completedTasksContainer); // Refresh the completed tasks list
        });

        // Layout: Stack the buttons and tasks container
        root.getChildren().addAll(backButton, new Label("Completed Tasks:"), completedTasksContainer, clearAllButton);

        // Initialize the completed tasks list
        updateCompletedTasksListView(completedTasksContainer);

        return new Scene(root, 400, 600);
    }

    private void updatePendingTasksListView(VBox container) {
        container.getChildren().clear();

        // Group tasks by date, and now sort by date with the earliest date first
        taskManager.getPendingTasks().stream()
                .collect(Collectors.groupingBy(Task::getDate))
                .entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey())) // Sort dates from earliest to
                                                                                        // latest
                .forEach(entry -> {
                    LocalDate date = entry.getKey();
                    container.getChildren().add(new Label(date.toString()));

                    // Add tasks under the date, ordered by priority (highest to lowest)
                    entry.getValue().stream()
                            .sorted((a, b) -> Integer.compare(b.getPriority(), a.getPriority())) // Sort by priority:
                                                                                                 // highest first
                            .forEach(task -> {
                                CheckBox checkBox = new CheckBox();
                                checkBox.setSelected(task.isCompleted());
                                Label taskLabel = new Label(task.toString());

                                HBox taskItem = new HBox(10, checkBox, taskLabel);
                                container.getChildren().add(taskItem);

                                checkBox.setOnAction(e -> {
                                    if (checkBox.isSelected()) {
                                        taskManager.completeTask(task);
                                        updatePendingTasksListView(container); // Refresh list
                                    }
                                });
                            });
                });
    }

    private void updateCompletedTasksListView(VBox container) {
        container.getChildren().clear();

        for (Task task : taskManager.getCompletedTasks()) {
            Label taskLabel = new Label(task.toString());
            Button restoreButton = new Button("Restore");
            restoreButton.setOnAction(e -> {
                taskManager.restoreTask(task);
                updateCompletedTasksListView(container); // Refresh the completed tasks list
            });

            HBox taskItem = new HBox(10, restoreButton, taskLabel);
            container.getChildren().add(taskItem);
        }
    }

    private void showAddTaskDialog(VBox pendingTasksContainer) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);

        VBox dialogLayout = new VBox(15); // Increase spacing
        dialogLayout.setPadding(new Insets(10));

        TextField taskNameField = new TextField();
        taskNameField.setPromptText("Task Name");

        DatePicker datePicker = new DatePicker();

        Spinner<Integer> prioritySpinner = new Spinner<>(1, 5, 1);

        // Button to confirm task addition
        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String taskName = taskNameField.getText();
            LocalDate date = datePicker.getValue();
            Integer priority = prioritySpinner.getValue();

            // Ensure all fields are properly filled before adding the task
            if (taskName.isEmpty() || date == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("All fields are required!");
                alert.setContentText("Please enter a task name, select a date, and set a priority.");
                alert.showAndWait();
                return;
            }

            // Add the new task
            Task newTask = new Task(taskName, date, priority);
            taskManager.addTask(newTask);

            // Update the task list and close the dialog
            updatePendingTasksListView(pendingTasksContainer);
            dialog.close();
        });

        // Add all components to the dialog layout
        dialogLayout.getChildren().addAll(
                new Label("Task Name:"), taskNameField,
                new Label("Date:"), datePicker,
                new Label("Priority:"), prioritySpinner,
                addButton // Include the add button
        );

        Scene dialogScene = new Scene(dialogLayout, 300, 300); // Dialog size adjusted to 300x300
        dialog.setScene(dialogScene);
        dialog.setTitle("Add Task");
        dialog.showAndWait();
    }

    private void switchToCompletedTasksScene() {
        Scene completedTasksScene = createCompletedTasksScene();
        primaryStage.setScene(completedTasksScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
