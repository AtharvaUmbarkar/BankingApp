import React from 'react'
import { useParams } from 'react-router-dom'

const AccountDetails = () => {
  const { accountNumber } = useParams();
  return (
    <div>{accountNumber}</div>
  )
}

export default AccountDetails