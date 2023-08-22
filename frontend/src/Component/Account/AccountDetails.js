import React from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';
import { toast } from 'react-hot-toast';
import { format } from 'date-fns';

const BASE_URL = "http://localhost:8090/fetchAccount";
const AccountDetails = () => {
  const { accountNumber } = useParams();
  const [accountDetails, setAccountDetails] = useState(undefined);



  useEffect(() => {
    const fetchAccount = () => {
      axios.get(BASE_URL,
        { params: { accountNo: accountNumber } }).then((response) => {
          setAccountDetails(response.data)
          console.log(response.data);
        }).catch(error => {
          toast.error(error)
        })
    }
    fetchAccount();
  }, [])

  return (
    <div className='w-full p-8'>
      <div className='w-2/5 flex flex-col mx-auto items-center'>
        <h1 className='font-semibold text-2xl mb-8'>Account Details</h1>
        {accountDetails &&
          <div className='w-full shadow-md rounded bg-slate-200 p-2'>
            <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 rounded'>
              <span className='w-1/2 font-semibold'>Account Number:</span>
              <span className='flex-grow'>{accountDetails.accountNumber}</span>
            </div>
            <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 rounded'>
              <span className='w-1/2 font-semibold'>Account Type:</span>
              <span className='flex-grow'>{accountDetails.accountType}</span>
            </div>
            <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 rounded'>
              <span className='w-1/2 font-semibold'>Account Balance:</span>
              <span className='flex-grow'>{accountDetails.accountBalance}</span>
            </div>
            <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 rounded'>
              <span className='w-1/2 font-semibold'>Creation Date:</span>
              <span className='flex-grow'>{format(new Date(accountDetails.accountCreationDate), "dd/MM/yyyy")}</span>
            </div>
            <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 rounded'>
              <span className='w-1/2 font-semibold'>Account Status:</span>
              <span className='flex-grow'>{accountDetails.active ? "Activated" : "Deactivated"}</span>
            </div>
          </div>
        }
      </div>
    </div>

  )
}

export default AccountDetails