import React, { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from 'axios'
import { toast } from 'react-hot-toast'
import { Dialog } from '@headlessui/react'
import { XMarkIcon } from '@heroicons/react/24/outline'

const DEPOSIT_URL = "http://localhost:8090/save/deposit"

const Deposit = () => {
    const { accountNumber } = useParams();
    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false)

    const [transactionDetails, setTransactionDetails] = useState({
        receiverAccountNumber: accountNumber,
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

    const openModal = () => {
        setModalOpen(true);
    }

    const closeModal = () => {
        setModalOpen(false);
        setTransactionDetails({
            senderAccount: accountNumber,
            transactionPassword: "",
            receiverAccount: "",
            txnAmount: 0,
            // txnDate: new Date(),
            userRemarks: "",
        })
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post(DEPOSIT_URL, JSON.stringify({
                transaction: {
                    txnAmount: transactionDetails.txnAmount,
                    userRemarks: transactionDetails.userRemarks,
                    txnType: "Deposit",
                },
                receiverAccountNumber: transactionDetails.receiverAccountNumber,
                transactionPassword: transactionDetails.transactionPassword
            }), {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`
                }
            });
            openModal();
        } catch (error) {
            if (error.response.data) toast.error(error.response.data.message, { duration: 3000 })
            else toast.error("Transaction Failed!", { duration: 3000 })

        }

    }

    return (
        <form autoComplete='off' onSubmit={handleSubmit} className='my-4 w-full'>
            <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold'>Transaction Details</h2>

            <label className=" my-2">Receiver Account Number:
                <input
                    disabled
                    type="text"
                    name="receiverAccountNumber"
                    value={transactionDetails.receiverAccountNumber}
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

            <label className=" my-2">Transaction Password:
                <input
                    required
                    minLength={8}
                    type="password"
                    name="transactionPassword"
                    value={transactionDetails.transactionPassword}
                    onChange={handleChange}
                    className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
                />
            </label>

            <button type='submit' className='p-2 w-full bg-indigo-700 text-xl text-white rounded-sm'>SUBMIT</button>
            <Dialog open={modalOpen} className={"fixed top-0 right-0 bottom-0 left-0 overflow-auto z-[60] bg-white"} onClose={closeModal}>
                <Dialog.Panel className="w-full h-full grid place-content-center p-2 z-50">
                    <div className='h-full w-full p-8 shadow-md flex flex-col'>
                        <button type='button' className='cursor-pointer self-end' onClick={(closeModal)}><XMarkIcon width={28} height={28} /></button>
                        <h1 className='text-3xl font-semibold text-green-500 my-2'>Transaction Successful</h1>
                        <ul className='flex flex-col itens center w-full'>
                            <li className='flex flex-row items-center'>
                                <div className='font-semibold w-56 whitespace-nowrap'>Sender Account Number:</div>
                                <div>{transactionDetails.senderAccount}</div>
                            </li>
                            <li className='flex flex-row items-center'>
                                <div className='font-semibold w-56 whitespace-nowrap'>Receiver Account Number:</div>
                                <div>{transactionDetails.receiverAccount}</div>
                            </li>
                            <li className='flex flex-row items-center'>
                                <div className='font-semibold w-56 whitespace-nowrap'>Amount:</div>
                                <div>{transactionDetails.txnAmount}</div>
                            </li>
                            <li className='flex flex-row items-center'>
                                <div className='font-semibold w-56 whitespace-nowrap'>Remarks:</div>
                                <div>{transactionDetails.userRemarks}</div>
                            </li>
                        </ul>
                    </div>
                </Dialog.Panel>
            </Dialog>
        </form>
    )
}

export default Deposit