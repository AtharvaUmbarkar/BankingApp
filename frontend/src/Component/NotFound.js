import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { UserContext } from '../Utilities/context/userContext'

const NotFound = () => {

  const { user } = useContext(UserContext);

  return (
    <div className="grid place-items-center min-h-screen">
      <div className='flex flex-col items-center'>
        <div className='text-9xl font-bold text-indigo-700 my-4'>404</div>
        <div className='text-2xl my-t'>Oops! This page cannot be found</div>
        <div className='text-xl my-2 text-slate-400'>The page you are looking for may be moved elsewhere</div>
        <Link to={user.isAdmin ? "/admin": "/"} className='px-8 py-2 my-4 uppercase rounded bg-indigo-700 text-white'>Home</Link>
      </div>
    </div>
  )
}

export default NotFound