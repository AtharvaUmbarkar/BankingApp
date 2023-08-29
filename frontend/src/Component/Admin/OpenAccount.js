import React, { useState } from 'react'
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import { toast } from 'react-hot-toast';

const CUSTOMER_DATA_URL = "http://localhost:8090/createFirstAccount";

const OpenAccount = () => {
  const navigate = useNavigate()
  const [personalDetails, setPersonalDetails] = useState({
    title: "",
    firstName: "",
    middleName: "",
    lastName: "",
    fatherName: "",
    mobileNumber: "",
    emailId: "",
    aadhaarNumber: "",
    dateOfBirth: "",
  })

  const [residentialAddress, setResidentialAddress] = useState({
    tempAddressLine1: "",
    tempAddressLine2: "",
    tempLandmark: "",
    tempState: "",
    tempCity: "",
    tempPincode: "",
  })

  const [permanentAddress, setPermanentAddress] = useState({
    permAddressLine1: "",
    permAddressLine2: "",
    permLandmark: "",
    permState: "",
    permCity: "",
    permPincode: "",
  })

  const [occupationDetails, setOccupationDetails] = useState({
    occupation: "",
    sourceOfIncome: "",
    grossAnnualIncome: "",
  })

  const [sameAddress, setSameAddress] = useState(false);
  const [debitCardAvailed, setDebitCardAvailed] = useState(false);
  const [onlineBanking, setOnlineBanking] = useState(false);
  const [agree, setAgree] = useState(false);

  const handlePersonalDetailsChange = (e) => {
    setPersonalDetails((personalDetails) => ({ ...personalDetails, [e.target.name]: e.target.value }))
  }
  const handleResidentialAddressChange = (e) => {
    setResidentialAddress((residentialAddress) => ({ ...residentialAddress, [e.target.name]: e.target.value }))
  }
  const handlePermanentAddressChange = (e) => {
    setPermanentAddress((permanentAddress) => ({ ...permanentAddress, [e.target.name]: e.target.value }))
  }
  const handleOccupationDetailsChange = (e) => {
    setOccupationDetails((occupationDetails) => ({ ...occupationDetails, [e.target.name]: e.target.value }))
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const permAddress = {
      permAddressLine1: sameAddress ? residentialAddress.tempAddressLine1 : permanentAddress.permAddressLine1,
      permAddressLine2: sameAddress ? residentialAddress.tempAddressLine2 : permanentAddress.permAddressLine2,
      permLandmark: sameAddress ? residentialAddress.tempLandmark : permanentAddress.permLandmark,
      permState: sameAddress ? residentialAddress.tempState : permanentAddress.permState,
      permCity: sameAddress ? residentialAddress.tempCity : permanentAddress.permCity,
      permPincode: sameAddress ? residentialAddress.tempPincode : permanentAddress.permPincode,
    }
    const accountDetails = {
      customer: {
        ...personalDetails,
        ...residentialAddress,
        ...permAddress,
        ...occupationDetails,
      },
      account: {
        debitCardAvailed,
        netBankingOpted: onlineBanking,
        accountType: "Savings",
      }
    }

    axios.post(CUSTOMER_DATA_URL, accountDetails
    ).then((response) => {
      console.log(response);
      toast.success("Account created successfully!",{ duration: 3000 });
    }, (error) => {
      console.log(error);
      toast.error("Account Creation Failed", { duration: 2000 });
    });

  }

  return (
    <form onSubmit={handleSubmit} className='w-full flex flex-col items-center'>
      <h1 className='text-2xl m-4 mt-6 self-center'>Open a Savings Account</h1>
      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold pb-2'>Personal Details</h2>

        <label className="text-base my-2">Title:
          <input
            required
            type="text"
            name="title"
            value={personalDetails.title}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Firstname:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="firstName"
            value={personalDetails.firstName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Middlename:
          <input
            minLength={3}
            maxLength={30}
            type="text"
            name="middleName"
            value={personalDetails.middleName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Lastname:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="lastName"
            value={personalDetails.lastName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Father's Name:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="fatherName"
            value={personalDetails.fatherName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Mobile Number:
          <input
            required
            minLength={10}
            maxLength={10}
            pattern='^\d{10}$'
            type="text"
            name="mobileNumber"
            value={personalDetails.mobileNumber}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Email Id:
          <input
            type="email"
            name="emailId"
            value={personalDetails.emailId}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Aadhaar Number:
          <input
            required
            minLength={12}
            maxLength={12}
            pattern='^[0-9]{12}$'
            type="text"
            name="aadhaarNumber"
            value={personalDetails.aadhaarNumber}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Date of Birth:
          <input
            required
            type='date'
            name="dateOfBirth"
            value={personalDetails.dateOfBirth}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>
      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold pb-2'>Residential Address</h2>

        <label className="text-base my-2">Address Line 1:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="tempAddressLine1"
            value={residentialAddress.tempAddressLine1}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Address Line 2:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="tempAddressLine2"
            value={residentialAddress.tempAddressLine2}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Landmark:
          <input
            minLength={3}
            maxLength={30}
            type="text"
            name="tempLandmark"
            value={residentialAddress.tempLandmark}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">State:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="tempState"
            value={residentialAddress.tempState}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">City:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="tempCity"
            value={residentialAddress.tempCity}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Pincode:
          <input
            required
            maxLength={6}
            pattern='^[0-9]{6}$'
            type="text"
            name="tempPincode"
            value={residentialAddress.tempPincode}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold pb-2'>Permanent Address</h2>
        <div className='flex flex-row items-center mt-4 mb-2'>
          <label className="text-base mr-6 whitespace-nowrap flex-grow">Same as Residential:</label>
          <input
            type="checkbox"
            name="sameAddress"
            value={sameAddress}
            onChange={() => setSameAddress(!sameAddress)}
            className="h-4 w-4"
          />
        </div>

        <label className="text-base my-2">Address Line 1:
          <input
            disabled={sameAddress}
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="permAddressLine1"
            value={permanentAddress.permAddressLine1}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Address Line 2:
          <input
            disabled={sameAddress}
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="permAddressLine2"
            value={permanentAddress.permAddressLine2}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Landmark:
          <input
            disabled={sameAddress}
            minLength={3}
            maxLength={30}
            type="text"
            name="permLandmark"
            value={permanentAddress.permLandmark}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">State:
          <input
            disabled={sameAddress}
            type="text"
            name="permState"
            value={permanentAddress.permState}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">City:
          <input
            disabled={sameAddress}
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="permCity"
            value={permanentAddress.permCity}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Pincode:
          <input
            disabled={sameAddress}
            required
            maxLength={6}
            pattern='^[0-9]{6}$'
            type="text"
            name="permPincode"
            value={permanentAddress.permPincode}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold pb-2'>Occupation Details</h2>

        <label className="text-base my-2">Occupation Type:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="occupation"
            value={occupationDetails.occupation}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Source of Income:
          <input
            required
            minLength={3}
            maxLength={30}
            type="text"
            name="sourceOfIncome"
            value={occupationDetails.sourceOfIncome}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>

        <label className="text-base my-2">Gross Annual Income:
          <input
            required
            type="text"
            name="grossAnnualIncome"
            value={occupationDetails.grossAnnualIncome}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-indigo-700 p-1 mt-1 mb-3"
          />
        </label>
      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-indigo-700 font-semibold pb-2'>Additional Details</h2>
        <div className='flex flex-row items-center mt-4 mb-2'>
          <label className="text-base mr-6 whitespace-nowrap flex-grow">Want Debit Card:</label>
          <input
            type="checkbox"
            name="debitCardAvailed"
            value={debitCardAvailed}
            onChange={() => setDebitCardAvailed(!debitCardAvailed)}
            className="h-4 w-4"
          />
        </div>
        <div className='flex flex-row items-center mt-4 mb-2'>
          <label className="text-base mr-6 whitespace-nowrap flex-grow">Activate Online Banking:</label>
          <input
            type="checkbox"
            name="onlineBanking"
            value={onlineBanking}
            onChange={() => setOnlineBanking(!onlineBanking)}
            className="h-4 w-4"
          />
        </div>
        <div className='flex flex-row items-center mt-4 mb-2'>
          <label className="text-base mr-6 whitespace-nowrap flex-grow">Do you agree to our terms and conditions:</label>
          <input
            type="checkbox"
            name="agree"
            value={agree}
            onChange={() => setAgree(!agree)}
            className="h-4 w-4"
          />
        </div>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <button type='submit' disabled={!agree} className={(agree ? "bg-indigo-700" : "bg-slate-500") + ' p-2 my-4 w-full text-xl text-white rounded-sm'}>SUBMIT</button>
      </div>

    </form>
  )
}

export default OpenAccount