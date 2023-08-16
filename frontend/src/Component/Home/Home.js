import React, { useEffect, useState } from 'react'
import { Link, Outlet } from 'react-router-dom'
import Navbar from './Navbar'
import Main from './Main'
import { toast } from 'react-hot-toast'

const Home = () => {


    useEffect(() => {
        let user = sessionStorage.getItem("user")
        if(user) {
            user = JSON.parse(user)
            setTimeout(() => {
                toast.success(`Welcome ${user.username}`)
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