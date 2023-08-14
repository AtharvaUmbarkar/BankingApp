import React, { useState } from 'react'

const SavingsAccountRegistration = () => {
  const [personalDetails, setPersonalDetails] = useState({
    title: "",
    firstName: "",
    middleName: "",
    lastName: "",
    fatherName: "",
    mobileNumber: "",
    emailId: "",
    aadharNumber: "",
    dateOfBirth: "",
  })

  const [residentialAddress, setResidentialAddress] = useState({
    addressLine1: "",
    addressLine12: "",
    landmark: "",
    state: "",
    city: "",
    pincode: "",
  })

  const [permanentAddress, setPermanentAddress] = useState({
    addressLine1: "",
    addressLine2: "",
    landmark: "",
    state: "",
    city: "",
    pincode: "",
  })

  const [occupationDetails, setOccupationDetails] = useState({
    occupationType: "",
    sourceOfIncome: "",
    grossAnnualIncome: "",
  })

  const [sameAddress, setSameAddress] = useState(false);
  const [debitCard, setDebitCard] = useState(false);
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
    const accountDetails = {
      personalDetails,
      residentialAddress,
      permanentAddress: sameAddress ? residentialAddress : permanentAddress,
      occupationDetails,
      debitCard,
      onlineBanking,
      agree,
    }
  }

  return (
    <form onSubmit={handleSubmit} className='w-full flex flex-col items-center'>
      <h1 className='text-2xl m-4 self-center'>Open a Savings Account</h1>
      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Personal Details</h2>

        <label className=" my-2">Title:
          <input
            type="text"
            name="title"
            value={personalDetails.title}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Firstname:
          <input
            type="text"
            name="firstName"
            value={personalDetails.firstName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Middlename:
          <input
            type="text"
            name="middleName"
            value={personalDetails.middleName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Lastname:
          <input
            type="text"
            name="lastName"
            value={personalDetails.lastName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Father's Name:
          <input
            type="text"
            name="fatherName"
            value={personalDetails.fatherName}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Mobile Number:
          <input
            type="text"
            name="mobileNumber"
            value={personalDetails.mobileNumber}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Email Id:
          <input
            type="text"
            name="emailId"
            value={personalDetails.emailId}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Aadhar Number:
          <input
            type="text"
            name="aadharNumber"
            value={personalDetails.aadharNumber}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Date of Birth:
          <input
            type='date'
            name="dateOfBirth"
            value={personalDetails.dateOfBirth}
            onChange={handlePersonalDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>
      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Residential Address</h2>

        <label className=" my-2">Address Line 1:
          <input
            type="text"
            name="addressLine1"
            value={residentialAddress.addressLine1}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Address Line 2:
          <input
            type="text"
            name="addressLine2"
            value={residentialAddress.addressLine2}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Landmark:
          <input
            type="text"
            name="landmark"
            value={residentialAddress.landmark}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">State:
          <input
            type="text"
            name="state"
            value={residentialAddress.state}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">City:
          <input
            type="text"
            name="city"
            value={residentialAddress.city}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Pincode:
          <input
            type="text"
            name="pincode"
            value={residentialAddress.pincode}
            onChange={handleResidentialAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Permanent Address</h2>

        <label className=" my-2">Same as Residential:
          <input
            type="checkbox"
            name="sameAddress"
            value={sameAddress}
            onChange={() => setSameAddress(!sameAddress)}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Address Line 1:
          <input
            disabled={sameAddress}
            type="text"
            name="addressLine1"
            value={permanentAddress.addressLine1}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Address Line 2:
          <input
            disabled={sameAddress}
            type="text"
            name="addressLine2"
            value={permanentAddress.addressLine2}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Landmark:
          <input
            disabled={sameAddress}
            type="text"
            name="landmark"
            value={permanentAddress.landmark}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">State:
          <input
            disabled={sameAddress}
            type="text"
            name="state"
            value={permanentAddress.state}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">City:
          <input
            disabled={sameAddress}
            type="text"
            name="city"
            value={permanentAddress.city}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Pincode:
          <input
            disabled={sameAddress}
            type="text"
            name="pincode"
            value={permanentAddress.pincode}
            onChange={handlePermanentAddressChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Occupation Details</h2>

        <label className=" my-2">Occupation Type:
          <input
            type="text"
            name="occupationType"
            value={occupationDetails.occupationType}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Source of Income:
          <input
            type="text"
            name="sourceOfIncome"
            value={occupationDetails.sourceOfIncome}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Gross Annual Income:
          <input
            type="text"
            name="grossAnnualIncome"
            value={occupationDetails.grossAnnualIncome}
            onChange={handleOccupationDetailsChange}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>
      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <h2 className='text-xl mb-3 border-b border-blue-500 font-semibold'>Additional Details</h2>

        <label className=" my-2">Want Debit Card:
          <input
            type="checkbox"
            name="debitCard"
            value={debitCard}
            onChange={() => setDebitCard(!debitCard)}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Activate Online Banking:
          <input
            type="checkbox"
            name="onlineBanking"
            value={onlineBanking}
            onChange={() => setOnlineBanking(!onlineBanking)}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

        <label className=" my-2">Do you agree to our terms and conditions:
          <input
            type="checkbox"
            name="agree"
            value={agree}
            onChange={() => setAgree(!agree)}
            className="border border-slate-500 focus-within:border-blue-500 p-1 mt-1 mb-3"
          />
        </label>

      </div>

      <div className='px-4 my-2 w-full lg:w-1/3'>
        <button type='submit' className='p-2 my-4 w-full bg-blue-500 text-xl text-white rounded-sm'>SUBMIT</button>
      </div>

    </form>
  )
}

export default SavingsAccountRegistration