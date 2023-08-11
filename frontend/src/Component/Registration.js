import { useState } from "react"
import axios from "axios"

function Registration() {
  const [inputs, setInputs] = useState({});

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputs(values => ({ ...values, [name]: value }))
  }

  const handleSubmit = async (event) => {
    console.log(inputs)
    event.preventDefault();
    if (Object.keys(inputs).length == 4) {
      let allFieldsEntered = true;
      Object.keys(inputs).map(field => {
        if (!inputs[field]) {
          allFieldsEntered = false;
        }
      })
      if (!allFieldsEntered) {
        return alert("All fields are mandatory!");
      }
      else {
        alert(JSON.stringify(inputs));
        console.log(inputs)
        inputs["customer_phone"] = parseInt(inputs[["customer_phone"]])
        inputs["customer_aadhar"] = parseInt(inputs[["customer_aadhar"]])
        const res = await axios.post("http://localhost:8090/saveCustomer", JSON.stringify(inputs), { headers: { "Content-Type": "application/json" } })
        if (res) {
          alert(JSON.stringify(res));
        }
        setInputs({})
      }
    } else {
      return alert("All fields are mandatory!");
    }
  }

  return (
    <div className="flex flex-col w-2/5 mt-8">
      <h2 className="text-2xl font-semibold mt-4 mb-2 w-full border-b-2 border-blue-500 pb-2">Personal details:</h2>
      <form onSubmit={handleSubmit} className=''>

        <div className=''>

          <label className="text-lg my-2">Customer Name:
            <input
              type="text"
              name="customer_name"
              value={inputs.customer_name || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
            />
          </label>
        </div>
        {/* <div className='user-form-inputs'>
    
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
    </div> */}
        <div className='user-form-inputs'>

          <label className="text-lg my-2">Mobile Number:
            <input
              type="number"
              name="customer_phone"
              value={inputs.customer_phone || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
            />
          </label>
        </div>
        {/* <div className='user-form-inputs'>

     <label>Email ID:
      <input 
        type="text" 
        name="emailId" 
        value={inputs.emailId || ""} 
        onChange={handleChange}
      />
      </label>    
    </div>
 */}
        <div className='user-form-inputs'>

          <label className="text-lg my-2">Aadhar card number:
            <input
              type="text"
              name="customer_aadhar"
              value={inputs.customer_aadhar || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
            />
          </label>
        </div>
        <div className='user-form-inputs'>

          <label className="text-lg my-2">Date of birth:
            <input
              type="date"
              name="customer_dob"
              value={inputs.customer_dob || ""}
              onChange={handleChange}
              className="border border-slate-500 focus-within:border-blue-500 text-lg p-1 mt-1"
            />
          </label>
        </div>
        <input type="submit" value="Create account" className="self-center p-2 uppercase bg-blue-800 text-white my-4"/>
      </form>
    </div>
  )
}

export default Registration;
