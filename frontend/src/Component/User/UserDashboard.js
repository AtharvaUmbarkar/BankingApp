import React, { useEffect } from 'react'
import UserNavbar from './UserNavbar'
import { Outlet, useNavigate } from 'react-router-dom'

const Dashboard = () => {
  const navigate = useNavigate();
  const user = sessionStorage.getItem('user');
  useEffect(() => {
    if (!user) navigate('/');
  }, [user])


  return (
    <div className='flex flex-col w-full'>
      <UserNavbar />
      <Outlet />
    </div>
  )
}

export default Dashboard