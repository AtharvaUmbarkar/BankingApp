import React, { useContext } from 'react'
import axios from "axios"
import { UserContext } from '../../Utilities/context/userContext'

const UserProfile = () => {
  const { user } = useContext(UserContext)
  // console.log(user);
  return (
    <div className='w-full p-8'>
      <div className='w-1/3 flex flex-col mx-auto items-center'>
        <h1 className='font-semibold text-2xl mb-8'>User Details</h1>
        <div className='w-full'>
          {/* {Object.keys(user).map((key, i) => {
            return (
              <div key={key} className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
                <span className='w-1/2 font-semibold'>{key}:</span>
                <span className='flex-grow'>{user[key]}</span>
              </div>
            )
          })} */}
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Customer ID:</span>
            <span className='flex-grow'>{user.customerId}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Username:</span>
            <span className='flex-grow'>{user.userName}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Title:</span>
            <span className='flex-grow'>{user.title}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>First Name:</span>
            <span className='flex-grow'>{user.firstName}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Middle Name:</span>
            <span className='flex-grow'>{user.middleName}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Last Name:</span>
            <span className='flex-grow'>{user.lastName}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Father's Name:</span>
            <span className='flex-grow'>{user.fatherName}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Email ID:</span>
            <span className='flex-grow'>{user.emailId}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Mobile No.:</span>
            <span className='flex-grow'>{user.mobileNumber}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Aadhaar No.:</span>
            <span className='flex-grow'>{user.aadhaarNumber}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Date of Birth:</span>
            <span className='flex-grow'>{user.dateOfBirth}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Residential Add. Line 1:</span>
            <span className='flex-grow'>{user.tempAddressLine1}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Residential Add. Line 2:</span>
            <span className='flex-grow'>{user.tempAddressLine2}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Permanent Add. Line 1:</span>
            <span className='flex-grow'>{user.permAddressLine1}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Permanent Add. Line 2:</span>
            <span className='flex-grow'>{user.permAddressLine2}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Occupation:</span>
            <span className='flex-grow'>{user.occupation}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Source of Income:</span>
            <span className='flex-grow'>{user.sourceOfIncome}</span>
          </div>
          <div className='w-full flex flex-row items-center p-1 px-2 my-0.5 bg-slate-300 rounded'>
            <span className='w-1/2 font-semibold'>Gross annual Income:</span>
            <span className='flex-grow'>{user.grossAnnualIncome}</span>
          </div>

        </div>
      </div>
    </div>
  )
}

export default UserProfile