import React, { useContext } from 'react'
// import { UserContext } from '../../Utilities/context/userContext'
import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";
import { toast } from 'react-hot-toast';

const passwordTypes = ["Login", "Transactional"]

const ForgotPassword = () => {
  // const { user } = useContext(UserContext)
  const [inputs, setInputs] = useState({});
  const navigate = useNavigate()

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    const valid = (inputs.newPassword === inputs.confirmNewPassword)

    if (!valid) {
      toast.error("Entered details are not valid", { duration: 3000 });
    }
    else {
      axios.put("http://localhost:8090/forgotPassword",
        {
          userName: inputs.userName,
          passwordType: inputs.passwordType,
          newPassword: inputs.newPassword,
          otp: inputs.otp,
        },
        {
          headers: { "Content-Type": "application/json" }
        }
      ).then((response) => {
        // console.log(response);
        toast.success("Password changed successfully", { duration: 3000 });
        navigate("/login")
      }, (error) => {
        // console.log("Failure.." + error);
        toast.error("Failed to change password", { duration: 3000 })
      });
    }
  }

  return (
    <div className='w-full flex flex-col'>
      <div className="flex flex-col w-1/3 mt-3 self-center">
        <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-indigo-700 pb-2">Forgot/Change Password</h2>
        <form onSubmit={handleSubmit} className=''>

          <div className='flex flex-col w-full'>
            <label className="my-1 text-sm w-full">User Name:
              <input
                type="text"
                required
                minLength={8}
                maxLength={30}
                name="userName"
                value={inputs.userName || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-indigo-700 text-sm p-1 mt-1"
              />
            </label>

            <label className="my-1 text-sm w-full">Password Type:
              <select
                required
                name="passwordType"
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-1 w-full"
              >
                <option className='w-full' value={""} ></option>
                {passwordTypes.map((b, i) => {
                  return <option key={i} className='w-full' value={b} >{b}</option>
                })}
              </select>
            </label>

            <label className="my-1 text-sm w-full">Set New Password:
              <input
                required
                minLength={8}
                maxLength={16}
                type="password"
                name="newPassword"
                value={inputs.newPassword || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-indigo-700 text-sm p-1 mt-1"
              />
            </label>
            <label className="my-1 text-sm w-full">Confirm New Password:
              <input
                required
                minLength={8}
                maxLength={16}
                type="password"
                name="confirmNewPassword"
                value={inputs.confirmNewPassword || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-indigo-700 text-sm p-1 mt-1"
              />
            </label>
            <label className="my-1 text-sm w-full">Enter OTP:
              <input
                type="otp"
                name="otp"
                value={inputs.otp || ""}
                onChange={handleChange}
                className="border border-slate-500 focus-within:border-indigo-700 text-sm p-1 mt-1"
              />
            </label>
          </div>
          <input type="submit" value="Submit" className="self-center p-2 uppercase bg-blue-800 text-white my-4" />
        </form>
      </div>
    </div>
  )
}

export default ForgotPassword