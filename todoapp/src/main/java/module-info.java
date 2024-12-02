module michael.todoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens michael.todoapp to javafx.fxml;

    exports michael.todoapp;
}
