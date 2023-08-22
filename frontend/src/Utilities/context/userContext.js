import React, { useEffect, useState } from "react";

export const UserContext = React.createContext()

export const UserContextProvider = (props) => {
    const [user, setUser] = useState(
        sessionStorage.getItem("user") ?
            JSON.parse(sessionStorage.getItem("user")) : undefined
    )

    const setUserInContext = (user) => {
        sessionStorage.setItem("user", JSON.stringify(user))
        setUser(user)
    }

    const removeUser = () => {
        setUser(undefined);
        sessionStorage.removeItem('user');
    }

    useEffect(() => {
        if (sessionStorage.getItem("user")) {
            setUser(JSON.parse(sessionStorage.getItem("user")))
        }
    }, [])

    return (
        <UserContext.Provider value={{ setUserInContext, user, removeUser }}>
            {props.children}
        </UserContext.Provider>
    )
}

