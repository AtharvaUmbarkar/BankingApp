import { useState, useEffect } from 'react'
import AdminNavbar from './AdminNavbar'
import { createAccount, getAllCustomers, getCustomerAccounts, getCustomerAndAccountDetails, getCustomerDetails, toggleActivation, toggleUser } from '../../Utilities/api'
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
          if(result.data.length){
            setCustomer(result.data[0][0])
            setAccounts(result.data.map(([c, a]) => a))
          } else{
            const result = await getCustomerDetails(customerId)
            if(result){
              setCustomer(result.data)
            } else{
              toast.error("Error fetching customer details!")
            } 
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
        toast.error(e.response.data.message)
      }
    }

    const handleCustomerStatus = async (message) => {
      try{
        if(customer && customer.customerId){
          const response = await toggleUser(customer.customerId)
          toast.success(`Customer ${message}`)
          setToggled((prev) => !prev)
        }
      } catch(e){
        toast.error(e.response.data.message)
      }
    }

    const handleAddNewAccount = async (e) => {
      try {
        const response = await createAccount(customer.userName);
        if (response) {
          toast.success("Acount Created Successfully", { duration: 3000 })
          setToggled((prev) => !prev)
        }
      } catch (error) {
        console.log(error);
        toast.error(error.message, { duration: 3000 })
      }
    }

  return (
    <div className="mt-12 mx-8 md:h-1/3 p-16 grid grid-cols-1 md:grid-cols-2">
      <div className="max-w-[360px] p-6 py-8 pb-12 shadow-md md:max-w-[480px] rounded-2xl border text-gray-700 border-black-700">
        {customer && (
          <>
            <div className='flex flex-col items-center'>
                <p className='text-lg text-indigo-700 font-bold'>#{customer.customerId}</p>
              <p className="text-2xl font-bold tracking-tight text-gray-900">{customer.firstName}</p>
                  <p>@{customer.userName}</p>
                  <button className={`mt-2 rounded-md px-1.5 p-1 text-sm font-semibold leading-6 text-white shadow-sm  focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 ${customer.enabled ? 'bg-red-600 hover:bg-red-500 focus-visible:outline-red-600': 'bg-green-600 hover:bg-green-500 focus-visible:outline-green-600'}`} onClick={(e) => handleCustomerStatus(customer.enabled ? "disabled!" : "enabled!")}>{customer.enabled ? "Disable" : "Enable"}</button>
            </div>
            <div className='flex justify-between mt-4 px-8'>
              <div className="flex items-center text-sm">
                <PhoneIcon className="h-4 w-4 mr-[4px] flex-shrink-0 text-indigo-600"/>{customer.mobileNumber}
              </div>
              <div className="flex items-center text-sm">
                <CalendarDaysIcon className="h-4 w-4 mr-[4px] flex-shrink-0 text-indigo-600"/>{customer.dateOfBirth}
              </div>
            </div>
            <div className='flex flex-col gap-y-2 px-8 mt-4'>
            <p className="w-full mt-2 text-sm leading-8 border-b border-gray-200 text-gray-600">
              {customer.emailId}
            </p>
            <p className="w-full mt-2 text-sm leading-8 border-b border-gray-200 text-gray-600">
              {customer.aadhaarNumber}
            </p>
            <p className="w-full mt-2 text-sm leading-8 border-b border-gray-200 text-gray-600">
              {customer.permAddressLine1}
            </p>
            </div>
            <div className='flex mt-5 px-8 text-xs text-gray-500 justify-end'>
              <p className=''><span>Last Login: </span>{customer.lastLogin ? new Date(customer.lastLogin).toLocaleString() : "N/A"}</p>
            </div>
          </>
        )}
        
    </div>
    <div className="max-w-[360px] p-6 py-8 pb-12 shadow-md md:max-w-[480px] rounded-2xl border
text-gray-700 border-black-700">
      <p className="text-2xl px-7 mb-4 font-bold tracking-tight text-gray-900">Accounts</p>
      <div className="flex flex-col px-3 gap-y-4 overflow-y-auto max-h-[280px]">
        {accounts.length  ? accounts.map(({accountNumber, accountType, accountBalance, active}) => (
          <article key={accountNumber} className="flex border rounded-lg p-4 justify-between text-blue-500">
            <div className='flex flex-col text-indigo-600'>
              <Link className='flex gap-x-1' to={`/admin/viewAccount/${accountNumber}`}>
                  <span>
                  {accountNumber}
                  </span>
                  <ArrowTopRightOnSquareIcon className="h-5 w-5 flex-shrink-0 text-indigo-600" aria-hidden="true" /></Link>
             <p className="mt-2 text-gray-700 text-sm"> Account Balance: &#8377;{accountBalance}</p>
            </div>
            <div>
            <div className='flex flex-col w-[72px]'>
              <p className="p-1 px-1.5 bg-indigo-200 text-black rounded-md text-sm text-center">{accountType}</p>
              <button className={`mt-2 rounded-md px-1.5 p-1 text-sm font-semibold leading-6 text-white shadow-sm  focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 ${active ? 'bg-red-600 hover:bg-red-500 focus-visible:outline-red-600': 'bg-green-600 hover:bg-green-500 focus-visible:outline-green-600'}`} onClick={(e) => handleDisable(accountNumber, active ? "disabled" : "activated")}>{active ? "Disable" : "Activate"}</button>
            </div>
            </div>

        </article>
        )): <p className="mt-6 text-center text-gray-500 font-semibold">No records</p>}
      </div>
        <button type='button' onClick={handleAddNewAccount} className='rounded p-1 uppercase px-2 mt-3 block bg-slate-500 text-sm text-white ml-auto mr-0'>+ Add new account</button>
      </div>
  </div>
  )
}

export default CustomerDetails