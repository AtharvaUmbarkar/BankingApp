import { useState, useEffect } from "react"
import { useParams } from "react-router-dom"
import withAuthorization from "../../Utilities/context/withAuthorization"
import { getLatestTransactions, getStatement } from "../../Utilities/api"
import { LOGIN } from "../../Utilities/routes"
import DatePicker from "react-datepicker"

import "react-datepicker/dist/react-datepicker.css";
import { toast } from "react-hot-toast"
import Pagination from "../../Utilities/Pagination"

const TRANSACTIONS_PER_PAGE = 4
const condition = (authUser) => !authUser // User not logged in -> redirect Login

const formatDate = (date) => {
  if (date) {
    let day = date.getDate()
    let month = date.getMonth()
    if (day < 10) day = `0${day}`
    if (month < 10) month = `0${month + 1}`
    return `${date.getFullYear()}-${month}-${day}`
  }
}


export default withAuthorization(condition, LOGIN)(() => {

  const [transactions, setTransactions] = useState([])
  const [from, setFrom] = useState("")
  const [to, setTo] = useState(new Date())
  const { accountNumber } = useParams()
  const [page, setPage] = useState(1)



  useEffect(() => {
    // console.log(formatDate(from), formatDate(to))
    const updateTransactions = async (accountNumber, from, to) => {
      try{
        const result = await getStatement(accountNumber, from, to)
        setTransactions(result.data)
      } catch (e){
        toast.error(e.response.data.message)
      }
      // console.log(result.data);
    }
    if (from && to) {
      if (to > from) {
        updateTransactions(accountNumber, formatDate(from), formatDate(to));
      } else {
        toast.error("Enter a valid range!")
      }

    }
  }, [from, to])


  return (
    <div className='w-full flex flex-col items-center'>
      <h2 className="mt-4 text-xl">Statement</h2>
      <div className="flex gap-4">
        <DatePicker placeholderText="From" format="yyy-MM-dd" className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={from} onChange={(d) => setFrom(d)} />
        <DatePicker placeholderText="To" format="yyy-MM-dd" className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={to} onChange={(d) => setTo(d)} />
      </div>
      <div className='mt-2 w-[400px]'>
      {(from && to && transactions.length) ? 
      <>
        <ul role="list" className="divide-y-2 divide-gray-300">
          {transactions.slice((page - 1)*TRANSACTIONS_PER_PAGE, ((page)*TRANSACTIONS_PER_PAGE)).map(([transaction, sender, receiver], i) => (
            <li key={i + Math.random()} className="flex justify-between gap-x-6 py-2">
              <div className="flex min-w-0 gap-x-4">
                {transaction.txnType == "IMPS" || transaction.txnType == "NEFT" || transaction.txnType == "RTGS" ?
                  <div className="shrink-0 sm:flex sm:flex-col sm:items-start">
                    <p className="text-xl font-semibold leading-6 text-gray-900">{String(sender) === accountNumber ? `To: ${receiver}` : `From: ${sender}`}</p>
                    <button type="button" disabled className="mt-2 mb-1 flex justify-center rounded-md bg-indigo-600 px-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">{transaction.txnType}</button>
                    <p className="text-sm font-semibold leading-6 text-gray-900">#{transaction.txnId}</p>
                  </div>
                  :
                  <div className="shrink-0 sm:flex sm:flex-col sm:items-start">
                    <button type="button" disabled className="mt-2 mb-1 flex justify-center rounded-md bg-indigo-600 px-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600 uppercase">{transaction.txnType}</button>
                    <p className="text-sm font-semibold leading-6 text-gray-900">#{transaction.txnId}</p>
                  </div>
                }
              </div>
              <div className="shrink-0 sm:flex sm:flex-col sm:items-end">
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
            ))}
          </ul>
            <Pagination 
              length={transactions ? transactions.length : 0} 
              totalPages={transactions ? Math.ceil(transactions.length / TRANSACTIONS_PER_PAGE) : 0}
              perPage={TRANSACTIONS_PER_PAGE}
              page={page}
              setPage={setPage}
              type={"transactions"}
            /> 
          </>
          :
           <p className="mt-6 text-center text-gray-500 font-semibold">No records</p>}
      </div>
    </div>
  )
})
