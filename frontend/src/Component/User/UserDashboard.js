import React, { useContext, useEffect } from 'react'
import UserNavbar from './UserNavbar'
import { Outlet, useNavigate } from 'react-router-dom'
import { UserContext } from '../../Utilities/context/userContext';

const Dashboard = () => {
  const navigate = useNavigate();
  const {user} = useContext(UserContext);
  useEffect(() => {
    if (!user || user.isAdmin) navigate('/');
  }, [user])


  return (
    <div className='flex flex-col w-full'>
      <UserNavbar />
      <Outlet />
    </div>
  )
}

export default Dashboard