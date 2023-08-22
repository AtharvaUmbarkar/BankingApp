
import React, { useContext } from 'react'
import { useState, useEffect } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";
import { addBeneficiaryToCustomer } from '../../Utilities/api';
import { UserContext } from '../../Utilities/context/userContext';
import withAuthorization from '../../Utilities/context/withAuthorization';
import { LOGIN } from '../../Utilities/routes';
import { toast } from 'react-hot-toast';


const condition = (authUser) => !authUser // User not logged in -> Redirect to Login

export default withAuthorization(condition, LOGIN)(() => {
  const [inputs, setInputs] = useState({});
  const { user } = useContext(UserContext)

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await addBeneficiaryToCustomer(inputs, user.userName)
    if(response){
      toast.success("Beneficiary added!")
      setInputs({})
    }
  }

  return (
    <div className='w-full flex flex-col'>
      <div className="flex flex-col w-2/5 mt-8 self-center">
        <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-indigo-700 pb-4">Add payee</h2>
        <form onSubmit={handleSubmit}>
          <label className="text-base py-8">Account Number:
            <input
              type="text"
              name="accountNumber"
              value={inputs.accountNumber || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-indigo-700 text-base px-1 py-0.5 mb-1 mt-1"
            />
          </label>
          <label className="text-base py-8">Re-enter Account Number:
            <input
              type="text"
              name="reenterAccountNumber"
              value={inputs.reenterAccountNumber || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-indigo-700 text-base px-1 py-0.5 mb-1 mt-1"
            />
          </label>
          <label className="text-base py-8">Name:
            <input
              type="text"
              name="name"
              value={inputs.name || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-indigo-700 text-base px-1 py-0.5 mb-1 mt-1"
            />
          </label>
          <label className="text-base py-8">Nickname:
            <input
              type="text"
              name="nickname"
              value={inputs.nickname || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-indigo-700 text-base px-1 py-0.5 mb-1 mt-1"
            />
          </label>
          <div className='flex flex-row items-center mt-3 mb-1'>
            <label className="text-base mr-6 whitespace-nowrap flex-grow">Save Beneficiary?:</label>
            <input
              type="checkbox"
              name="save"
              value={inputs.save || false}
              onChange={handleChange}
              className="h-4 w-4"
            />
          </div>
          <input type="submit" value="Submit" className="self-center p-1.5 uppercase bg-blue-800 text-white my-4" />
        </form>
      </div>
    </div>
  )
})


