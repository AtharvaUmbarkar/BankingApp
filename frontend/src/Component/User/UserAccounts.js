import React, { useEffect, useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';


const UserAccounts = () => {
  const BASE_URL = "http://localhost:8090/fetchAccounts/";
  const USER_NAME = JSON.parse(sessionStorage.getItem('user'));
  const [accountsList, setAccounts] = useState([]);

  const fetchAccounts = () => {
    if (USER_NAME) {
      axios.get(BASE_URL + USER_NAME.username).then((response) => {
        setAccounts(response.data);
      }).catch(error => {
        console.log(error);
      })
    }
  }

  useEffect(() => {
    fetchAccounts();
  }, [USER_NAME]);

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-1/4 my-8'>
        {accountsList.map((account, i) => {
          return (
            <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
              <p><span className='font-semibold'>Account Number: </span><span>{account}</span></p>
              <Link to={'/account/' + account} className='self-end bg-indigo-700 text-white py-1 px-2 rounded mt-8'>Enter Account</Link>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default UserAccounts