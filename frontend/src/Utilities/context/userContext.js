import React, { useEffect, useState } from "react";

export const UserContext = React.createContext()

export const UserContextProvider = (props) => {
    const [user, setUser] = useState(
        sessionStorage.getItem("user") ?
            JSON.parse(sessionStorage.getItem("user")) : undefined
    )

    const [token, setToken] = useState(sessionStorage.getItem("token") ? sessionStorage.getItem("token") : undefined)

    const setUserInContext = (user) => {
        sessionStorage.setItem("user", JSON.stringify(user))
        sessionStorage.setItem("token", user.token)
        setUser(user)
        setToken(user.token)
    }

    const removeUser = () => {
        setUser(undefined);
        setToken(undefined)
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('token');
    }

    useEffect(() => {
        if (sessionStorage.getItem("user") && sessionStorage.getItem("token")) {
            setUser(JSON.parse(sessionStorage.getItem("user")))
            setToken(sessionStorage.getItem("token"))
        }
    }, [])

    return (
        <UserContext.Provider value={{ setUserInContext, user, removeUser, token }}>
            {props.children}
        </UserContext.Provider>
    )
}

