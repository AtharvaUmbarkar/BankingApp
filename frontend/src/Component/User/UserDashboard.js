import React from 'react'
import UserNavbar from './UserNavbar'
import { Outlet } from 'react-router-dom'

const Dashboard = () => {
  return (
    <div className='flex flex-col w-full'>
      <UserNavbar />
      <Outlet/>
    </div>
  )
}

export default Dashboard