import React, { useEffect, useState } from "react";

export const UserContext = React.createContext()

export const UserContextProvider = (props) => {
    const [username, setUsername] = useState(
        sessionStorage.getItem("user") ?
        JSON.parse(sessionStorage.getItem("user")).username : undefined
        )

    const setUsernameInContext = (uname) => {
        sessionStorage.setItem("user", JSON.stringify({username: uname}))
        setUsername(uname)
    }

    useEffect(() => {
        if(sessionStorage.getItem("user")){
            setUsername(JSON.parse(sessionStorage.getItem("user")).username)
        }
    }, [])

    return (
        <UserContext.Provider value={{username, setUsernameInContext}}>
            {props.children}
        </UserContext.Provider>
    )
}

