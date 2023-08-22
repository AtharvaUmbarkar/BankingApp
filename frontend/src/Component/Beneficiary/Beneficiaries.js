
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getAllBeneficiaries } from "../../Utilities/api";
import { UserContext } from "../../Utilities/context/userContext";
import withAuthorization from "../../Utilities/context/withAuthorization";
import { LOGIN } from "../../Utilities/routes";


const condition = (authUser) => !authUser // User not logged in -> Redirect to Login

export default withAuthorization(condition, LOGIN)(() => {
  const [beneficiaries, setBeneficiaries] = useState([])
  const { username } = useContext(UserContext)

  useEffect(() => {
    const updateBeneficiaries = async (username) => {
      const result = await getAllBeneficiaries(username)
      setBeneficiaries(result.data)
      console.log(result.data);
    }
    updateBeneficiaries(username);
  }, [])

  return (
    <>
      <div className='w-full flex flex-col items-center'>
        <div className='w-1/4 my-8'>
        <h2 className="text-2xl font-semibold mb-6 w-full border-b-2 border-indigo-700 pb-4">Your Beneficiaries</h2>
          {beneficiaries.map((b, i) => {
            return (
              <>
                <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Name: </span><span>{b.name}</span></p>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Account Number: </span><span>{b.accountNumber}</span></p>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Nickname: </span><span>{b.nickname}</span></p>
                </div>
                <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Name: </span><span>{b.name}</span></p>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Account Number: </span><span>{b.accountNumber}</span></p>
                  <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Nickname: </span><span>{b.nickname}</span></p>
                </div>
              </>
            )
          })}
        </div>
      </div>
    </>
  )
})