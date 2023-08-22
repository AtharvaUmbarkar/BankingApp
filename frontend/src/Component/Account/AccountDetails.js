import React from 'react'
import { useParams } from 'react-router-dom'
import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';

const BASE_URL = "http://localhost:8090/fetchAccount";
const AccountDetails = () => {
  const { accountNumber } = useParams();
  
  //alert("Inside account : "+accountNumber);

  const fetchAccount= () => {
    var acNo = Number(accountNumber)
    //alert(typeof(acNo))
    axios.get(BASE_URL,
      {params: {accountNo : accountNumber}}).then((response) => {
            console.log(response.data.accountBalance);
            var accNo = document.getElementById("accNo");
            accNo.innerHTML = "Account Number : "+response.data.accountNumber;
            var accType = document.getElementById("accType");
            accType.innerHTML = "Account Type : "+response.data.accountType;
            var accBalance = document.getElementById("accBalance");
            accBalance.innerHTML = "Account Balance : "+response.data.accountBalance;
            var accCreationDate = document.getElementById("accCreationDate");
            accCreationDate.innerHTML = "Creation Date : "+response.data.accountCreationDate.substring(0,10);
            var netBanking = document.getElementById("netBanking");
            var debitCard = document.getElementById("debitCard");
            if(response.data.netBankingOpted)
            {
              netBanking.innerHTML = "Net Banking Opted : YES";
            }
            else
            {
              netBanking.innerHTML = "Opted for netbanking : NO";
            }
            if(response.data.debitCardAvailed)
            {
              debitCard.innerHTML = "Debit Card Availed : YES";
            }
            else
            {
              debitCard.innerHTML = "Debit Card Availed : NO";
            }
            //setAccountData(response.data);  
    }).catch(error => {
      alert("Error while fetching transactions for this accountNumber"+error);
    })
  }

  useEffect(() => {
    fetchAccount();
  })
  return (
    <div>
      <center>
        <br></br>
        <br></br>
        <br></br>
        
      <span  id="accNo" className="w-100 p-2 bg-blue-100 text-black text-lg"></span>
        <br></br>        
        <br></br>
        <span id="accType" className="p-2 bg-blue-100 text-black text-lg"></span>
        <br></br>
        <br></br>
        <span id="accBalance" className="p-2 bg-blue-100 text-black text-lg"></span>
        <br></br>
        <br></br>
        <span id="accCreationDate" className="p-2 bg-blue-100 text-black text-lg"></span>
        <br></br>
        <br></br>
        <span id="netBanking" className="p-2 bg-blue-100 text-black text-lg"></span>
        <br></br>
        <br></br>
        <span id="debitCard" className="p-2 bg-blue-100 text-black text-lg"></span>

      </center>
    </div>
  )
}

export default AccountDetails