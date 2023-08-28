import React, { useState } from 'react'
import { useNavigate, useOutletContext, useParams } from 'react-router-dom'
import axios from 'axios'
import { toast } from 'react-hot-toast';
import { ADD_BENEFICIARY, LOGIN } from '../../../Utilities/routes';
import { Dialog } from '@headlessui/react';
import { XMarkIcon } from '@heroicons/react/24/outline';

const NEFT = () => {
  const { accountNumber } = useParams();
  const [transactionDetails, setTransactionDetails] = useState({
    senderAccount: accountNumber,
    receiverAccount: "",
    transactionPassword: "",
    txnAmount: 0,
    // txnDate: new Date(),
    userRemarks: "",
  })

  const navigate = useNavigate();
  const [beneficiaries] = useOutletContext();
  const [modalOpen, setModalOpen] = useState(false);

  const handleChange = (e) => {
    setTransactionDetails((transactionDetails) =>
      ({ ...transactionDetails, [e.target.name]: e.target.value })
    )
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
      const response = await axios.post("http://localhost:8090/save/fundTransfer", {
        "transaction": {
          "txnType": "NEFT",
          "txnAmount": transactionDetails.txnAmount,
          "userRemarks": transactionDetails.userRemarks,
        },
        "receiverAccountNumber": transactionDetails.receiverAccount,
        "senderAccountNumber": transactionDetails.senderAccount,
        transactionPassword: transactionDetails.transactionPassword,
      }, {
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
    <form autoComplete='off' onSubmit={handleSubmit} className='my-4 w-full flex flex-col'>
      <h2 className='text-xl mb-3 font-semibold'>Transaction Details</h2>

      <label className="w-full">Sender Account Number:
        <input
          disabled
          type="text"
          name="senderAccount"
          value={transactionDetails.senderAccount}
          className="border rounded-md pl-3 pr-10 disabled:border-slate-500 disabled:bg-slate-50 disabled:text-slate-500 p-1.5 mt-1 mb-3"
        />
      </label>

      <label className="w-full">Receiver Account Number:
        <select
          required
          name="receiverAccount"
          value={transactionDetails.receiverAccount}
          onChange={handleChange}
          className="border rounded-md pl-3 pr-10 ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 p-1.5 mt-1 mb-1 w-full"
        >
          <option className='w-full' value="" ></option>
          {beneficiaries.map((b, i) => {
            return <option key={i} className='w-full' value={b.accountNumber} >{b.name + ": " + b.accountNumber}</option>
          })}
        </select>
      </label>

      <button type='button' onClick={() => navigate(ADD_BENEFICIARY)} className='rounded p-1 uppercase px-2 mt-0.5 mb-1 block bg-slate-500 text-sm text-white self-end'>+ Add new</button>

      <label className="w-full my-2">Amount
        <input
          required
          min={1}
          type="number"
          name="txnAmount"
          value={transactionDetails.txnAmount}
          onChange={handleChange}
          className="border rounded-md pl-3 pr-10 ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 p-1.5 mt-1"
        />
      </label>

      {/* <label className="w-full">Transaction Date:
      <input
        type="date"
        name="txnDate"
        value={transactionDetails.txnDate}
        onChange={handleChange}
        className="border rounded-md pl-3 pr-10 ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 p-1.5 mt-1"
      />
    </label> */}

      <label className="w-full">Remarks:
        <input
          type="text"
          name="userRemarks"
          value={transactionDetails.userRemarks}
          onChange={handleChange}
          className="border rounded-md pl-3 pr-10 ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 p-1.5 mt-1"
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
          className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3 w-full"
        />
      </label>

      <button type='submit' className='p-2 w-full rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600'>SUBMIT</button>

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

export default NEFT