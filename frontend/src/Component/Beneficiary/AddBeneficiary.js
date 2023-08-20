
import React, { useContext } from 'react'
import { useState, useEffect } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";
import { addBeneficiaryToCustomer } from '../../Utilities/api';
import { UserContext } from '../../Utilities/context/userContext';
import withAuthorization from '../../Utilities/context/withAuthorization';
import { LOGIN } from '../../Utilities/routes';


const condition = (authUser) => !authUser // User not logged in -> Redirect to Login

export default withAuthorization(condition, LOGIN)(() => {
  const [inputs, setInputs] = useState({});
  const { username } = useContext(UserContext)

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {
    await addBeneficiaryToCustomer()
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
})


