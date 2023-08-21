import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { getAllCustomers, getCustomerAccounts, getCustomerDetails } from '../../Utilities/api'
import { PaperClipIcon, UserIcon } from '@heroicons/react/20/solid'
import { ArrowTopRightOnSquareIcon } from '@heroicons/react/24/outline'
import { Link, useParams } from 'react-router-dom'


function CustomerDetails() {
  
  const [customer, setCustomer] = useState()
  const [accounts, setAccounts] = useState([])
  const { customerId, username } = useParams() 

    useEffect(() => {
      const updateData = async (customerId) => {
          const promises = [getCustomerDetails(customerId), getCustomerAccounts(username)]
          const result = await Promise.all(promises)
          setCustomer(result[0].data)
          setAccounts(result[1].data)
      }
      updateData(customerId, username);        
  }, [customerId, username])

  return (
    <div className="bg-white py-24 sm:py-32">
    <div className="mx-auto max-w-7xl px-6 lg:px-8">
      <div className="mx-auto max-w-2xl lg:mx-0">
        <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">{customer && customer.firstName}</h2>
        <p className="mt-2 text-lg leading-8 text-gray-600">
          {customer && customer.permAddressLine1}
        </p>
      </div>
      <div className="mx-auto mt-10 grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 border-t border-gray-200 pt-10 sm:mt-16 sm:pt-16 lg:mx-0 lg:max-w-none lg:grid-cols-3">
        {accounts.map(accountNumber => (
          <article key={accountNumber} className="flex max-w-xl flex-col items-start text-blue-500 justify-between">
            <Link to={`/admin/viewAccount/${accountNumber}`}>{accountNumber}</Link>
        </article>
        ))}
      </div>
    </div>
  </div>
  )
}

export default CustomerDetails