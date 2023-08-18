import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from "axios"

const WITHDRAW_URL = "http://localhost:8090/save/withdraw";

const Withdraw = () => {
    const { accountNumber } = useParams();
    const navigate = useNavigate();

    const [transactionDetails, setTransactionDetails] = useState({
        senderAccountNumber: accountNumber,
        txnAmount: 0,
        userRemarks: "",
    })

    const handleChange = (e) => {
        setTransactionDetails((transactionDetails) =>
            ({ ...transactionDetails, [e.target.name]: e.target.value })
        )
        // console.log(transactionDetails);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(transactionDetails);
        const res = await axios.post(WITHDRAW_URL, JSON.stringify(
            {
                "transaction": {
                    "txnType": "Withdraw",
                    "txnAmount": transactionDetails.txnAmount,
                    "userRemarks": transactionDetails.userRemarks
                },
                "senderAccountNumber": transactionDetails.senderAccountNumber
            }
        ), {
            headers: {
                "Content-Type": "application/json"
            }
        })
        window.alert(res.data);
    }

    return (
        <form onSubmit={handleSubmit} className='my-4 w-full'>
            <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Transaction Details</h2>

            <label className=" my-2">Sender Account Number:
                <input
                    disabled
                    type="number"
                    name="senderAccountNumber"
                    value={transactionDetails.senderAccountNumber}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
                />
            </label>

            <label className="w-full my-2">Amount
                <input
                    type="number"
                    name="txnAmount"
                    value={transactionDetails.txnAmount}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
                />
            </label>

            <label className=" my-2">Remarks:
                <input
                    type="text"
                    name="userRemarks"
                    value={transactionDetails.userRemarks}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
                />
            </label>

            <button type='submit' className='p-2 my-4 w-full bg-blue-600 text-xl text-white rounded-sm'>SUBMIT</button>
        </form>
    )
}

export default Withdraw