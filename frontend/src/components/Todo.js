import React from 'react';
import styled from 'styled-components';

export default function Todo({ id, status, description }) {
    return (
        <StyledTodo>
            <h3>{description}</h3>
        </StyledTodo>
    );
}

const StyledTodo = styled.section`
    padding: 8px;
    border: 1px solid salmon;
    border-radius: 8px;
`;
