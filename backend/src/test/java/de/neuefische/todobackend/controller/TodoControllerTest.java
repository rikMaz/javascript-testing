package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.db.TodoDb;
import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.TodoStatus;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoDb todoDb;

    @MockBean
    private IdUtils idUtils;

    private String getUrl() {
        return "http://localhost:" + port + "/api/todo";
    }

    @BeforeEach
    public void setup(){
        todoDb.clear();
    }

    @Test
    public void postTodoShouldAddTodoItemToDb() {
        //GIVEN
        AddTodoItemDto itemToPost = new AddTodoItemDto("Some todo", TodoStatus.IN_PROGRESS);
        when(idUtils.generateId()).thenReturn("123");

        //WHEN
        ResponseEntity<TodoItem> response = restTemplate.postForEntity(getUrl(), itemToPost, TodoItem.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        TodoItem expectedTodoItem = new TodoItem(
                "123",
                "Some todo",
                TodoStatus.IN_PROGRESS
        );
        assertThat(response.getBody(), is(expectedTodoItem));

        Optional<TodoItem> savedTodoItem = todoDb.getTodoItem("123");
        assertThat(savedTodoItem.get(), is(expectedTodoItem));

    }

    @Test
    public void getTodoItemsShouldReturnAllItemsFromDb() {
        //GIVEN
        todoDb.addItem(new TodoItem(
                "first-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        ));
        todoDb.addItem(new TodoItem(
                "second-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        ));

        //WHEN
        ResponseEntity<TodoItem[]> response = restTemplate.getForEntity(getUrl(), TodoItem[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(Arrays.asList(response.getBody()), containsInAnyOrder(new TodoItem(
                "first-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        ), new TodoItem(
                "second-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        )));

    }

    @Test
    public void deleteShouldDeleteItemFromDb(){
        //GIVEN
        todoDb.addItem(new TodoItem(
                "first-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        ));
        todoDb.addItem(new TodoItem(
                "second-item",
                "Some todo",
                TodoStatus.IN_PROGRESS
        ));

        //When
        restTemplate.delete(getUrl()+"/first-item");

        //THEN
        Optional<TodoItem> todoItem = todoDb.getTodoItem("first-item");
        assertThat(todoItem.isEmpty(), is(true));
    }

}
