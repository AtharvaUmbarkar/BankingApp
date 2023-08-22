import React, { useContext, useEffect, useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';
import { UserContext } from '../../Utilities/context/userContext';


const UserAccounts = () => {
  const BASE_URL = "http://localhost:8090/fetchAccounts/";
  const { user } = useContext(UserContext)
  const [accountsList, setAccounts] = useState([]);

  useEffect(() => {
    const fetchAccounts = async () => {
      if (user) {
        const response  = await axios.get(BASE_URL + user.userName)
        console.log(response.data);
        setAccounts(response.data);
      }
    }
    fetchAccounts();
  }, []);

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-1/4 my-8'>
        {accountsList.map(({accountNumber}, i) => {
          return (
            <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
              <p><span className='font-semibold'>Account Number: </span><span>{accountNumber}</span></p>
              <Link to={'/account/' + accountNumber} className='self-end bg-indigo-700 text-white py-1 px-2 rounded mt-8'>Enter Account</Link>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default UserAccounts