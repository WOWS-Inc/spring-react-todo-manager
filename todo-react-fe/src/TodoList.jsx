import './TodoList.css'

export default function TodoList({todos, onClick}) {

    return (
        <>
            <h2>Todo List</h2>
            <div className="todo-list">
                {todos.map(todo => (
                    <div
                        key={todo.id}
                        className={"todo-item todo-" + (todo.completed ? 'completed' : 'todo')}
                        onClick={() => onClick(todo)}
                        >
                        <h3>[{todo.id}] {todo.title}</h3>
                        <p>{todo.user.username}</p>
                    </div>
                ))}
            </div>
        </>
    )
}