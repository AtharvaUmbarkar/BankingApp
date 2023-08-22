import { useState, useEffect } from "react"
import { useParams } from "react-router-dom"
import withAuthorization from "../../Utilities/context/withAuthorization"
import { getLatestTransactions, getStatement } from "../../Utilities/api"
import { LOGIN } from "../../Utilities/routes"
import DatePicker from "react-datepicker"

import "react-datepicker/dist/react-datepicker.css";
import { toast } from "react-hot-toast"


const condition = (authUser) => !authUser // User not logged in -> redirect Login

const formatDate = (date) => {
  if(date){
    let day = date.getDate()
    let month = date.getMonth()
    if(day < 10) day =`0${day}`
    if(month < 10) month =`0${month + 1}`
    return `${date.getFullYear()}-${month}-${day}`
  }
}


export default withAuthorization(condition, LOGIN)(() => {

  const [transactions, setTransactions] = useState([])
  const [from, setFrom] = useState("")
  const [to, setTo] = useState("")
  const { accountNumber } = useParams()

    useEffect(() => {
      console.log(formatDate(from), formatDate(to))
        const updateTransactions = async (accountNumber, from, to) => {
            const result = await getStatement(accountNumber, from, to)
            setTransactions(result.data)
        }
        if(from && to){
          if(to > from){
            updateTransactions(accountNumber, formatDate(from), formatDate(to));
          } else{
            toast.error("Enter a valid range!")
          }
            
        }
    }, [from, to])


  return (
    <div className='w-full flex flex-col items-center'>
    <h2 className="mt-8 text-xl">Statement</h2>
    <div className="flex gap-4">
    <DatePicker placeholderText="From" format="yyy-MM-dd"  className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={from} onChange={(d) => setFrom(d)}/>
    <DatePicker placeholderText="To" format="yyy-MM-dd"  className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={to} onChange={(d) => setTo(d)}/>
    </div>
    <div className='mt-8 w-2/5 px-4'>
    <ul role="list" className="divide-y divide-gray-100">
      {from && to && (transactions.length ? transactions.map(([transaction, sender, receiver]) => (
        <li key={transaction.id} className="flex justify-between gap-x-6 py-5">
          <div className="flex min-w-0 gap-x-4">
              {transaction.txnType == "IMPS" || transaction.txnType == "NEFT" || transaction.txnType == "RTGS" ? 
              <div className="shrink-0 sm:flex sm:flex-col sm:items-start">
              <p className="text-xl font-semibold leading-6 text-gray-900">{receiver}</p>
              <button type="button" disabled className="mt-2 flex justify-center rounded-md bg-indigo-600 px-1.5 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">{transaction.txnType}</button>
              </div>
              :
              <div className="shrink-0 sm:flex sm:flex-col sm:items-start">
              <button type="button" disabled className="mt-2 flex justify-center rounded-md bg-indigo-600 px-1.5 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 uppercase">{transaction.txnType}</button>
              </div>
              }
            {/* <div className="min-w-0 flex-auto">
              <p className="text-sm font-semibold leading-6 text-gray-900">{transaction.userRemarks}</p>
            </div> */}
          </div>
          <div className="hidden shrink-0 sm:flex sm:flex-col sm:items-end">
            {sender == accountNumber ? 
            <>
              <p className="text-sm leading-6 text-red-700">-&#8377;{transaction.txnAmount}</p>
              <p className="mt-1 text-l leading-5 text-gray-500">
              &#8377;{transaction.senderBalance}
              </p>
            </>
            
            :
            <>
              <p className="text-sm leading-6 text-green-700">+&#8377;{transaction.txnAmount}</p>
              <p className="mt-1 text-l leading-5 text-gray-500">
                &#8377;{transaction.receiverBalance}
              </p>
            </>
            }
              <p className="mt-1 text-xs leading-5 text-gray-500">
                <time dateTime={transaction.txnDate}>{new Date(transaction.txnDate).toLocaleString()}</time>
              </p>
          </div>
        </li>
      ))  : <p className="text-center text-gray-500 font-semibold">No records</p>)}
    </ul>
    </div>
  </div>
  )
})
