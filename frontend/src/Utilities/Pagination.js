import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/react/24/outline'

export default  function Pagination({ totalPages, perPage, page, setPage, type, length }){
    return (
        <div className="flex items-center justify-between bg-white mt-4">
      {/* <div className="flex flex-1 justify-between sm:hidden">
        <a
          href="#"
          className="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50"
        >
          Previous
        </a>
        <a
          href="#"
          className="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50"
        >
          Next
        </a>
      </div> */}
      <div className="flex flex-1 items-center justify-between">
      <div>
          <p className="text-sm text-gray-700">
            Showing <span className="font-medium">{(page - 1)*perPage + 1}</span> to <span className="font-medium">{Math.min(page*perPage, length)}</span> of{' '}
            <span className="font-medium">{length}</span> {type}
          </p>
        </div>
        <div>
          <nav className="isolate inline-flex -space-x-px rounded-md shadow-sm" aria-label="Pagination">
            <button
              className={`relative inline-flex items-center rounded-l-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0 ${page == 1 && 'disabled cursor-not-allowed'}`}
              onClick={() => setPage((prev) => prev - 1)}
              disabled={page == 1}
            >
              <span className="sr-only">Previous</span>
              <ChevronLeftIcon className="h-5 w-5" aria-hidden="true" />
            </button>
            {totalPages < 9 && ([...Array(Math.ceil(totalPages))].map((_, i) => (
                <button
                key={i}
                className={`hidden sm:inline-flex relative z-10 items-center ${i + 1 == page ? 'bg-indigo-600 text-white text-white focus:z-20 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-60' : 'text-gray-900 ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0 ring-1 ring-inset ring-gray-300'} px-4 py-2 text-sm font-semibold`}
                onClick={() => setPage(i + 1)}
              >
                {i + 1}
              </button>
            )))}
            
            <button
              className={`relative inline-flex items-center rounded-r-md px-2 py-2 text-gray-400 ring-1 ring-inset ring-gray-300 hover:bg-gray-50 focus:z-20 focus:outline-offset-0 ${totalPages == page && 'disabled cursor-not-allowed'}`}
              onClick={() => {
                setPage((prev) => prev + 1)
                console.log(page)
                }}
              disabled={totalPages == page}

            >
              <span className="sr-only">Next</span>
              <ChevronRightIcon className="h-5 w-5" aria-hidden="true" />
            </button>
          </nav>
        </div>
      </div>
    </div>
    )
}