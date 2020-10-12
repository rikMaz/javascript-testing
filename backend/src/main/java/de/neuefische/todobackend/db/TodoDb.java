package de.neuefische.todobackend.db;

import de.neuefische.todobackend.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<TodoItem> list() {
        return Collections.unmodifiableList(items);
    }

    public void clear() {
        items.clear();
    }

    public void deleteItem(String id) {
        Optional<TodoItem> todoItem = getTodoItem(id);
        todoItem.ifPresent(items::remove);
    }

    public void updateItem(TodoItem item) {
        deleteItem(item.getId());
        addItem(item);
    }
}
