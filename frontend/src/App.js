import logo from './logo.svg';
import './App.css';
import { useState } from "react"
import axios from "axios"
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Registration from './Component/Registration';
import Login from './Component/Login';
import Home from './Component/Home/Home';
import Navbar from './Component/Home/Navbar';

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
    <div>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/registration" element={<Registration />} />
          <Route exact path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App;
