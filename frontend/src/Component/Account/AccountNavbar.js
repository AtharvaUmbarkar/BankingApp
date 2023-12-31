import React, { useContext, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Dialog } from '@headlessui/react'
import { Bars3Icon, ChevronLeftIcon, XMarkIcon } from '@heroicons/react/24/outline'
import { UserContext } from '../../Utilities/context/userContext';


function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}


const AccountNavbar = ({active}) => {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
    const navigate = useNavigate();
    const { user, removeUser } = useContext(UserContext)

    const { accountNumber } = useParams();
    const navigation = [
        { name: 'Details', to: `/account/${accountNumber}/details` },
        { name: 'Statement', to: `/account/${accountNumber}/statement` },
        { name: 'Transaction', to: `/account/${accountNumber}/transaction/imps` },
        { name: 'Transaction History', to: `/account/${accountNumber}/transactionHistory` },
    ]

    const handleLogout = () => {
        removeUser();
        navigate('/');
    }

    useEffect(() => {
        if (!sessionStorage.getItem('user')) {
            navigate('/');
        }
    }, [])


    return (
        <header className=" inset-x-0 top-0 z-50 w-full">
            <nav className="shadow-md flex items-center justify-between p-6 lg:px-8 w-full" aria-label="Global">
                <div className="flex lg:flex-1">
                    <Link to='/' className="-m-1.5 p-1.5">
                        <span className="sr-only">Banking App</span>
                        <p className='font-serif font-bold text-3xl text-indigo-700'>BA</p>
                        {/* <img
                className="h-8 w-auto"
                src="https://ogden_images.s3.amazonaws.com/www.nashuatelegraph.com/images/2018/12/29000134/1024px-Wells_Fargo_Bank.svg_-840x840.png"
                alt=""
              /> */}
                    </Link>
                </div>
                <div className="flex lg:hidden">
                    <button
                        type="button"
                        className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
                        onClick={() => setMobileMenuOpen(true)}
                    >
                        <span className="sr-only">Open main menu</span>
                        <Bars3Icon className="h-6 w-6" aria-hidden="true" />
                    </button>
                </div>
                <div className="hidden lg:flex lg:gap-x-12 items-center">
                    <Link to={"/user/account"} className="flex flex-row items-center text-sm font-semibold leading-6 text-gray-900">
                        <ChevronLeftIcon className="h-4 w-4 mr-1" />
                        Accounts
                    </Link>
                    {navigation.map((item) => (
                        <Link key={item.name} to={item.to} className={classNames(
                            item.name == active
                              ? 'bg-indigo-700 text-white'
                              : 'text-black hover:bg-indigo-700 hover:text-white',
                            'rounded-md px-3 py-2 text-sm font-medium'
                          )}>
                            {item.name}
                        </Link>
                    ))}
                </div>
                <div className="hidden lg:flex lg:flex-1 lg:justify-end flex-row ml-12">
                    {user &&
                        <Link to={"/user"} className='py-1 px-3 text-white bg-indigo-700 mr-8 rounded'>{user.userName}</Link>
                    }
                    <button type='button' onClick={handleLogout} className="whitespace-nowrap mr-1 text-sm font-semibold leading-6 text-gray-900">
                        Log Out
                    </button>
                </div>
            </nav>
            <Dialog as="div" className="lg:hidden" open={mobileMenuOpen} onClose={setMobileMenuOpen}>
                <div className="fixed inset-0 z-50" />
                <Dialog.Panel className="fixed inset-y-0 right-0 z-50 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
                    <div className="flex items-center justify-between">
                        <Link to='/' className="-m-1.5 p-1.5">
                            <span className="sr-only">Banking App</span>
                            <p className='font-serif'>BA</p>
                            {/* <img
                  className="h-8 w-auto"
                  src="https://ogden_images.s3.amazonaws.com/www.nashuatelegraph.com/images/2018/12/29000134/1024px-Wells_Fargo_Bank.svg_-840x840.png"
                  alt=""
                /> */}
                        </Link>
                        <button
                            type="button"
                            className="-m-2.5 rounded-md p-2.5 text-gray-700"
                            onClick={() => setMobileMenuOpen(false)}
                        >
                            <span className="sr-only">Close menu</span>
                            <XMarkIcon className="h-6 w-6" aria-hidden="true" />
                        </button>
                    </div>
                    <div className="mt-6 flow-root">
                        <div className="-my-6 divide-y divide-gray-500/10">
                            <div className="space-y-2 py-6">
                                {navigation.map((item) => (
                                    <Link
                                        key={item.name}
                                        to={item.to}
                                        className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                                    >
                                        {item.name}
                                    </Link>
                                ))}
                            </div>
                            <div className="py-6">
                                {user &&
                                    <Link to={"/user"} className='py-1 px-3 text-white bg-indigo-700 rounded'>{user.userName}</Link>
                                }
                                <button
                                    onClick={handleLogout}
                                    className="-mx-3 block rounded-lg px-3 py-2.5 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                                >
                                    Log Out
                                </button>
                            </div>
                        </div>
                    </div>
                </Dialog.Panel>
            </Dialog>
        </header>
    )

}

export default AccountNavbar