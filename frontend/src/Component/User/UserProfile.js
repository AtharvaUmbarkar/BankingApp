import React from 'react'
import axios from "axios"


const userData = {
  title: "Miss",
  firstName: "Shradha",
  middleName: "Manoj",
  lastName: "Kumavat",
  fatherName: "Manoj",
  mobileNumber: "7058774805",
  emailId: "shradha.kumavat@cumminscollege.in",
  aadhaarNumber: "523412341234",
  dateOfBirth: "13/10/2001"
}



const UserProfile = () => {
  return (
    <div>
      <br></br>
      
      <br></br>
      <center>
      <div className=' h-50 w-50'>
      <span class="text-xl font-semibold inline-block py-1 px-5 rounded text-black-1000 bg-blue-50  last:mr-0 mr-1">
        Welcome {userData.firstName}
      </span>
      <br></br>
      <br></br>
      <span class="text-base font-semibold inline-block py-1 px-5 rounded text-black-1000 bg-gray-50  last:mr-0 mr-1">
        Email : {userData.emailId}<br></br>
        Phone : {userData.mobileNumber}<br></br>
        Aadhaar : {userData.aadhaarNumber}
      </span>
          
          
          
      </div>
      </center>
    </div>
  )
}

export default UserProfile