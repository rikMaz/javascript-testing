const axios = require('axios');

export const getTodos = async () => {
    return await axios.get('/api/todo');
};

export const addTodo = async (description) => {
    return await axios.put('/api/todo', {
        description: description
    });
};

export const deleteTodo = async (id) => {
    return await axios.delete(`/api/todo/${id}`);
};

export const setTodoStatus = async (id, status) => {
    return await axios.put(`/api/todo/${id}/status`, {
        status: status
    });
};

