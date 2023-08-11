import axios from "axios";
import React, { useState } from "react"
import { useNavigate } from "react-router-dom";


function Login() {

    const [user_id, setUserID] = useState();
    const [user_password, setUserPassword] = useState();
    const baseURL = "http://localhost:8090/Login"
    const navigate = useNavigate()

    const handleSubmit = async (event) => {
        event.preventDefault();
        axios.post(
            baseURL,
            {
                customer_id: user_id,
                password: user_password
            }
        ).then((response) => {
            if (response.data) {
                sessionStorage.setItem("userId", user_id)
                navigate("/")
            }
            else {
                alert("Invalid credentials")
            }
            setUserID("");
            setUserPassword("")
        }).catch(error => {
            alert("Error ---> " + error);
        })
    }


    const handle_idChange = (event) => {
        console.log(user_id);
        console.log(event.target.value)
        setUserID(event.target.value)
    }

    const handle_passChange = (event) => {
        setUserPassword(event.target.value)
    }

    return (

        <div className="flex flex-col w-2/5 mt-8">
            <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-blue-500 pb-2">Login Details</h2>
            <form onSubmit={handleSubmit} className=''>
                <label className="text-lg my-2">User ID:
                    <input
                        type="text"
                        name="user_id"
                        value={user_id}
                        onChange={handle_idChange || ""}
                        className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
                    />
                </label>
                <label className="text-lg my-2">User Password:
                    <input
                        type="password"
                        name="user_id"
                        value={user_password}
                        onChange={handle_passChange || ""}
                        className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
                    />
                </label>
                <input type="submit" value="Login" className="self-center p-2 uppercase bg-blue-800 text-white my-4" />
            </form>
        </div>

    )
}

export default Login;