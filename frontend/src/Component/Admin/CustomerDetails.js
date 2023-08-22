import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { getAllCustomers, getCustomerAccounts, getCustomerDetails } from '../../Utilities/api'
import { PaperClipIcon, UserIcon } from '@heroicons/react/20/solid'
import { ArrowTopRightOnSquareIcon } from '@heroicons/react/24/outline'
import { Link, useParams } from 'react-router-dom'
import axios from 'axios'


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
      <div className="px-4 py-12 mx-auto max-w-xl sm:grid sm:grid-cols-1 sm:px-0">
            <dd className="mt-2 text-sm text-gray-900 sm:col-span-2 sm:mt-0">
              <ul role="list" className="divide-y divide-gray-100 rounded-md border border-gray-200">
              <li className="flex items-center justify-between py-4 pl-4 pr-5 text-lg leading-6">Accounts</li>
                {
                  accounts.map((account) => (
                    <li className="flex items-center justify-between py-4 pl-4 pr-5 text-sm leading-6">
                      <div className="flex w-0 flex-1 items-center">
                        <UserIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" />
                        <div className="ml-4 flex min-w-0 flex-1 gap-2">
                          <span className="truncate font-medium">{account.accountNumber}</span>
                        </div>
                      </div>
                      <div className="flex gap-x-2 items-center">
                          <span>
                          Balance: {account.accountBalance}
                          </span>
                          <Link to={`/admin/viewAccount/${account.accountNumber}`} className="font-medium text-indigo-600 hover:text-indigo-500">
                          <ArrowTopRightOnSquareIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" />
                        </Link>
                      </div>
                    </li>
                  ))
                }
              </ul>
            </dd>
      </div>
    </div>
  </div>
  )
}

export default CustomerDetails