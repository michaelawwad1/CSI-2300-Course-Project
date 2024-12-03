# CSI-2300-Course-Project
1. Project Name: "What To Do", Team Name: "Michael", Team Member(s): Michael Awwad

2. A To-Do List Application with additional features for task reminders and priority levels. This application enables users to add tasks, set due dates, assign priority levels, and include optional reminder messages.This project serves as a productivity tool, aiming to help users stay organized and manage their tasks effectively. By allowing users to assign priorities and set reminders, the application goes beyond a standard to-do list, enabling them to track which tasks need urgent attention and which are approaching their due dates.

How it will be used (Adding Tasks: Users enter details such as the task description, due date, priority level, and an optional reminder message. Completing and Viewing Tasks: The user can mark tasks as completed and view all tasks in a list, sorted by due date or priority. Updating and Managing Tasks: A simple interface allows users to refresh the task list, see what is due soon, and keep track of their priorities.)

3. classDiagram
    title To-Do App

    class Task {
        +String name
        +LocalDate date
        +int priority
        +boolean completed
        +Task(String name, LocalDate date, int priority)
        +String getName()
        +LocalDate getDate()
        +int getPriority()
        +boolean isCompleted()
        +void setCompleted(boolean completed)
        +String toString()
    }

    class TaskManager {
        -List<Task> pendingTasks
        -List<Task> completedTasks
        +void addTask(Task task)
        +void completeTask(Task task)
        +List<Task> getPendingTasks()
        +List<Task> getCompletedTasks()
        +void deleteTask(Task task)
    }

    class Main {
        -TaskManager taskManager
        -Stage primaryStage
        +void start(Stage primaryStage)
        +Scene createMainTaskListScene()
        +Scene createCompletedTasksScene()
        +void updatePendingTasksListView(VBox container)
        +void updateCompletedTasksListView(VBox container)
        +void showAddTaskDialog(VBox pendingTasksContainer)
        +void switchToCompletedTasksScene()
        +void main(String[] args)
    }

    TaskManager "1" o-- "*" Task : manages
    Main "1" o-- "1" TaskManager : uses


4. Estimated time and effort: Between the UML diagram design, core class implementations, taskManager class, GUI Design, integrating GUI, testing and debugging, and final clean up id say the project would take 20-30 hours to create

5. What's this project about: This project is a JavaFX-based To-Do List application designed to help users efficiently manage their daily tasks and responsibilities. The app was developed with a college student in mind, someone balancing multiple areas of life, including academics, personal tasks, work obligations, and social activities. The application allows users to add tasks with details such as a name, due date, and priority level, organize tasks into groups by date, and track their completion status. Completed tasks are moved to a separate section, where they can be reviewed, restored, or cleared entirely. With a user-friendly interface and robust functionality, this application demonstrates the power of object-oriented programming and serves as a practical solution for anyone needing better task organization.

6. How to run application: To run the program, ensure that Java (version 8 or higher) and Maven are installed on your computer. Open the folder containing the project files in an IDE that supports JavaFX, such as IntelliJ IDEA, Eclipse, or VS Code. If JavaFX is not already set up, download the JavaFX SDK from the official website and configure your IDE to include the JavaFX library, although this is typically handled automatically if the project uses Maven with the correct dependencies in the pom.xml file. Once everything is set up, open a terminal in the project's root directory and execute the command mvn javafx:run to compile and launch the application. This will start the To-Do List app, where you can interact with its features, including adding tasks, marking them as complete, managing priorities, and navigating between pending and completed tasks.
