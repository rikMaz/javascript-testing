import React from 'react';
import styled from 'styled-components/macro';
import TodoList from './components/TodoList';
import useTodos from './hooks/useTodos';
import AddTodo from './components/AddTodo';

export default function App() {
    const [todos, create, remove, advance] = useTodos();

    return (
        <Main>
            <h1>Super Kanban Board </h1>
            <AddTodo onAdd={create} />
            <Board>
                <TodoList
                    status="OPEN"
                    todos={todos}
                    onDelete={remove}
                    onAdvance={advance}
                />
                <TodoList
                    status="IN_PROGRESS"
                    todos={todos}
                    onDelete={remove}
                    onAdvance={advance}
                />
                <TodoList
                    status="DONE"
                    todos={todos}
                    onDelete={remove}
                    onAdvance={advance}
                />
            </Board>
        </Main>
    );
}

const Main = styled.main`
    height: 100vh;
    padding: 8px;

    h1 {
        color: hotpink;
    }
`;

const Board = styled.section`
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 24px;
`;
