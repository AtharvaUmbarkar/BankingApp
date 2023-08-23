import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { getAllCustomers, searchCustomerByUsername } from '../../Utilities/api'
import { UserIcon } from '@heroicons/react/20/solid'
import { ArrowTopRightOnSquareIcon } from '@heroicons/react/24/outline'
import { Link } from 'react-router-dom'
import Pagination from '../../Utilities/Pagination'


const USERS_PER_PAGE = 6

function CustomersList() {
  
  const [allCustomers, setAllCustomers] = useState([])
  const [query, setQuery] = useState("")
  const [page, setPage] = useState(1)

    useEffect(() => {
      const updateCustomers = async () => {
          const result = await getAllCustomers()
          setAllCustomers([...result.data])
      }
      updateCustomers();        
  }, [])

  const filterByUsername = async (e) => {
    e.preventDefault();
    const response = await searchCustomerByUsername(query)
    if(response){
      setAllCustomers(...response.data)
    }
  } 

  return (
    <div className="px-4 pt-8 pb-2 mx-auto max-w-xl sm:grid sm:grid-cols-1 sm:px-0">
      
<form class="flex items-center" onSubmit={filterByUsername}>   
    <label htmlFor="simple-search" class="sr-only">Search</label>
    <div className="relative w-full">
        <input type="text" id="simple-search" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Search by username..." name='query' value={query} onChange={(e) => setQuery(e.target.value)} required />
    </div>
    <button type="submit" class="p-2.5 ml-2 text-sm font-medium text-white bg-indigo-700 rounded-lg border border-blue-700 hover:bg-indgo-800 focus:ring-4 focus:outline-none focus:ring-indigo-300 dark:bg-indigo-600 dark:hover:bg-indigo-700 dark:focus:ring-indigo-800">
        <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
            <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
        </svg>
        <span class="sr-only">Search</span>
    </button>
</form>

    <dd className={`mt-4 text-sm text-gray-900 sm:col-span-2 overflow-y-auto h-[400px]`}>
      <ul role="list" className="divide-y divide-gray-100 rounded-md border border-gray-200">
        {
          allCustomers.slice((page - 1)*USERS_PER_PAGE, ((page)*USERS_PER_PAGE)).map(({customerId, firstName, userName}) => (
            <li key={`${customerId}${Math.random()}`} className="flex items-center justify-between py-2 pl-4 pr-5 text-sm leading-6">
              <div className="flex w-0 flex-1 items-center">
                <UserIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" />
                <div className="ml-4 flex min-w-0 flex-col flex-1">
                  <span className="truncate font-medium">{firstName}</span>
                  <span className="truncate font-light">@{userName}</span>
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
    <Pagination 
      length={allCustomers ? allCustomers.length : 0} 
      totalPages={allCustomers ? Math.ceil(allCustomers.length / USERS_PER_PAGE) : 0}
      perPage={USERS_PER_PAGE}
      page={page}
      setPage={setPage}
      type={"customers"}
    />
  </div>
  )
}

export default CustomersList