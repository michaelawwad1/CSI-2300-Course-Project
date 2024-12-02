package michael.todoapp;

import java.time.LocalDate;

public class Task {

    private final String name;
    private final LocalDate date;
    private final int priority;
    private boolean completed;

    public Task(String name, LocalDate date, int priority) {
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}
