import { useState, useEffect } from 'react'
import { Link, useParams } from 'react-router-dom'
import { getLatestTransactions } from '../../Utilities/api'


function AccountDetails() {
  
    const [transactions, setTransactions] = useState([])
    const { accountNumber } = useParams()

    useEffect(() => {
        const updateTransactions = async (accountNumber) => {
            const result = await getLatestTransactions(accountNumber)
            setTransactions(result.data)
        }
        updateTransactions(accountNumber);        
    }, [])



  return (
    <div className='w-full flex flex-col items-center'>
      <h2 className="mt-8 text-xl">Transaction History</h2>
    <div className='mt-8 w-3/5 shadow-md'>
    <table className="w-full text-center">
      <thead className="text-gray-700 uppercase bg-gray-50">
        <tr>
        <th className='px-4 py-3 font-semibold'>ID</th>
        <th className='px-4 py-3 font-semibold'>Type</th>
        <th className='px-4 py-3 font-semibold'>Receiver</th>
        <th className='px-4 py-3 font-semibold'>Amount</th>
        <th className='px-4 py-3 font-semibold'>Balance</th>
        <th className='px-4 py-3 font-semibold'>Time</th>
        </tr>
      </thead>
      <tbody>
      {transactions.map(([transaction, sender, receiver]) => (
        <tr key={transaction.txnId} className='border-b dark:border-gray-700 text-sm font-semibold text-gray-700'>
              <td className='px-4 py-3'><p>#{transaction.txnId}</p></td>            
              <td className='px-4 py-3'><button type="button" disabled className="p-1.5 bg-indigo-200 text-black w-[84px] rounded-md text-xs text-center uppercase">{transaction.txnType}</button></td>
              <td className='px-4 py-3'><p>{receiver ?? "-"}</p></td>            
          <td className='px-4 py-3'>{sender == accountNumber ?  <p className="text-sm leading-6 text-red-700">-&#8377;{transaction.txnAmount}</p>
: <p className="text-sm leading-6 text-green-700">+&#8377;{transaction.txnAmount}</p>
      }</td>
          <td className='px-4 py-3'><p className="text-sm leading-6 text-gray-700">&#8377;{sender == accountNumber ? transaction.senderBalance : transaction.receiverBalance}</p></td>
            <td className='px-4 py-3'>
              <p className="leading-5 text-gray-500">
                <time dateTime={transaction.txnDate}>{new Date(transaction.txnDate).toLocaleString()}</time>
              </p>
          </td>
        </tr>
      ))}
      </tbody>
    </table>
    </div>
  </div>
  )
}

export default AccountDetails