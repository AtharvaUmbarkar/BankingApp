import React from 'react'
import { Link } from 'react-router-dom'
import Navbar from './Navbar'

const Home = () => {
    return (
        <div className='w-full flex flex-col min-h-screen'>
            
            <ul className='w-2/5 mt-4 flex flex-col items-center self-center text-xl'>
                <Link className='w-4/5 py-2 my-2 px-2 bg-blue-900 text-white text-center' to='/login'>Login</Link>
                <Link className='w-4/5 py-2 my-2 px-2 bg-blue-900 text-white text-center' to='/registration'>Register</Link>
                <Link className='w-4/5 py-2 my-2 px-2 bg-blue-900 text-white text-center' to='/apply'>Apply for an account</Link>
            </ul>
        </div>
    )
}

export default Home