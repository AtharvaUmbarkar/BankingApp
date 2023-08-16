import React from 'react'
import AccountSidebar from './AccountSidebar'


const Account = () => {
  return (
    <div> Welcome to your account {sessionStorage.getItem('user')}
        <AccountSidebar/>
    </div>
  )
}

export default Account