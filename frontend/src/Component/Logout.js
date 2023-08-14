import React from 'react'
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        sessionStorage.removeItem('user');
        navigate('/');
    }

    return (
        <div className='grid place-content-center'>
            <h1 className='text-2xl my-4'>Are you sure you want to Logout</h1>
            <button type='button' className='p-2 uppercase bg-blue-500 text-white' onClick={handleLogout}>Logout</button>
        </div>
    )
}

export default Logout