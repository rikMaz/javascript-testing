import React from 'react';
import styled from 'styled-components/macro';
import TodoList from './components/TodoList';
import useTodos from './hooks/useTodos';
import AddTodo from './components/AddTodo';
import Search from "./components/Search";
import useSearch from "./hooks/useSearch";

export default function App() {
    const [todos, create, remove, advance] = useTodos();
    const [search, setSearch, filteredTodos] = useSearch(todos);

    return (
        <Main>
            <Header>
                <h1>Super Kanban Board </h1>
                <AddTodo onAdd={create} />
                <Search search={search} onChange={setSearch}/>
            </Header>
            <Board>
                <TodoList
                    status="OPEN"
                    todos={filteredTodos}
                    onDelete={remove}
                    onAdvance={advance}
                />
                <TodoList
                    status="IN_PROGRESS"
                    todos={filteredTodos}
                    onDelete={remove}
                    onAdvance={advance}
                />
                <TodoList
                    status="DONE"
                    todos={filteredTodos}
                    onDelete={remove}
                    onAdvance={advance}
                />
            </Board>
        </Main>
    );
}

const Header = styled.header`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
`

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
