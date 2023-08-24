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
        const response = await axios.post(`${API_URL}/admin/LoginAdmin`, JSON.stringify(userCredentials), {
            headers: {
                "Content-Type": "application/json"
            }
        })
        return response;
    }
}

export const getAllBeneficiaries = async (currentUsername) => {
    const response = await axios.get(`${API_URL}/getAllBeneficiaries?userName=${currentUsername}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const addBeneficiaryToCustomer = async (beneficiary, userName) => {
    const response = await axios.post(`${API_URL}/save/beneficiary?userName=${userName}`, JSON.stringify(beneficiary), {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${sessionStorage.getItem("token")}`,
        }
    })
    return response
}

export const makeFundTransfer = async (transactionDetails) => {
    const response = await axios.post(`${API_URL}/save/fundTransfer`, JSON.stringify(transactionDetails), {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${sessionStorage.getItem("token")}`
        }
    })
    return response
}

export const getLatestTransactions = async (accountNumber) => {
    const response = await axios.get(`${API_URL}/getLatestTransactions?accountNumber=${accountNumber}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const getStatement = async (accountNumber, from, to) => {
    const response = await axios.get(`${API_URL}/getAccountStatement?accountNumber=${accountNumber}&from=${from}&to=${to}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const getAllCustomers = async () => {
    const response = await axios.get(`${API_URL}/admin/fetch/AllCustomers`, {
        headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}
    })
    return response;
}

export const getCustomerDetails = async (id) => {
    const response = await axios.get(`${API_URL}/fetchUser?customerId=${id}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const getCustomerAccounts = async (username) => {
    const response = await axios.get(`${API_URL}/fetchAccounts/${username}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const toggleActivation = async (actNo) => {
    const response = await axios.put(`${API_URL}/admin/toggle/Activation?acntNo=${actNo}`)
    return response
}

export const getCustomerAndAccountDetails = async (id) => {
    const response = await axios.get(`${API_URL}/getCustomerAndAccountDetails/${id}`, { headers: { "Authorization": `Bearer ${sessionStorage.getItem("token")}` } })
    return response;
}

export const searchCustomerByUsername = async (query) => {
    const response = await axios.get(`${API_URL}/admin/searchCustomerByUsername?query=${query}`)
    return response;
}

export const getTransactionStats = async (query) => {
    const response = await axios.get(`${API_URL}/admin/getTransactionStats`, {
        headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}
    })
    return response;
}