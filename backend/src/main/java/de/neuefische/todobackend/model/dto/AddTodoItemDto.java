package de.neuefische.todobackend.model.dto;

import de.neuefische.todobackend.model.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTodoItemDto {
    private String description;
    private TodoStatus status;
}
