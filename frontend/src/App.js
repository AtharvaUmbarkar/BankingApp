import logo from './logo.svg';
import './App.css';
import {useState} from "react"
import axios from "axios"

function App() {
   const [inputs, setInputs] = useState({});

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({...values, [name]: value}))
  }

  const handleSubmit = async (event) => {
    console.log(inputs)
    event.preventDefault();
    if(Object.keys(inputs).length == 9){
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
        //   const res = await axios.post("http://localhost:8080/createCustomer", inputs)
        //   if(res){
          //     alert(JSON.stringify(res));
          //   }
        setInputs({})
     }
    } else{
      return alert("All fields are mandatory!");
    }
  }

  return (
    <div className="personal-details">
      <form onSubmit={handleSubmit} className='user-form'>
      <h2>Personal details:</h2>
     
        <div className='user-form-inputs'>
    
      <label>Title:
        <input 
          type="text" 
          name="title" 
          value={inputs.title || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
        <div className='user-form-inputs'>
    
      <label>First name:
        <input 
          type="text" 
          name="firstName" 
          value={inputs.firstName || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
        <div className='user-form-inputs'>
    
      <label>Middle Name:
        <input 
          type="text" 
          name="middleName" 
          value={inputs.middleName || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
        <div className='user-form-inputs'>
    
      <label>Last Name:
        <input 
          type="text" 
          name="lastName" 
          value={inputs.lastName || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
    <div className='user-form-inputs'>

     <label>Father's name:
      <input 
        type="text" 
        name="fathersName" 
        value={inputs.fathersName || ""} 
        onChange={handleChange}
      />
      </label>    
    </div>
    <div className='user-form-inputs'>

     <label>Mobile Number:
      <input 
        type="number" 
        name="mobileNumber" 
        value={inputs.mobileNumber || ""} 
        onChange={handleChange}
      />
      </label>    
    </div>
    <div className='user-form-inputs'>

     <label>Email ID:
      <input 
        type="text" 
        name="emailId" 
        value={inputs.emailId || ""} 
        onChange={handleChange}
      />
      </label>    
    </div>

      <div className='user-form-inputs'>
    
      <label>Aadhar card number:
        <input 
          type="text" 
          name="aadhar" 
          value={inputs.aadhar || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
      <div className='user-form-inputs'>
    
      <label>Date of birth:
        <input 
          type="date" 
          name="dateOfBirth" 
          value={inputs.dateOfBirth || ""} 
          onChange={handleChange}
        />
        </label>
    </div>
    <div className="user-form-inputs user-form-button">
        <input type="submit" value="Create account" />
    </div>
      </form>
    </div>
  )
}

export default App;
