import React, { useEffect, useState } from 'react'
import axios from "axios";

const BASE_URL = "http://localhost:8090/fetchAccounts/";
const USER_NAME = JSON.parse(sessionStorage.getItem('user'));
const TABLE_HEAD = ["Accounts"];

const Account = () => {
  const [accountsList, setAccounts] = useState([1, 2]);

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
    <div>
      
    </div>
  )
}

export default Account