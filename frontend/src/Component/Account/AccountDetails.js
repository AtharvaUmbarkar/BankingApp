import React from 'react'
import { useParams } from 'react-router-dom'

const AccountDetails = () => {
  const { accountNumber } = useParams();
  alert("Inside account : "+accountNumber);
  return (
    <div>{accountNumber}</div>
  )
}

export default AccountDetails