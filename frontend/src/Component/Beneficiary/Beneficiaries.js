
import axios from "axios"
import { useContext, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getAllBeneficiaries, removeBeneficiary } from "../../Utilities/api";
import { UserContext } from "../../Utilities/context/userContext";
import withAuthorization from "../../Utilities/context/withAuthorization";
import { LOGIN } from "../../Utilities/routes";
import { toast } from "react-hot-toast";


const condition = (authUser) => !authUser // User not logged in -> Redirect to Login

export default withAuthorization (condition, LOGIN) (() => {
    const [beneficiaries, setBeneficiaries] = useState([])
    const { user } = useContext(UserContext)
    const [changed, setChanged] = useState(false)

    useEffect(() => {
        const updateBeneficiaries = async (user) => {
            const result = await getAllBeneficiaries(user.userName)
            if(result != null)
              setBeneficiaries(result.data)
        }
        updateBeneficiaries(user);        
    }, [changed])

    const handleRemoveBeneficiary = async (id) => {
      try{
        const response = await removeBeneficiary(id)
        if(response){
          toast.success(`Beneficiary removed!`)
          setChanged((prev) => !prev)
        }
      } catch(e){
        toast.error(e.response.data.message)
      }
    }

    
  return (
    <>
      <div className='w-full flex flex-col items-center'>
        <div className='w-1/4 my-8'>
          <h2 className="text-2xl font-semibold mb-6 w-full border-b-2 border-indigo-700 pb-4">Your Beneficiaries</h2>
          {beneficiaries.map((b, i) => {
            return (
              <div key={i} className='w-full p-4 my-2 bg-slate-100 shadow-md flex flex-col rounded'>
                <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Name: </span><span>{b.name}</span></p>
                <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Account Number: </span><span>{b.accountNumber}</span></p>
                <p className="flex flex-row items-center justify-between"><span className='font-semibold'>Nickname: </span><span>{b.nickname}</span></p>
                <button className={`mt-2 mr-0 ml-auto max-w-[240px] rounded-md px-1.5 p-1 text-sm font-semibold leading-6 text-white shadow-sm  focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 bg-red-600 hover:bg-red-500 focus-visible:outline-red-600`} onClick={(e) => handleRemoveBeneficiary(b.id)}>Remove beneficiary</button>
              </div>
            )
          })}
        </div>
      </div>
    </>
  )
})