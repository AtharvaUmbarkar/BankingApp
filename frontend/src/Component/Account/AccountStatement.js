import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";


const BASE_URL = "http://localhost:8090/getStatementTransactions"
const AccountStatement = () => {
  const { accountNumber } = useParams();
const [fromDate, setFromDate] = useState();
const [toDate, setToDate] = useState();
const handleSubmit = () => {
  //alert("Account statement for : "+accountNumber)
  axios.get(BASE_URL,
    {params: {accountNo : accountNumber,
    fromDt : fromDate,
    toDt : toDate}}).then((response) => {
          console.log(response.data);
          //alert("Heyyyyyyy you got response : "+response.data[1].txnAmount);
          var tbl = document.getElementById("tblBody");
          var i = 0;
          tbl.innerHTML = " ";

          if(response.data.length > 0)
          {
            for(i=0; i<response.data.length; i++)
            {
                var row = tbl.insertRow(0);
                var dtColumn = row.insertCell(0);
                var amountColumn = row.insertCell(1);
                var balanceColumn = row.insertCell(2);

                dtColumn.innerHTML = response.data[i].txnDate.substring(0,10);
                amountColumn.innerHTML = response.data[i].txnAmount;
                balanceColumn.innerHTML = response.data[i].senderBalance;
            }
          }
          else
          {
            alert("No transactions found..")
          }


  }).catch(error => {
    alert("Error while fetching transactions for this accountNumber"+error);
  })
}
const changeFromDate = (event) => {
  setFromDate(event.target.value)
}
const changeToDate = (event) => {
  alert(" "+event.target.value.localeCompare(fromDate))
  if(event.target.value.localeCompare(fromDate) < 0)
  {
    alert("To-Date cant not be older than From-Date");
    setToDate("");
  }
  else
  {
    setToDate(event.target.value)
  }
  
}

  return (
    <div>
    <form className='w-full flex flex-col items-center' >
    <div className='px-4 my-2 w-full lg:w-1/3'>
      <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Check Account Statement</h2>
      <label className=" my-2">From:
          <input
            type='date'
            name="fromDt"
            value={fromDate}
            onChange={changeFromDate}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">To:
          <input
            type='date'
            name="toDt"
            value={toDate}
            onChange={changeToDate}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <div className='px-4 my-2 w-full lg:w-1/3'>
        <button type='button' className='p-2 my-4 w-full bg-blue-500 text-xl text-white' onClick={handleSubmit}>SUBMIT</button>
      </div>
    </div>
    </form>
    <div>
      <br></br>
      <center>
        <span id='display' name='display' className="p-2 bg-blue-100 text-black text-lg">Transactions Data</span>
      <br></br>
      <br></br>
      <div class="relative overflow-x-auto">
        <table id="txnList" class="border-collapse border border-slate-400 p-100 text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-center text-sm text-gray-700 uppercase bg-gray-200 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="px-6 py-3">
                        Date
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Amount
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Balance
                    </th>
                </tr>
            </thead>
            <tbody id="tblBody" class="space-x-0 m-20 text-center text-lg text-gray-700 uppercase bg-blue-100 dark:bg-blue-700 dark:text-gray-400">

            </tbody>
        </table>
      </div>
    </center>
  </div>
  </div>

  )
})
