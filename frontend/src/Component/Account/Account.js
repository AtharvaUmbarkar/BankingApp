import React, { useEffect, useState } from 'react'
import axios from "axios";
import { Link } from 'react-router-dom';

const BASE_URL = "http://localhost:8090/fetchAccounts/";
const USER_NAME = JSON.parse(sessionStorage.getItem('user'));
const TABLE_HEAD = ["Accounts"];

const Account = () => {
  const [accountsList, setAccounts] = useState([]);

  const fetchAccounts = () => {
    axios.get(BASE_URL + USER_NAME.username).then((response) => {
      setAccounts(response.data);
    }).catch(error => {
      console.log(error);
    })
  }

  useEffect(() => {
    fetchAccounts();
  }, []);

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-1/4 my-8'>
        {accountsList.map((account, i) => {
          return (
            <div className='w-full p-4 my-2 bg-slate-200 shadow flex flex-col rounded'>
              <p><span className='font-semibold'>Account Number: </span><span>{account}</span></p>
              <Link to='/account' className='self-end bg-blue-600 text-white py-1 px-2 rounded mt-8'>Enter Account</Link>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default Account