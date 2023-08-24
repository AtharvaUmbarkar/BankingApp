import React, { useContext, useEffect, useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';
import { UserContext } from '../../Utilities/context/userContext';
import { toast } from 'react-hot-toast';


const UserAccounts = () => {
  const BASE_URL = "http://localhost:8090/fetchAccounts";
  const { user, token } = useContext(UserContext)
  const [accountsList, setAccounts] = useState(undefined);

  useEffect(() => {
    const fetchAccounts = async () => {
      if (user) {
        try {
          const authHeader = "Bearer " + token
          console.log(authHeader);
          const response = await axios.get(
            BASE_URL,
            {
              params: { username: user.userName },
              headers: { "Authorization": authHeader }
            },
          )
          // console.log(response.data);
          setAccounts(response.data);
        } catch (error) {
          toast.error(error.message)
        }
      }
    }
    fetchAccounts();
  }, []);

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-1/4 my-8'>
        {accountsList && accountsList.map((account, i) => {
          return (
            <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Number: </span><span>{account.accountNumber}</span></p>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Type: </span><span>{account.accountType}</span></p>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Balance: </span><span>{account.accountBalance}</span></p>
              <Link to={'/account/' + account.accountNumber} className={`self-end bg-indigo-700 text-white py-1 px-2 rounded mt-8 ${!account.active && 'pointer-events-none disabled cursor-not-allowed'}`}>{account.active ? "Enter Account" : "Account Disabled"}</Link>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default UserAccounts