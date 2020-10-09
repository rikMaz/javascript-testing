package de.neuefische.todowebapp.db;

import de.neuefische.todowebapp.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoDb {

    private List<ToDo> toDoList;

    public ToDoDb() {
        this.toDoList = new ArrayList<ToDo>();
    }
}
