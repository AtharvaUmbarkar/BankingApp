const stats = [
    { id: 1, name: 'New users', value: '12' },
    { id: 2, name: 'Transactions every 24 hours', value: '100' },
    { id: 3, name: 'Assets under holding', value: '₹25,000' },
]
  

export default function Stats(){
    return (
          <div className="mx-auto  flex lg:flex-col lg:flex-grow justify-center max-w-4xl h-48 lg:h-[32rem] my-auto min-h-full">
                <dl className="grid grid-cols-1 gap-x-8 gap-y-16 text-center lg:grid-cols-3">
                  {stats.map((stat) => (
                    <div key={stat.id} className="w-64 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 px-16 py-12 mx-auto flex max-w-xs items-center flex-col gap-y-4">
                      <dt className="text-base leading-7 text-gray-600">{stat.name}</dt>
                      <dd className="order-first text-3xl font-semibold tracking-tight text-gray-900 sm:text-5xl">
                        {stat.value}
                      </dd>
                    </div>
                  ))}
                </dl>
          </div>
    )
}