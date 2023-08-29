import React, { useContext, useEffect, useState } from 'react'
import { Link, Outlet } from 'react-router-dom'
import Navbar from './Navbar'
import Main from './Main'
import { toast } from 'react-hot-toast'
import { UserContext } from '../../Utilities/context/userContext'
import withAuthorization from '../../Utilities/context/withAuthorization'
import { ADMIN_HOME } from '../../Utilities/routes'


const condition = (authUser) => authUser && authUser.isAdmin

export default withAuthorization (condition, ADMIN_HOME)(() => {

    return (
        <div className='bg-white'>
            <Navbar/>
            <Outlet/>
        </div>

    )
})