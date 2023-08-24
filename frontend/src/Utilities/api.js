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

export const getAllCustomers = async () => {
    const response = await axios.get(`${API_URL}/fetch/AllCustomers`)
    return response;
}

export const getCustomerDetails = async (id) => {
    const response = await axios.get(`${API_URL}/fetchUser?customerId=${id}`)
    return response;
}

export const getCustomerAccounts = async (username) => {
    const response = await axios.get(`${API_URL}/fetchAccounts/${username}`)
    return response;
}

export const toggleActivation = async (actNo) => {
    const response = await axios.put(`${API_URL}/toggle/Activation?acntNo=${actNo}`)
    return response
}

export const getCustomerAndAccountDetails = async (id) => {
    const response = await axios.get(`${API_URL}/getCustomerAndAccountDetails/${id}`)
    return response;
}

export const searchCustomerByUsername = async (query) => {
    const response = await axios.get(`${API_URL}/searchCustomerByUsername?query=${query}`)
    return response;
}

export const getTransactionStats = async (query) => {
    const response = await axios.get(`${API_URL}/getTransactionStats`)
    return response;
}