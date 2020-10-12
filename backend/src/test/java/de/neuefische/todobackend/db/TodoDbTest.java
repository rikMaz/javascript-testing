package de.neuefische.todobackend.db;

import de.neuefische.todobackend.model.TodoItem;
import de.neuefische.todobackend.model.TodoStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class TodoDbTest {

    private final TodoDb db = new TodoDb();

    @Test
    public void addItemShouldSaveItemToDatabase(){
        //GIVEN
        TodoItem item = new TodoItem("some-id", "some description", TodoStatus.OPEN);

        //WHEN
        db.addItem(item);

        //THEN
        Optional<TodoItem> savedItem = db.getTodoItem("some-id");
        assertThat(savedItem.get(), is(new TodoItem("some-id","some description", TodoStatus.OPEN)));
    }

    @Test
    public void getItemShouldReturnMatchingItem(){
        //GIVEN
        db.addItem(new TodoItem("some-id", "some description", TodoStatus.OPEN));
        db.addItem(new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS));
        db.addItem(new TodoItem("some-id-3", "description", TodoStatus.DONE));

        //WHEN
        Optional<TodoItem> result = db.getTodoItem("some-id-2");

        //THEN
        assertThat(result.get(), is(new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS)));
    }

    @Test
    public void getItemShouldReturnEmptyWhenIdNotFound(){
        //GIVEN
        TodoItem item = new TodoItem("some-id", "some description", TodoStatus.OPEN);
        db.addItem(item);

        //WHEN
        Optional<TodoItem> result = db.getTodoItem("other-id");

        //THEN
        assertThat(result.isEmpty(), is(true));
    }

    @Test
    public void listShouldReturnAllItems(){
        //GIVEN
        db.addItem(new TodoItem("some-id", "some description", TodoStatus.OPEN));
        db.addItem(new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS));
        db.addItem(new TodoItem("some-id-3", "description", TodoStatus.DONE));

        //WHEN
        List<TodoItem> items = db.list();

        //THEN
        assertThat(items, containsInAnyOrder(new TodoItem("some-id", "some description", TodoStatus.OPEN),
        new TodoItem("some-id-2", "some other description", TodoStatus.IN_PROGRESS),
        new TodoItem("some-id-3", "description", TodoStatus.DONE)));
    }

}
