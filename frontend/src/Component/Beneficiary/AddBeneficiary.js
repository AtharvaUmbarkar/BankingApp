
import React from 'react'
import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";



const AddBeneficiary = () => {

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
        <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-blue-500 pb-2">Add payee</h2>
        <form onSubmit={handleSubmit} className=''>

          <div className=''>

            <label className="text-lg my-2">Account Number:
              <input
                type="text"
                name="accountNumber"
                value={inputs.accountNumber || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Re-enter Account Number:
              <input
                type="text"
                name="reenterAccountNumber"
                value={inputs.reenterAccountNumber || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Name:
              <input
                type="text"
                name="name"
                value={inputs.name || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Nickname:
              <input
                type="text"
                name="nickname"
                value={inputs.nickname || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Save Beneficiary?:
              <input
                type="checkbox"
                name="save"
                value={inputs.save || false}
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

export default AddBeneficiary