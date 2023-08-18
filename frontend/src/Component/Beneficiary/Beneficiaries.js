
import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../Utilities/constants";

const getAllBeneficiaries = async (currentUserID) => {
    const response = await axios.get(`${API_URL}/getAllBeneficiaries?userId=${currentUserID}`)
    return response;
}
const Beneficiaries = () => {
    const [beneficiaries, setBeneficiaries] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        const updateBeneficiaries = async() => {
            const result = await getAllBeneficiaries(user.username)
            setBeneficiaries(result.data)
        }

        let user = sessionStorage.getItem("user")
        if(user){
            user = JSON.parse(user)
            updateBeneficiaries();
        } else{
            navigate("/login")
        }
    }, [])

    return (
        <>
        {beneficiaries.map(({id, name}) => <p key={id}>{name}</p>)}
        </>
    )

 
}

export default Beneficiaries