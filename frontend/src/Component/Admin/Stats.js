import { toast } from "react-hot-toast";
import { getAllCustomers, getTransactionStats } from "../../Utilities/api"
import { useEffect, useState } from "react";

export default function Stats(){
  const [stats, setStats] = useState({
    customers: "-",
    transactions: "-",
    transactionsWorth: "0"
  })

  useEffect(() => {
    const getStats = async () => {
      const promises = [getAllCustomers(), getTransactionStats()];
      try{
        let response = await Promise.all(promises)
        if(response){
          setStats({
            customers: response[0].data ? response[0].data.length :  "-",
            transactions: response[1].data ? response[1].data[0] : "-",
            transactionsWorth: response[1].data ? response[1].data[1] : "0", 
          })
        }
      } catch(e){
        toast.error(e.response.data.message)
      }
    }

    getStats()


  }, [])

    return (
          <div className="mx-auto  flex lg:flex-col lg:flex-grow justify-center max-w-4xl h-48 lg:h-[32rem] my-auto min-h-full">
                <dl className="grid grid-cols-1 gap-x-8 gap-y-16 text-center lg:grid-cols-3">
                <div className="w-64 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 px-16 py-12 mx-auto flex max-w-xs items-center flex-col gap-y-4">
                      <dt className="text-base leading-7 text-gray-600">customers</dt>
                      <dd className="order-first text-3xl font-semibold tracking-tight text-gray-900 sm:text-5xl">
                        {stats.customers}
                      </dd>
                    </div>
                    <div className="w-64 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 px-16 py-12 mx-auto flex max-w-xs items-center flex-col gap-y-4">
                      <dt className="text-base leading-7 text-gray-600">transactions</dt>
                      <dd className="order-first text-3xl font-semibold tracking-tight text-gray-900 sm:text-5xl">
                        {stats.transactions}
                      </dd>
                    </div>
                    <div className="w-64 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 px-16 py-12 mx-auto flex max-w-xs items-center flex-col gap-y-4">
                      <dt className="text-base leading-7 text-gray-600">transacted</dt>
                      <dd className="order-first text-3xl font-semibold tracking-tight text-gray-900 sm:text-5xl">
                        {stats.transactionsWorth.toLocaleString('en-IN', {style: "currency", currency: "INR", minimumFractionDigits: 0, maximumFractionDigits: 0})}
                      </dd>
                    </div>
                </dl>
          </div>
    )
}