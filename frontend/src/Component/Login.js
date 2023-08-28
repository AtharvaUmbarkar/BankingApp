import axios from "axios";
import React, { useContext, useState } from "react"
import { toast } from "react-hot-toast";
import { useNavigate, Link } from "react-router-dom";
import { UserContext } from "../Utilities/context/userContext";
import withAuthorization from "../Utilities/context/withAuthorization";
import { loginUser } from "../Utilities/api";
import { HOME } from "../Utilities/routes";




const condition = (authUser) => authUser
export default withAuthorization(condition, HOME)(() => {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [admin, setAdmin] = useState(false);
  const navigate = useNavigate()
  const { setUserInContext } = useContext(UserContext)

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (username && password) {
      try {
        const response = await loginUser({ username, password }, admin)
        if (response) {
          if (response.data.mobileNumber) {
            setUserInContext(response.data)
            toast.success(`Welcome ${response.data.userName}`, {duration:3000})
            if (admin) navigate("/admin")
            else navigate("/user")
          } else {
            toast.error("Login failed!")
          }
        }
      } catch (error) {
        console.log(error)
        if(error.response.data){
          toast.error(error.response.data.message);
        }
        else{
          toast.error("Invalid credentials!");
        }
      }
    }
  }


  return (
    <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-2 lg:px-8">
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
        <h2 className="mt-2 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
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
              <div className="text-sm flex flex-col items-end">
                <Link to="/forgot-password" className="font-semibold text-indigo-600 hover:text-indigo-500">
                  Change/Forgot Password?
                </Link>
                <Link to="/forgot-username" className="font-semibold text-indigo-600 hover:text-indigo-500">
                  Change/Forgot Username?
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
            <div className="mt-4 flex flex-row items-center justify-center">
              <label htmlFor="admin" className="mr-6 whitespace-nowrap text-sm">Sign In as Admin?</label>
              <input
                id="admin"
                name="admin"
                type="checkbox"
                className="w-4 h-4"
                value={admin}
                onChange={(e) => setAdmin(!admin)}
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
          <Link to="/savings-account-registration" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">
            Register here
          </Link>
        </p>
      </div>
    </div>
  )


})
