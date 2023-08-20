import logo from './logo.svg';
import './App.css';
import { useState } from "react"
import axios from "axios"
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import Registration from './Component/Registration';
import Login from './Component/Login';
import Home from './Component/Home/Home';
import Navbar from './Component/Home/Navbar';
import UserDashboard from './Component/User/UserDashboard'
import { Toaster } from 'react-hot-toast';
import Logout from './Component/Logout';
import UserProfile from './Component/User/UserProfile';
import SavingsAccountRegistration from './Component/Home/SavingsAccountRegistration';
import OnlineBankingRegistration from './Component/Home/OnlineBankingRegistration';
import Main from './Component/Home/Main';
import AccountDashboard from './Component/Account/AccountDashboard';
import AccountDetails from './Component/Account/AccountDetails';
import AccountStatement from './Component/Account/AccountStatement';
import AccountTransaction from './Component/Account/AccountTransaction';
import IMPS from './Component/Account/Transaction/IMPS';
import NEFT from './Component/Account/Transaction/NEFT'
import RTGS from './Component/Account/Transaction/RTGS'
import Account from './Component/User/UserAccounts';
import AddBeneficiary from './Component/Beneficiary/AddBeneficiary';
import Beneficiaries from './Component/Beneficiary/Beneficiaries';
import Withdraw from './Component/Account/Transaction/Withdraw';
import Deposit from './Component/Account/Transaction/Deposit';
import { UserContextProvider } from './Utilities/context/userContext';
import UserAccounts from './Component/User/UserAccounts';
import AccountTransactionHistory from './Component/Account/AccountTransactionHistory';

function App() {
  /*const [inputs, setInputs] = useState({});

 const handleChange = (event) => {
   const name = event.target.name;
   const value = event.target.value;
   setInputs(values => ({...values, [name]: value}))
 }

 const handleSubmit = async (event) => {
   console.log(inputs)
   event.preventDefault();
   if(Object.keys(inputs).length == 4){
     let allFieldsEntered = true;
     Object.keys(inputs).map(field => {
       if(!inputs[field]){
         allFieldsEntered = false;
       }
     })
     if(!allFieldsEntered){
       return alert("All fields are mandatory!");
     }
     else{
       alert(JSON.stringify(inputs));
       console.log(inputs)
       inputs["customer_phone"] = parseInt(inputs[["customer_phone"]])
       inputs["customer_aadhar"] = parseInt(inputs[["customer_aadhar"]])
         const res = await axios.post("http://localhost:8090/saveCustomer", JSON.stringify(inputs), {headers: {"Content-Type": "application/json"}})
         if(res){
             alert(JSON.stringify(res));
           }
       setInputs({})
    }
   } else{
     return alert("All fields are mandatory!");
   }
 }
*/
  return (
  <UserContextProvider>   
    <BrowserRouter>
      <Toaster />
      <Routes>
        <Route path="/" element={<Home />}>
          <Route index exact path="/" element={<Main />} />
          <Route exact path="login" element={<Login />} />
          <Route path='savings-account-registration' element={<SavingsAccountRegistration />} />
          <Route path='online-banking-registration' element={<OnlineBankingRegistration />} />
        </Route>

        <Route exact path="/logout" element={<Logout />} />

        <Route path="/user" element={<UserDashboard />}>
          <Route index element={<Navigate to='profile' />} />
          <Route path='profile' index element={<UserProfile />} />
          <Route path='account' index element={<UserAccounts />} />
          <Route path='addBeneficiary' element={<AddBeneficiary />} />
          <Route path='beneficiaries' element={<Beneficiaries />} />
        </Route>

        <Route path='/account/:accountNumber' element={<AccountDashboard />}>
          <Route index element={<Navigate to='details' />} />
          <Route path='details' element={<AccountDetails />} />
          <Route path='statement' element={<AccountStatement />} />
          <Route path='transactionHistory' element={<AccountTransactionHistory />} />
          <Route path='transaction' element={<AccountTransaction />} >
            <Route index element={<Navigate to='imps' />} />
            <Route path='imps' element={<IMPS />} />
            <Route path='neft' element={<NEFT />} />
            <Route path='rtgs' element={<RTGS />} />
            <Route path='withdraw' element={<Withdraw />} />
            <Route path='deposit' element={<Deposit />} />
          </Route>
        </Route>

      </Routes>
    </BrowserRouter>
  </UserContextProvider>
  )
}

export default App;