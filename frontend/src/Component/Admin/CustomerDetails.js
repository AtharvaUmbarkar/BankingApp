import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { getAllCustomers, getCustomerAccounts, getCustomerAndAccountDetails, getCustomerDetails, toggleActivation } from '../../Utilities/api'
import { PaperClipIcon, PhoneIcon, UserIcon } from '@heroicons/react/20/solid'
import { ArrowTopRightOnSquareIcon, CalendarDaysIcon } from '@heroicons/react/24/outline'
import { Link, useParams } from 'react-router-dom'
import { toast } from 'react-hot-toast'


function CustomerDetails() {
  
  const [customer, setCustomer] = useState()
  const [accounts, setAccounts] = useState([])
  const { customerId, username } = useParams() 
  const [toggled, setToggled] = useState(false)

    useEffect(() => {
      const updateData = async (customerId) => {
          const result = await getCustomerAndAccountDetails(customerId)
          console.log(result)
          if(result){
            setCustomer(result.data[0][0])
            setAccounts(result.data.map(([c, a]) => a))
          }
      }
      updateData(customerId, username);        
  }, [customerId, username, toggled])

    const handleDisable = async (accountNumber, message) => {
      try{
        const response = await toggleActivation(accountNumber)
        toast.success(`Account ${message}`)
        setToggled((prev) => !prev)
      } catch(e){
        toast.error(e.response.data)
      }
    }

  return (
    <div className="bg-white py-24 sm:py-32">
    <div className="mx-auto max-w-7xl px-6 lg:px-8">
      <div className="mx-auto max-w-2xl lg:mx-0">
        <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">{customer && customer.firstName}</h2>
        <p className="mt-2 text-lg leading-8 text-gray-600">
          {customer && customer.permAddressLine1}
        </p>
        <div className="flex items-center">
          <PhoneIcon className="h-5 w-5 flex-shrink-0 text-indigo-600"/>{customer && customer.mobileNumber}
        </div>
        <div className="flex items-center">
          <CalendarDaysIcon className="h-5 w-5 flex-shrink-0 text-indigo-600"/>{customer && customer.dateOfBirth}
        </div>
      </div>
      <div className="mx-auto mt-10 grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 border-t border-gray-200 pt-10 sm:mt-16 sm:pt-16 lg:mx-0 lg:max-w-none lg:grid-cols-3">
        {accounts.map(({accountNumber, accountType, accountBalance, active}) => (
          <article key={accountNumber} className="flex max-w-xl flex-col items-start text-blue-500 justify-between">
            <Link to={`/admin/viewAccount/${accountNumber}`}>{accountNumber}</Link>
            <span className="p-2 bg-blue-100 mt-2 text-black text-lg">{accountType}</span>
            <span className="p-2 bg-blue-100 mt-2 text-black text-lg"> Account Balance: &#8377;{accountBalance}</span>

            <button className="mt-2 rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600" onClick={(e) => handleDisable(accountNumber, active ? "disabled" : "activated")}>{active ? "Disable" : "Activate"}</button>
        </article>
        ))}
      </div>
    </div>
  </div>
  )
}

export default CustomerDetails