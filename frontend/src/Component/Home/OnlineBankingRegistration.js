
import React, { useEffect } from 'react'
import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";
import { toast } from 'react-hot-toast';



const OnlineBankingRegistration = () => {

  const [inputs, setInputs] = useState({});
  const navigate = useNavigate()

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    const valid = (inputs.loginPassword === inputs.confirmLoginPassword) &&
      (inputs.transactionPassword === inputs.confirmTransactionPassword) &&
      (inputs.loginPassword != inputs.transactionPassword);

    if (!valid) {
      toast.error("Entered details are not valid", { duration: 2000 });
    }
    else {
      axios.put("http://localhost:8090/netBankingRegistration", {
        accountNumber: inputs.accountNumber,
        userName: inputs.userName,
        loginPassword: inputs.loginPassword,
        transactionPassword: inputs.transactionPassword,
        otp: inputs.otp,
      }
      ).then((response) => {
        console.log(response);
        toast.success("Welcome " + inputs.userName, { duration: 3000 });
        navigate("/login")
      }, (error) => {
        console.log(error);
        toast.error("Credentials Generation Failed!", { duration: 2000 })
      });
    }
  }

  useEffect(() => {
    if (sessionStorage.getItem("user")) navigate("/user");
  }, [])


  return (
    <div className='w-full flex flex-col'>
      <div className="flex flex-col w-2/5 mt-3 self-center">
        <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-blue-500 pb-2">Internet Banking</h2>
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
            <label className="text-lg my-2">User Name:
              <input
                type="text"
                name="userName"
                value={inputs.userName || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Set Login Password:
              <input
                type="password"
                name="loginPassword"
                value={inputs.loginPassword || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Confirm Login Password:
              <input
                type="password"
                name="confirmLoginPassword"
                value={inputs.confirmLoginPassword || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Set Transaction Password:
              <input
                type="password"
                name="transactionPassword"
                value={inputs.transactionPassword || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
              />
            </label>
            <label className="text-lg my-2">Confirm Transaction Password:
              <input
                type="password"
                name="confirmTransactionPassword"
                value={inputs.confirmTransactionPassword || ""}
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