import React from 'react'
import { Outlet, useLocation } from 'react-router-dom'
import AccountNavbar from './AccountNavbar'
import { HOME } from '../../Utilities/routes'
import withAuthorization from '../../Utilities/context/withAuthorization'

const condition = (user) => !user || user.isAdmin

const pathMap = {
  "details": "Details",
  "statement": "Statement",
  "transactionHistory": "Transaction History",
}

export default withAuthorization(condition, HOME)(() => {
  const { pathname } = useLocation()

  return (
    <div className='flex flex-col w-full'>
      <AccountNavbar active={pathMap[pathname.split("/").reverse()[0]] ?? "Transaction"} />
      <Outlet/>
    </div>
  )
})
