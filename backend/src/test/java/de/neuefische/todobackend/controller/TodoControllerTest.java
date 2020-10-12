package de.neuefische.todobackend.controller;

import de.neuefische.todobackend.db.TodoDb;
import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.TodoStatus;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    public void postTodoShouldAddTodoItemToDb(){
        //GIVEN
        AddTodoItemDto itemToPost = new AddTodoItemDto("Some todo", TodoStatus.IN_PROGRESS);
        when(idUtils.generateId()).thenReturn("123");

        //WHEN
        ResponseEntity<TodoItem> response = restTemplate.postForEntity("http://localhost:" + port + "/api/todo", itemToPost, TodoItem.class);

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

}
