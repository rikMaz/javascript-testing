package de.neuefische.todowebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class ToDo {

    private final String id;
    private final ToDoStatus toDoStatus;
    private final String description;
}
