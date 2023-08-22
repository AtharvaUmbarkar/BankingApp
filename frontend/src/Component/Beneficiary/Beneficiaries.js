
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getAllBeneficiaries } from "../../Utilities/api";
import { UserContext } from "../../Utilities/context/userContext";
import withAuthorization from "../../Utilities/context/withAuthorization";
import { LOGIN } from "../../Utilities/routes";


const condition = (authUser) => !authUser // User not logged in -> Redirect to Login

export default withAuthorization (condition, LOGIN) (() => {
    const [beneficiaries, setBeneficiaries] = useState([])
    const { user } = useContext(UserContext)

    useEffect(() => {
        const updateBeneficiaries = async (user) => {
            const result = await getAllBeneficiaries(user.userName)
            setBeneficiaries(result.data)
        }
        updateBeneficiaries(user);        
    }, [])

    return (
        <>
        {beneficiaries.map(({id, name}) => <p key={id}>{name}</p>)}
        </>
    )
})