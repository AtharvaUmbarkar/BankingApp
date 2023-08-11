import axios from "axios";
import React, {useState} from "react"


function Login(){

    const [user_id, setUserID] = useState();
    const [user_password, setUserPassword] = useState();
    const baseURL = "http://localhost:8090/Login"
    const handleSubmit = async (event) => {
        event.preventDefault();
        axios.post(
            baseURL,
            {
                customer_id: user_id,
                password: user_password   
            }
        ).then((response) => {
            alert("User ID : "+response.data);
            if(response.data == "Login Success")
            {

            }
            else
            {
                    alert("Invalid credentials")
            }
            setUserID("");
            setUserPassword("")
        }).catch(error => {
            alert("Error ---> "+error);
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

    return(
        
        <div className="h-screen">
            <form onSubmit={handleSubmit} className='user-form'>
                <label>User ID:
                <input 
                        type="text" 
                        name="user_id" 
                        value={user_id} 
                        onChange={handle_idChange || ""}
                        className="border-solid border-2 border-black"
                />
                </label>
                <label>User Password:
                <input 
                        type="text" 
                        name="user_id" 
                        value={user_password} 
                        onChange={handle_passChange || ""}
                        className="border-solid border-2 border-black"
                />
                </label>

        
                <div className="user-form-inputs user-form-button">
                 <input type="submit" value="Login" />
                </div>

            </form>
        </div>        
        
    )
}

export default Login;