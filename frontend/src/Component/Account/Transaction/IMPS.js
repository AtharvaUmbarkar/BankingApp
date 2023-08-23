import React, { useEffect, useState, useContext } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { getAllBeneficiaries, makeFundTransfer } from "../../../Utilities/api";
import { UserContext } from "../../../Utilities/context/userContext";
import withAuthorization from '../../../Utilities/context/withAuthorization';
import { ADD_BENEFICIARY, LOGIN } from '../../../Utilities/routes';
import { toast } from 'react-hot-toast';


const condition = (authUser) => !authUser

export default withAuthorization (condition, LOGIN)(() => {
  
  const { accountNumber } = useParams()
  const [transactionDetails, setTransactionDetails] = useState({
    senderAccount: accountNumber,
    receiverAccount: "",
    txnAmount: 0,
    // txnDate: new Date(),
    userRemarks: "",
  })
  const [beneficiaries, setBeneficiaries] = useState([])
  const { user } = useContext(UserContext)
  const navigate = useNavigate()



  useEffect(() => {
      const updateBeneficiaries = async (user) => {
        const result = await getAllBeneficiaries(user.userName)
        setBeneficiaries(result.data)
      }
      updateBeneficiaries(user);      
  }, [])


  const handleChange = (e) => {
    setTransactionDetails((transactionDetails) =>
      ({ ...transactionDetails, [e.target.name]: e.target.value })
    )
    // console.log(transactionDetails);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try{
      const response = await makeFundTransfer({
        transaction: {
          txnType: "IMPS",
          txnAmount: transactionDetails.txnAmount,
          remarks: transactionDetails.userRemarks
        },
        senderAccountNumber: accountNumber,
        receiverAccountNumber: transactionDetails.receiverAccount
      })
      if(response){
        toast.success(response.data)
        setTransactionDetails({
          senderAccount: accountNumber,
          receiverAccount: "",
          txnAmount: 0,
          // txnDate: new Date(),
          userRemarks: "",
        })
      }
    } catch(e){
      toast.error(e.response.data.message)
    }
  }

  return (
    <form onSubmit={handleSubmit} className='my-4 w-full flex flex-col'>
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
          name="receiverAccount"
          onChange={handleChange}
          className="border rounded-md pl-3 pr-10 ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 p-1.5 mt-1 mb-1 w-full"
        >
          <option className='w-full' value=""></option>
          {beneficiaries.map((b, i) => {
            return <option key={i} className='w-full' value={b.accountNumber} >{b.name + " " + b.accountNumber}</option>
          })}
        </select>
      </label>

      <button type='button' onClick={() => navigate(ADD_BENEFICIARY)} className='rounded p-1 uppercase px-2 mt-0.5 mb-1 block bg-slate-500 text-sm text-white self-end'>+ Add new</button>

      <label className="w-full my-2">Amount
        <input
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

      <button type='submit' className='p-2 my-4 w-full rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600'>SUBMIT</button>
    </form>
  )
})