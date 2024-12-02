package michael.todoapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final List<Task> pendingTasks = new ArrayList<>();
    private final List<Task> completedTasks = new ArrayList<>();

    public void addTask(Task task) {
        pendingTasks.add(task);
    }

    public void completeTask(Task task) {
        pendingTasks.remove(task);
        completedTasks.add(task);
    }

    public List<Task> getPendingTasks() {
        return pendingTasks;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void restoreTask(Task task) {
        completedTasks.remove(task);
        pendingTasks.add(task);
    }

    public void deleteTask(Task task) {
        completedTasks.remove(task);
    }

    public void clearCompletedTasks() {
        completedTasks.clear();
    }
}
