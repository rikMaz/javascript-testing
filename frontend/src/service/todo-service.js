const axios = require("axios");

export const getTodos = async () => {
  return await axios.get("/api/todo");
};

export const addTodo = async (description) => {
  return await axios.post("/api/todo", {
    status: "OPEN",
    description,
  });
};

export const deleteTodo = async (id) => {
  return await axios.delete(`/api/todo/${id}`);
};

export const setTodo = async (id, description, status) => {
  return await axios.put(`/api/todo/${id}`, {
    id,
    description,
    status,
  });
};
