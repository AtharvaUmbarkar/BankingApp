import React, { useContext, useEffect, useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';
import { UserContext } from '../../Utilities/context/userContext';
import { toast } from 'react-hot-toast';


const UserAccounts = () => {
  const BASE_URL = "http://localhost:8090/fetchAccounts";
  const CREATE_ACCOUNT_URL = "http://localhost:8090/createAccount";
  // const { user, token } = useContext(UserContext)
  const token = sessionStorage.getItem("token");
  const { user } = useContext(UserContext);
  const [accountsList, setAccounts] = useState([])

  const handleOnClick = async (e) => {
    e.preventDefault();
    try {
      const authHeader = "Bearer " + token;
      const response = await axios.post(
        `${CREATE_ACCOUNT_URL}?username=${user.userName}`,
        {},
        {
          headers: { "Authorization": authHeader }
        }
      )
      if (response) {
        toast.success("Acount Created Successfully", { duration: 3000 })
        window.location.reload()
      }
    } catch (error) {
      if(error.response.data){
        toast.error(error.response.data.message, { duration: 3000 })
      }
      else{ 
        toast.error(error.message, { duration: 3000 })
      }
    }
  }

  useEffect(() => {
    const fetchAccounts = async () => {
      if (user) {
        try {
          const authHeader = "Bearer " + token
          const response = await axios.get(
            BASE_URL,
            {
              params: { username: user.userName },
              headers: { "Authorization": authHeader }
            },
          )
          setAccounts(response.data);
        } catch (error) {
          toast.error(error.response.data.message)
        }
      }
    }
    fetchAccounts();
  }, []);

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-97% my-8 lg:w-1/4 sm:w-1/2 md:w-1/3'>
        <h1 className='font-semibold text-2xl mb-4 border-b border-indigo-700 w-full text-center pb-4'>Your Accounts</h1>
        <button type='button' onClick={handleOnClick} className='block bg-indigo-700 text-center w-full p-1 text-white mb-4 rounded'>+ Create Account</button>
        {accountsList && accountsList.map((account, i) => {
          return (
            <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded text-xs sm:text-base md:text-base lg:text-base'>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Number: </span><span>{account.accountNumber}</span></p>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Type: </span><span>{account.accountType}</span></p>
              <p className='flex flex-row items-center justify-between'><span className='font-semibold'>Account Balance: </span><span>{account.accountBalance}</span></p>
              <Link to={'/account/' + account.accountNumber} className={`self-end bg-indigo-700 text-white py-1 px-2 rounded mt-8 ${!account.active && 'bg-slate-500 pointer-events-none disabled cursor-not-allowed'}`}>{account.active ? "Select Account" : "Account Disabled"}</Link>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default UserAccounts