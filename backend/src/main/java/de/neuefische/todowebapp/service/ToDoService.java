package de.neuefische.todowebapp.service;

import de.neuefische.todowebapp.db.ToDoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private ToDoDb toDoDb;

    @Autowired
    public ToDoService(ToDoDb toDoDb) {
        this.toDoDb = toDoDb;
    }
}
