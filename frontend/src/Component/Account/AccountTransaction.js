import React, { useState } from 'react'
import { Outlet, useNavigate, Link } from 'react-router-dom'
import { Listbox } from '@headlessui/react'

const transactionTypes = [
  'imps',
  'neft',
  'rtgs',
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
      <div className='w-2/5'>
        <Listbox value={type} onChange={handleChange}>
          <Listbox.Button className='uppercase p-1 bg-blue-200 w-full'>{type}</Listbox.Button>
          <Listbox.Options>
            {transactionTypes.map((t, i) => (
              <Listbox.Option
                key={i}
                value={t}
                className='uppercase w-full bg-slate-200 p-1'
                // onClick={(e) => navigate("/account/transaction/" + e.target.value)}
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