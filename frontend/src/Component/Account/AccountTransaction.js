import React, { useState } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'
import { Listbox } from '@headlessui/react'
import { ChevronUpDownIcon } from '@heroicons/react/24/outline'

const transactionTypes = [
  'imps',
  'neft',
  'rtgs',
  'withdraw',
  'deposit',
]

const AccountTransaction = () => {
  const [type, setType] = useState(transactionTypes[0]);
  const navigate = useNavigate();

  const handleChange = (t) => {
    navigate(t)
    setType(t);
  }

  return (
    <div className='w-full flex flex-col items-center'>
      <div className='w-2/5 px-4'>
        <Listbox as={'div'} className='relative w-full mt-8' value={type} onChange={handleChange}>
          <Listbox.Button className='uppercase relative w-full cursor-default rounded-md py-1.5 pl-3 pr-10 text-center text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 sm:text-l font-bold sm:leading-6'>
              <span className="ml-3 block truncate">{type}</span>
              <span className="pointer-events-none absolute inset-y-0 right-0 ml-3 flex items-center pr-2">
                <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
              </span>
            </Listbox.Button>
          <Listbox.Options className='absolute w-full text-center'>
            {transactionTypes.map((t, i) => (
              <Listbox.Option
                key={i}
                value={t}
                className='uppercase w-full bg-slate-200 p-1 cursor-pointer'
              >
                {t}
              </Listbox.Option>
            ))}
          </Listbox.Options>
        </Listbox>
        <Outlet />
      </div>
    </div>
  )
}

export default AccountTransaction