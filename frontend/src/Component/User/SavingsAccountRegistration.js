import React, { useState } from 'react'

const SavingsAccountRegistration = () => {
  const [personalDetails, setPersonaletails] = useState({
    title: "",
    firstName: "",
    middleName: "",
    lastName: "",
    fatherName: "",
    mobileNumber: "",
    emailId: "",
    aadharNumbar: "",
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
    addressLine12: "",
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

  const [debitCard, setDebitCard] = useState(false);
  const [onlineBanking, setOnlineBanking] = useState(false);
  const [agree, setAgree] = useState(false);

  return (
    <div className='w-full flex flex-col items-center'>
      <h1 className='text-2xl m-4 self-center'>Open a Savings Account</h1>
      <div className='px-4 my-2 w-full lg:w-2/3'>
        <h2 className='text-xl m-2'>Personal Details</h2>

      </div>
    </div>
  )
}

export default SavingsAccountRegistration