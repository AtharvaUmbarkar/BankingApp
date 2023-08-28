import React, { useContext, useEffect } from 'react'
import UserNavbar from './UserNavbar'
import { Outlet, useLocation, useNavigate } from 'react-router-dom'
import { UserContext } from '../../Utilities/context/userContext';

const pathMap = {
  "profile": "Profile",
  "account": "Accounts",
  "beneficiaries": "Beneficiaries",
  "addBeneficiary": "Add Beneficiary",
}

const Dashboard = () => {
  const navigate = useNavigate();
  const { pathname } = useLocation()
  const {user} = useContext(UserContext);
  useEffect(() => {
    if (!user || user.isAdmin) navigate('/');
  }, [user])


  return (
    <div className='flex flex-col w-full'>
      <UserNavbar active={pathMap[pathname.split("/").reverse()[0]]} />
      <Outlet />
    </div>
  )
}

export default Dashboard