package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todo")
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public TodoItem addTodoItem(@RequestBody AddTodoItemDto dto){
        return this.service.addTodoItem(dto);
    }

    @GetMapping
    public List<TodoItem> listAllTodos(){
        return service.listAllTodos();
    }

    @DeleteMapping("{id}")
    public void deleteTodoItem(@PathVariable String id){
        service.deleteItem(id);
    }

    @PutMapping("{id}")
    public TodoItem updateItem(@PathVariable String id,@RequestBody TodoItem item ){
        if(!id.equals(item.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ids not matching");
        }

        Optional<TodoItem> todoItem = service.updateItem(item);
        if(todoItem.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item not found");
        }
        return todoItem.get();
    }

}
