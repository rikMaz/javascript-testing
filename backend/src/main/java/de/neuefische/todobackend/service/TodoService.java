package de.neuefische.todobackend.service;

import de.neuefische.todobackend.db.TodoDb;
import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.utils.IdUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final IdUtils idUtils;
    private final TodoDb db;

    public TodoService(IdUtils idUtils, TodoDb db) {
        this.idUtils = idUtils;
        this.db = db;
    }

    public TodoItem addTodoItem(AddTodoItemDto dto){
        TodoItem todoItem = new TodoItem(idUtils.generateId(), dto.getDescription(), dto.getStatus());
        db.addItem(todoItem);
        return todoItem;
    }

    public List<TodoItem> listAllTodos() {
        return db.list();
    }
}
