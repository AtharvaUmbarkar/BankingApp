import React, { useState } from 'react'

const AccountStatement = () => {

const [fromDate, setFromDate] = useState();
const [toDate, setToDate] = useState();
const handleSubmit = () => {

}
const changeFromDate = (event) => {
  setFromDate(event.target.value)
}
const changeToDate = (event) => {
  setToDate(event.target.value)
}

  return (
    <form onSubmit={handleSubmit} className='w-full flex flex-col items-center'>
    <div className='px-4 my-2 w-full lg:w-1/3'>
      <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Check Account Statement</h2>
      <label className=" my-2">From:
          <input
            type='date'
            name="From"
            value={fromDate}
            onChange={changeFromDate}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">From:
          <input
            type='date'
            name="To"
            value={toDate}
            onChange={changeToDate}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <div className='px-4 my-2 w-full lg:w-1/3'>
        <button type='submit' className='p-2 my-4 w-full bg-blue-500 text-xl text-white rounded-sm'>SUBMIT</button>
      </div>
    </div>
    </form>

  )
}

export default AccountStatement