import React from 'react';
import styled from 'styled-components/macro';
import TodoList from './components/TodoList';
import useTodos from './hooks/useTodos';

export default function App() {
    const [todos] = useTodos();

    return (
        <Main>
            <h1>Super Kanban Board </h1>
            <TodoList todos={todos} />
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
