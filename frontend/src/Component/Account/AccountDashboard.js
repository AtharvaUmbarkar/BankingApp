import React from 'react'
import { Outlet } from 'react-router-dom'
import AccountNavbar from './AccountNavbar'
import { HOME } from '../../Utilities/routes'
import withAuthorization from '../../Utilities/context/withAuthorization'

const condition = (user) => !user || user.isAdmin

export default withAuthorization(condition, HOME)(() => {
  return (
    <div className='flex flex-col w-full'>
      <AccountNavbar />
      <Outlet/>
    </div>
  )
})
