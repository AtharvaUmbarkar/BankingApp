
import React from 'react'
import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";



const OnlineBankingRegistration = () => {

  const [inputs, setInputs] = useState({});
  const navigate = useNavigate()

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {

  }

  return (
    <div className='w-full flex flex-col'>
      <div className="flex flex-col w-2/5 mt-8 self-center">
        <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-blue-500 pb-2">Internet Banking</h2>
        <form onSubmit={handleSubmit} className=''>

          <div className=''>

            <label className="text-lg my-2">Account Number:
              <input
                type="text"
                name="account_no"
                value={inputs.account_no || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Set Login Password:
              <input
                type="password"
                name="login_password"
                value={inputs.login_password || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Confirm Login Password:
              <input
                type="password"
                name="confirm_login_password"
                value={inputs.confirm_login_password || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Set Transaction Password:
              <input
                type="password"
                name="transaction_password"
                value={inputs.transaction_password || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Confirm Transaction Password:
              <input
                type="password"
                name="confirm_transaction_password"
                value={inputs.confirm_transaction_password || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Enter OTP:
              <input
                type="otp"
                name="otp"
                value={inputs.otp || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
          </div>
          <input type="submit" value="Submit" className="self-center p-2 uppercase bg-blue-800 text-white my-4" />
        </form>
      </div>
    </div>
  )
}

export default OnlineBankingRegistration