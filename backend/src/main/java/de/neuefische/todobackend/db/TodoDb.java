package de.neuefische.todobackend.db;

import de.neuefische.todobackend.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoDb {

    private final List<TodoItem> items = new ArrayList<>();

    public Optional<TodoItem> getTodoItem(String id){
        return items.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    public void addItem(TodoItem item) {
        items.add(item);
    }
}
