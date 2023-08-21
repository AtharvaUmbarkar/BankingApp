import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { getAllCustomers } from '../../Utilities/api'
import { PaperClipIcon, UserIcon } from '@heroicons/react/20/solid'
import { ArrowTopRightOnSquareIcon } from '@heroicons/react/24/outline'
import { Link } from 'react-router-dom'


function CustomersList() {
  
  const [customers, setCustomers] = useState([])

    useEffect(() => {
      const updateCustomers = async () => {
          const result = await getAllCustomers()
          setCustomers(result.data)
      }
      updateCustomers();        
  }, [])

  return (
    <div className="px-4 py-12 mx-auto max-w-xl sm:grid sm:grid-cols-1 sm:px-0">
    <dd className="mt-2 text-sm text-gray-900 sm:col-span-2 sm:mt-0">
      <ul role="list" className="divide-y divide-gray-100 rounded-md border border-gray-200">
        {
          customers.map(({customerId, firstName, userName}) => (
            <li className="flex items-center justify-between py-4 pl-4 pr-5 text-sm leading-6">
              <div className="flex w-0 flex-1 items-center">
                <UserIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" />
                <div className="ml-4 flex min-w-0 flex-1 gap-2">
                  <span className="truncate font-medium">{firstName}</span>
                </div>
              </div>
              <div className="flex gap-x-2 items-center">
                  <span>
                  ID: {customerId}
                  </span>
                  <Link to={`/admin/viewCustomer/${customerId}/${userName}`} className="font-medium text-indigo-600 hover:text-indigo-500">
                  <ArrowTopRightOnSquareIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" />
                </Link>
              </div>
            </li>
          ))
        }
      </ul>
    </dd>
  </div>
  )
}

export default CustomersList