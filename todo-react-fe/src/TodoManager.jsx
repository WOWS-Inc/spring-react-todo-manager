import { useEffect, useState } from "react"

import TodoList from "./TodoList"

export default function App() {

  const [fetchCounter, setFetchCounter] = useState(0)
  const [todos, setTodos] = useState([])
  const [todoUpdate, setTodoUpdate] = useState(-1)

  const fetchCounterInc = () => setFetchCounter(counter => counter + 1)

  useEffect(() => {

    setInterval(fetchTodoUpdatedAt, 1000)
  })

  useEffect(() => { fetchTodos() }, [todoUpdate])

  const fetchTodoUpdatedAt = () => {

    fetch('http://localhost:4000/api/todos/udpate')
      .then(res => res.json())
      .then(setTodoUpdate)
  }
  const fetchTodos = () => {

    fetch('http://localhost:4000/api/todos')
      .then(res => res.json())
      .then(res => {
        
        fetchCounterInc()
        setTodos(res)
      })
  }
  const swapCompletedState = (todo) => {

    fetch(`http://localhost:4000/api/todos/${todo.id}/${!todo.completed}`, {method: 'PUT'})
      .then(res => res.json())
      .then(res => {

        setTodos(prev => prev.map(preTodo => preTodo.id === todo.id ? res : preTodo))
      })
  }

  return (
    <>
      <h2>Todo Manager</h2>
      Fetch count: {fetchCounter}
      <TodoList todos={todos} onClick={swapCompletedState} />
    </>
  )
}