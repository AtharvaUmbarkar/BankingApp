import { useState, useEffect } from "react"
import { useParams } from "react-router-dom"
import withAuthorization from "../../Utilities/context/withAuthorization"
import { getLatestTransactions, getStatement } from "../../Utilities/api"
import { LOGIN } from "../../Utilities/routes"
import Pagination from "../../Utilities/Pagination"
import DatePicker from "react-datepicker"

import "react-datepicker/dist/react-datepicker.css";
import { toast } from "react-hot-toast"
import { format } from 'date-fns';



const TRANSACTIONS_PER_PAGE = 8
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
  const { accountNumber } = useParams()
  const [page, setPage] = useState(1)
  const [from, setFrom] = useState("")
  const [to, setTo] = useState("")

  useEffect(() => {
    const updateTransactions = async (accountNumber, from, to) => {
      try{
          const result = await getStatement(accountNumber, from, to)
          setTransactions(result.data)
        } catch(error){
          toast.error(error.response.data.message)
        }
      }
      if (from && to) {
        if (to > from) {
          updateTransactions(accountNumber, formatDate(from), formatDate(to));
        } else {
          toast.error("Enter a valid range!")
        } 
      }
  }, [from, to])

    useEffect(() => {
        const updateTransactions = async (accountNumber) => {
            const result = await getLatestTransactions(accountNumber)
            setTransactions(result.data)
        }
        updateTransactions(accountNumber);        
    }, [])



  return (
    <div className='px-2 pt-4 pb-2 mx-auto max-w-4xl sm:grid sm:grid-cols-1 sm:px-0'>
      <h2 className="mt-2 text-lg text-center">Transaction History</h2>
      <div className="flex gap-4 mx-auto">
        <DatePicker placeholderText="From" format="dd-MM-yyyy" className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={from} onChange={(d) => setFrom(d)} />
        <DatePicker placeholderText="To" format="dd-MM-yyyy" className="mt-4 px-2 py-1.5 border rounded-md shadow-sm focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" selected={to} onChange={(d) => setTo(d)} />
      </div>
      <div className='mt-2 shadow-md'>
    <table className="w-full text-center">
      <thead className="text-gray-700 uppercase bg-gray-50">
        <tr>
        <th className='px-4 py-3 border font-semibold'>ID</th>
        <th className='px-4 py-3 border font-semibold'>Type</th>
        <th className='px-4 py-3 border font-semibold'>Receiver</th>
        <th className='px-4 py-3 border font-semibold'>Amount</th>
        <th className='px-4 py-3 border font-semibold'>Balance</th>
        <th className='px-4 py-3 border font-semibold'>Time</th>
        </tr>
      </thead>
      <tbody>
      {transactions.slice((page - 1)*TRANSACTIONS_PER_PAGE, ((page)*TRANSACTIONS_PER_PAGE)).map(([transaction, sender, receiver]) => (
        <tr key={transaction.txnId + Math.random()} className='border-b dark:border-gray-700 text-sm font-semibold text-gray-700'>
              <td className='px-4 py-2 border'><p>#{transaction.txnId}</p></td>            
              <td className='px-4 py-2 border'><button type="button" disabled className="p-1.5 bg-indigo-200 text-black w-[84px] rounded-md text-xs text-center uppercase">{transaction.txnType}</button></td>
              <td className='px-4 py-2 border'><p>{receiver ?? "-"}</p></td>            
          <td className='px-4 py-2 border'>{sender == accountNumber ?  <p className="text-sm leading-6 text-red-700">-&#8377;{transaction.txnAmount}</p>
: <p className="text-sm leading-6 text-green-700">+&#8377;{transaction.txnAmount}</p>
      }</td>
          <td className='px-4 py-2 border'><p className="text-sm leading-6 text-gray-700">&#8377;{sender == accountNumber ? transaction.senderBalance : transaction.receiverBalance}</p></td>
            <td className='px-4 py-2 border'>
              <p className="leading-5 text-gray-500">
                <time dateTime={transaction.txnDate}>{new Date(transaction.txnDate).toLocaleString()}</time>
              </p>
          </td>
        </tr>
      ))}
      </tbody>
    </table>
    </div>
    <Pagination 
      length={transactions ? transactions.length : 0} 
      totalPages={transactions ? Math.ceil(transactions.length / TRANSACTIONS_PER_PAGE) : 0}
      perPage={TRANSACTIONS_PER_PAGE}
      page={page}
      setPage={setPage}
      type={"transactions"}
    />
  </div>
  )
})
