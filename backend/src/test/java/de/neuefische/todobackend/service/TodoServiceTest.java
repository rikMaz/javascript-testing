package de.neuefische.todobackend.service;

import de.neuefische.todobackend.db.TodoDb;
import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.TodoStatus;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private final IdUtils idUtils = mock(IdUtils.class);
    private final TodoDb db = mock(TodoDb.class);
    private final TodoService service = new TodoService(idUtils, db);

    @Test
    public void addTodoItemShouldCreateNewTodoItem(){
        //GIVEN
        AddTodoItemDto dto = new AddTodoItemDto("some description", TodoStatus.OPEN);
        when(idUtils.generateId()).thenReturn("some-id");

        //WHEN
        TodoItem todoItem = service.addTodoItem(dto);

        //THEN
        assertThat(todoItem, is(new TodoItem("some-id","some description", TodoStatus.OPEN)));
    }

    @Test
    public void addTodoItemShouldAddNewTodoItemToDb(){
        //GIVEN
        AddTodoItemDto dto = new AddTodoItemDto("some description", TodoStatus.OPEN);
        when(idUtils.generateId()).thenReturn("some-id");

        //WHEN
        service.addTodoItem(dto);

        //THEN
        verify(db).addItem(new TodoItem("some-id","some description", TodoStatus.OPEN));
    }


}
