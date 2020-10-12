package de.neuefische.todobackend.service;

import de.neuefische.todobackend.db.TodoDb;
import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.TodoStatus;
import de.neuefische.todobackend.model.dto.AddTodoItemDto;
import de.neuefische.todobackend.utils.IdUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class TodoServiceTest {

    private final IdUtils idUtils = mock(IdUtils.class);
    private final TodoDb db = mock(TodoDb.class);
    private final TodoService service = new TodoService(idUtils, db);

    @Test
    public void addTodoItemShouldCreateNewTodoItem() {
        //GIVEN
        AddTodoItemDto dto = new AddTodoItemDto("some description", TodoStatus.OPEN);
        when(idUtils.generateId()).thenReturn("some-id");

        //WHEN
        TodoItem todoItem = service.addTodoItem(dto);

        //THEN
        assertThat(todoItem, is(new TodoItem("some-id", "some description", TodoStatus.OPEN)));
    }

    @Test
    public void addTodoItemShouldAddNewTodoItemToDb() {
        //GIVEN
        AddTodoItemDto dto = new AddTodoItemDto("some description", TodoStatus.OPEN);
        when(idUtils.generateId()).thenReturn("some-id");

        //WHEN
        service.addTodoItem(dto);

        //THEN
        verify(db).addItem(new TodoItem("some-id", "some description", TodoStatus.OPEN));
    }


    @Test
    public void listAllTodosShouldReturnTodosFromDb() {
        //GIVEN
        when(db.list()).thenReturn(List.of(
                new TodoItem("some-id", "some description", TodoStatus.OPEN),
                new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS)));

        //WHEN
        List<TodoItem> todoItems = service.listAllTodos();

        //THEN
        assertThat(todoItems, containsInAnyOrder(new TodoItem("some-id", "some description", TodoStatus.OPEN),
                new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS)));
    }

    @Test
    public void deleteItemShouldCallDeleteOnDb(){
        //WHEN
        service.deleteItem("some-id");

        //THEN
        verify(db).deleteItem("some-id");
    }

    @Test
    public void updateItemShouldUpdateDbItem(){
        //GIVEN
        when(db.getTodoItem("123")).thenReturn(Optional.of(new TodoItem("123", "old description", TodoStatus.IN_PROGRESS)));

        //WHEN
        Optional<TodoItem> updateItem = service.updateItem (new TodoItem("123", "some description", TodoStatus.OPEN));

        //THEN
        assertThat(updateItem.get(), is(new TodoItem("123", "some description", TodoStatus.OPEN)));
        verify(db).updateItem(new TodoItem("123", "some description", TodoStatus.OPEN));
    }



    @Test
    public void updateItemShouldReturnEmptyOptionalWhenIdNotFound(){
        //GIVEN
        when(db.getTodoItem("455")).thenReturn(Optional.empty());

        //WHEN
        Optional<TodoItem> updateItem = service.updateItem(new TodoItem("455", "some description", TodoStatus.OPEN));

        //THEN
        assertThat(updateItem.isEmpty(), is(true));
    }
}
