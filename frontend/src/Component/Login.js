import axios from "axios";
import React, { useState } from "react"
import { toast } from "react-hot-toast";
import { useNavigate, Link } from "react-router-dom";



const LOGIN_URL = "http://localhost:8090/Login"

function Login() {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const navigate = useNavigate()


    const handleSubmit = async (e) => {
        e.preventDefault();
        if(username && password){
            const response = await axios.post(LOGIN_URL, JSON.stringify({username, password}),{
                headers:{
                    "Content-Type": "application/json"
                }
            })
            if(response){
                if(response.data == "Login Success"){
                    const toastId = toast.loading("Logging you in...")
                    setTimeout(() => {
                        sessionStorage.setItem("user", JSON.stringify({username}))
                        toast.dismiss(toastId)
                        navigate("/user/profile")
                    }, 1000)
                } else{
                    toast.error("Login failed!")
                }
            }
        }
    }


    return (
        <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-6 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
            <Link to="/" className="-m-1.5 p-1.5 text-center">
              <span className="sr-only">Banking App</span>
              <p className='font-serif font-bold text-3xl text-indigo-700'>BA</p>
              {/* <img
                className="h-8 w-auto"
                src="https://ogden_images.s3.amazonaws.com/www.nashuatelegraph.com/images/2018/12/29000134/1024px-Wells_Fargo_Bank.svg_-840x840.png"
                alt=""
              /> */}
            </Link>
          <h2 className="mt-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Log in to your account
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <form className="space-y-6" onSubmit={handleSubmit}>
            <div>
              <label htmlFor="username" className="block text-sm font-medium leading-6 text-gray-900">
                Username
              </label>
              <div className="mt-2">
                <input
                  id="username"
                  name="number"
                  type="username"
                  autoComplete="username"
                  required
                  className="block w-full rounded-md border-0 py-1.5 px-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  />
              </div>
            </div>

            <div>
              <div className="flex items-center justify-between">
                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                  Password
                </label>
                <div className="text-sm">
                  <Link to="/" className="font-semibold text-indigo-600 hover:text-indigo-500">
                    Forgot password?
                  </Link>
                </div>
              </div>
              <div className="mt-2">
                <input
                  id="password"
                  name="password"
                  type="password"
                  autoComplete="current-password"
                  required
                  className="block w-full rounded-md border-0 py-1.5 px-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
            </div>

            <div>
              <button
                type="submit"
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                Log in
              </button>
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Don't have an account?{' '}
            <Link to="/" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">
              Register here
            </Link>
          </p>
        </div>
      </div>
    )
}

export default Login;