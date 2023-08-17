import React from 'react'
import AccountSidebar from './AccountSidebar'


const Account = () => {
  return (
    <div>
        <div className="w-full border-b-2 border-gray-200"></div>
        <br></br>
         Welcome {sessionStorage.getItem('user')}
        <br></br>
        <br></br>
        <AccountSidebar/>
    </div>
  )
}

export default Account