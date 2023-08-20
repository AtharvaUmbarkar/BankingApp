import React, { useState } from 'react'
import { useNavigate, useOutletContext, useParams } from 'react-router-dom'
import axios from 'axios';

const RTGS = () => {
  const { accountNumber } = useParams();
  const [transactionDetails, setTransactionDetails] = useState({
    senderAccount: accountNumber,
    receiverAccount: "",
    txnAmount: 0,
    // txnDate: new Date(),
    userRemarks: "",
  })

  const navigate = useNavigate();
  const [beneficiaries] = useOutletContext();

  const handleChange = (e) => {
    setTransactionDetails((transactionDetails) =>
      ({ ...transactionDetails, [e.target.name]: e.target.value })
    )
    // console.log(transactionDetails);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await axios.post("http://localhost:8090/save/fundTransfer", {
      "transaction": {
        "txnType": "RTGS",
        "txnAmount": transactionDetails.txnAmount,
        "userRemarks": transactionDetails.userRemarks,
      },
      "receiverAccountNumber": transactionDetails.receiverAccount,
      "senderAccountNumber": transactionDetails.senderAccount,
    }, {
      headers: { "Content-Type": "application/json" }
    });
    window.alert(response.data)
  }

  return (
    <form onSubmit={handleSubmit} className='my-4 w-full'>
      <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Transaction Details</h2>

      <label className=" my-2">Sender Account Number:
        <input
          disabled
          type="text"
          name="senderAccount"
          value={transactionDetails.senderAccount}
          onChange={handleChange}
          className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
        />
      </label>

      <label className=" my-2">Receiver Account Number:
        <select
          name="receiverAccount"
          onChange={handleChange}
          className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-1 w-full"
        >
          <option className='w-full' value={""} ></option>
          {beneficiaries.map((b, i) => {
            return <option key={i} className='w-full' value={b.account} >{b.name + " " + b.account}</option>
          })}
        </select>
      </label>

      <button type='button' to='/user/beneficiaries' onClick={() => navigate('/user/beneficiaries')} className='p-1.5 mt-0.5 mb-3 w-full bg-blue-600 text-white'>ADD NEW</button>

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

export default RTGS