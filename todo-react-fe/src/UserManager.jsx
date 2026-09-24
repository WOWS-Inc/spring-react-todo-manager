import { Link } from "react-router-dom";
import { useEffect, useState } from "react"

export default function UserManager() {

    const [users, setUsers] = useState([])

    useEffect(() => {
        fetch('http://localhost:4000/api/users')
            .then(res => res.json())
            .then(setUsers)
    }, [])

    return (
        <>
            <h2>User Manager</h2>
            <div className="user-list">
                {users.map(user => (
                    <div className="user-item">
                        <h3>
                            <Link to={`/users/${user.id}`}>
                                [{user.id}] {user.username}: {user.todoCount}
                            </Link>
                        </h3>
                    </div>
                ))}
            </div>
        </>
    )
}