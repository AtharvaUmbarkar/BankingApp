import React from 'react'
import { Outlet } from 'react-router-dom'
import AccountNavbar from './AccountNavbar'

const AccountDashboard = () => {
  return (
    <div className='flex flex-col w-full'>
      <AccountNavbar />
      <Outlet/>
    </div>
  )
}

export default AccountDashboard