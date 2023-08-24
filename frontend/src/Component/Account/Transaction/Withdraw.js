import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from "axios"
import { toast } from 'react-hot-toast';

const WITHDRAW_URL = "http://localhost:8090/save/withdraw";

const Withdraw = () => {
    const { accountNumber } = useParams();
    const navigate = useNavigate();

    const [transactionDetails, setTransactionDetails] = useState({
        senderAccountNumber: accountNumber,
        transactionPassword: "",
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
        try {
            const res = await axios.post(WITHDRAW_URL, JSON.stringify(
                {
                    "transaction": {
                        "txnType": "Withdraw",
                        "txnAmount": transactionDetails.txnAmount,
                        "userRemarks": transactionDetails.userRemarks
                    },
                    "senderAccountNumber": transactionDetails.senderAccountNumber,
                    transactionPassword: transactionDetails.transactionPassword
                }
            ), {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`
                }
            })
            toast.success(res.data, { duration: 3000 })
        } catch (error) {
            toast.error(error.response.data.message, { duration: 3000 })

        }
    }

    return (
        <form onSubmit={handleSubmit} className='my-4 w-full'>
            <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold'>Transaction Details</h2>

            <label className=" my-2">Sender Account Number:
                <input
                    disabled
                    type="number"
                    name="senderAccountNumber"
                    value={transactionDetails.senderAccountNumber}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
                />
            </label>

            <label className="w-full my-2">Amount
                <input
                    required
                    min={1}
                    type="number"
                    name="txnAmount"
                    value={transactionDetails.txnAmount}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
                />
            </label>

            <label className=" my-2">Remarks:
                <input
                    type="text"
                    name="userRemarks"
                    value={transactionDetails.userRemarks}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
                />
            </label>

            <label className="w-full my-2">Transaction Password:
                <input
                    required
                    minLength={8}
                    type="password"
                    name="transactionPassword"
                    value={transactionDetails.transactionPassword}
                    onChange={handleChange}
                    className="border w-full border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
                />
            </label>

            <button type='submit' className='p-2 my-4 w-full bg-indigo-700 text-xl text-white rounded-sm'>SUBMIT</button>
        </form>
    )
}

export default Withdraw