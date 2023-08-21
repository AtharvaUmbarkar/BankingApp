import axios from "axios";
import { API_URL } from "./constants";


export const loginUser = async (userCredentials, admin) => {
    if (!admin) {
        const response = await axios.post(`${API_URL}/Login`, JSON.stringify(userCredentials), {
            headers: {
                "Content-Type": "application/json"
            }
        })
        return response;
    }
    else {
        const response = await axios.post(`${API_URL}/LoginAdmin`, JSON.stringify(userCredentials), {
            headers: {
                "Content-Type": "application/json"
            }
        })
        return response;
    }
}

export const getAllBeneficiaries = async (currentUsername) => {
    const response = await axios.get(`${API_URL}/getAllBeneficiaries?userName=${currentUsername}`)
    return response;
}

export const addBeneficiaryToCustomer = async (beneficiary, userName) => {
    const response = await axios.post(`${API_URL}/save/beneficiary?userName=${userName}`, JSON.stringify(beneficiary), {
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response
}

export const makeFundTransfer = async (transactionDetails) => {
    const response = await axios.post(`${API_URL}/save/fundTransfer`, JSON.stringify(transactionDetails), {
        headers: {
            "Content-Type": "application/json"
        }
    })
    return response
}

export const getLatestTransactions = async (accountNumber) => {
    const response = await axios.get(`${API_URL}/getLatestTransactions?accountNumber=${accountNumber}`)
    return response;
}

export const getStatement = async (accountNumber, from, to) => {
    const response = await axios.get(`${API_URL}/getAccountStatement?accountNumber=${accountNumber}&from=${from}&to=${to}`)
    return response;
}