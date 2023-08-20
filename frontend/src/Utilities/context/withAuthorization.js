import { useContext, useEffect } from "react"
import { UserContext } from "./userContext"
import { useNavigate } from "react-router-dom"


export default (condition, redirectURL) => (Component) => (props) => {

    const { username } = useContext(UserContext)
    const navigate = useNavigate()

    
    useEffect(() => {
        // if condition satisfies for user, redirect.
        if(condition(username)){
           if(typeof window !== "undefined") navigate(redirectURL)
        }
    }, []) 


    return <Component {...props} />

}