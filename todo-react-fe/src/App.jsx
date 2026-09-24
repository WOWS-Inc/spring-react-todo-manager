import { Routes, Route, Link, useNavigate } from "react-router-dom";

import "./App.css"

import TodoManager from "./TodoManager.jsx"
import UserManager from "./UserManager.jsx"
import SingleUserManager from "./SingleUserManager.jsx";

export default function App() {

    const navigate = useNavigate();

    return (
        <>
            <h1>Todo Application</h1>
            <div className="linker">
                <button
                    onClick={() => navigate(-1)}
                >BACK</button>
                <button
                    onClick={() => navigate(+1)}
                >FORWARD</button>
                -
                <Link
                    to="/"
                    >
                    HOME
                </Link>
                <Link
                    to="/todos"
                    >
                    TODOs
                </Link>
                <Link
                    to="/users"
                    >
                    USERs
                </Link>
            </div>
            <Routes>
                <Route 
                    path="/todos"
                    element={<TodoManager />}
                />
                <Route 
                    path="/users"
                    element={<UserManager />}
                />
                <Route 
                    path="/users/:id"
                    element={<SingleUserManager />}
                />
            </Routes>

        </>
    )
}