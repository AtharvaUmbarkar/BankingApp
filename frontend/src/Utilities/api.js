import axios from "axios";
import { API_URL } from "./constants";


export const loginUser = async (userCredentials) => {
    const response = await axios.post(`${API_URL}/Login`, JSON.stringify(userCredentials), {
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response;
}

export const getAllBeneficiaries = async (currentUsername) => {
    const response = await axios.get(`${API_URL}/getAllBeneficiaries?username=${currentUsername}`)
    return response;
}

export const addBeneficiaryToCustomer = async (beneficiary) => {
    const response = await axios.post(`${API_URL}/save/beneficiary}`, JSON.stringify(beneficiary), {
        headers: {
            "Content-Type": "application/json"
        }
    })
}