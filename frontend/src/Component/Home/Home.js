import React, { useContext, useEffect, useState } from 'react'
import { Link, Outlet } from 'react-router-dom'
import Navbar from './Navbar'
import Main from './Main'
import { toast } from 'react-hot-toast'
import { UserContext } from '../../Utilities/context/userContext'

const Home = () => {

    const { user } = useContext(UserContext)

    useEffect(() => {
        if(user) {
            setTimeout(() => {
                toast.success(`Welcome ${user.userName}`)
            }, 1000)
        }
    })

    return (
        <div className='bg-white'>
            <Navbar/>
            <Outlet/>
        </div>

    )
}

export default Home