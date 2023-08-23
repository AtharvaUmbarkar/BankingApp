import { useState, useEffect } from "react"
import { useParams } from "react-router-dom"
import withAuthorization from "../../Utilities/context/withAuthorization"
import { getLatestTransactions } from "../../Utilities/api"
import { LOGIN } from "../../Utilities/routes"
import Pagination from "../../Utilities/Pagination"



const TRANSACTIONS_PER_PAGE = 8
const condition = (authUser) => !authUser // User not logged in -> redirect Login

export default withAuthorization(condition, LOGIN)(() => {

  const [transactions, setTransactions] = useState([])
  const { accountNumber } = useParams()
  const [page, setPage] = useState(1)

    useEffect(() => {
        const updateTransactions = async (accountNumber) => {
            const result = await getLatestTransactions(accountNumber)
            setTransactions(result.data)
        }
        updateTransactions(accountNumber);        
    }, [])



  return (
    <div className='px-2 pt-4 pb-2 mx-auto max-w-6xl sm:grid sm:grid-cols-1 sm:px-0'>
      <h2 className="mt-2 text-lg text-center">Transaction History</h2>
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
