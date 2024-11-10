# CSI-2300-Course-Project
1. Project Name: "What To Do", Team Name: "Michael", Team Member(s): Michael Awwad

2. A To-Do List Application with additional features for task reminders and priority levels. This application enables users to add tasks, set due dates, assign priority levels, and include optional reminder messages.This project serves as a productivity tool, aiming to help users stay organized and manage their tasks effectively. By allowing users to assign priorities and set reminders, the application goes beyond a standard to-do list, enabling them to track which tasks need urgent attention and which are approaching their due dates.

How it will be used (Adding Tasks: Users enter details such as the task description, due date, priority level, and an optional reminder message. Completing and Viewing Tasks: The user can mark tasks as completed and view all tasks in a list, sorted by due date or priority. Updating and Managing Tasks: A simple interface allows users to refresh the task list, see what is due soon, and keep track of their priorities.)

3. classDiagram
    class Task {
        - String description
        - Date dueDate
        - boolean isCompleted
        + Task(String description, Date dueDate)
        + completeTask()
        + toString() String
    }

    class PriorityTask {
        - int priorityLevel
        + PriorityTask(String description, Date dueDate, int priorityLevel)
        + toString() String
    }

    class ReminderTask {
        - String reminderMessage
        + ReminderTask(String description, Date dueDate, String reminderMessage)
        + toString() String
    }

    class TaskManager {
        - ArrayList~Task~ tasks
        + TaskManager()
        + addTask(Task task)
        + completeTask(int index)
        + listTasks() String
        + getTasks() ArrayList~Task~
    }

    class ToDoListApp {
        - TaskManager taskManager
        - JTextArea taskDisplay
        - JTextField descriptionField
        - JTextField dueDateField
        - JTextField priorityField
        - JTextField reminderField
        + ToDoListApp()
        + refreshTaskDisplay()
    }

    Task <|-- PriorityTask
    Task <|-- ReminderTask
    TaskManager "1" *-- "many" Task
    ToDoListApp "1" *-- "1" TaskManager

4. Estimated time and effort: Between the UML diagram design, core class implementations, taskManager class, GUI Design, integrating GUI, testing and debugging, and final clean up id say the project would take 20-30 hours to create
