import React, { useEffect, useState } from "react";

export const UserContext = React.createContext()

export const UserContextProvider = (props) => {
    const [username, setUsername] = useState(
        sessionStorage.getItem("user") ?
            JSON.parse(sessionStorage.getItem("user")).username : undefined
    )

    const [user, setUser] = useState(
        sessionStorage.getItem("user") ?
            JSON.parse(sessionStorage.getItem("user")).username : undefined
    )

    const setUsernameInContext = (user) => {
        sessionStorage.setItem("user", JSON.stringify({ username: user.userName }))
        setUsername(user.userName)
        setUser(user)
    }

    const removeUser = () => {
        setUser(undefined);
        setUsername(undefined);
        sessionStorage.removeItem('user');
    }

    useEffect(() => {
        if (sessionStorage.getItem("user")) {
            setUsername(JSON.parse(sessionStorage.getItem("user")).username)
        }
    }, [])

    return (
        <UserContext.Provider value={{ username, setUsernameInContext, user, removeUser }}>
            {props.children}
        </UserContext.Provider>
    )
}

