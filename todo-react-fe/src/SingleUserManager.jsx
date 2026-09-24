import { useEffect, useState } from "react";
import { useParams} from "react-router-dom";

import TodoList from "./TodoList"

export default function SingleUserManager() {

    const { id } = useParams();

    const [user, setUser] = useState({})
    const [todos, setTodos] = useState([])

    useEffect(() => {

        fetch(`http://localhost:4000/api/users/${id}`)
            .then(res => res.json())
            .then(setUser)

        fetch(`http://localhost:4000/api/users/${id}/todos`)
            .then(res => res.json())
            .then(setTodos)
    }, [])

    const swapCompletedState = (todo) => {

        fetch(`http://localhost:4000/api/todos/${todo.id}/${!todo.completed}`, {method: 'PUT'})
            .then(res => res.json())
            .then(res => {

                setTodos(prev => prev.map(preTodo => preTodo.id === todo.id ? res : preTodo))
            })
    }

    return (
        <>
            <h2>
                [{id}] {user.username}: {user.todoCount}
            </h2>
            <TodoList todos={todos} onClick={swapCompletedState} />
        </>
    )
}