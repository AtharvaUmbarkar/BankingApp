import React, { useState } from 'react'

const AccountSidebar = () => {
    return(
        <div>
            <div className="pt-0 pr-0 pb-0 pl-0 mt-0 mr-0 mb-0 ml-0">

            </div>
            <div className="bg-white"></div>
            <div className="bg-white">
                <div className="flex-col flex">
                    <div className="w-full border-b-2 border-gray-200">
                    </div>
                    <div className="flex bg-white-100  overflow-x-hidden">
                        <div className="bg-gray-100 lg:flex md:w-54 md:flex-col hidden">
                            <div className="flex-col pt-5 flex overflow-y-auto">
                                <div className="h-full flex-col justify-between px-4 flex">
                                    <div className="space-y-4">
                                        <div className="bg-top bg-cover space-y-1">
                                            <a href="#" className="font-medium text-sm items-center rounded-lg text-blue-900 px-4 py-2.5 flex
                    transition-all duration-200 hover:bg-gray-200 group cursor-pointer">
                                                <span className="justify-center items-center flex">
                                                    <span className="justify-center items-center flex">
                                                        <span className="justify-center items-center flex">
                                                            <span className="items-center justify-center flex">
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                                <span>Account Details</span>
                                            </a>
                                            <a href="#" className="font-medium text-sm items-center rounded-lg text-blue-900 px-4 py-2.5 flex
                    transition-all duration-200 hover:bg-gray-200 group cursor-pointer">
                                                <span className="justify-center items-center flex">
                                                    <span className="justify-center items-center flex">
                                                        <span className="justify-center items-center flex">
                                                            <span className="items-center justify-center flex">
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                                <span>Account Summary</span>
                                            </a>
                                            <a href="#" className="font-medium text-sm items-center rounded-lg text-blue-900 px-4 py-2.5 flex
                    transition-all duration-200 hover:bg-gray-200 group cursor-pointer">
                                                <span className="justify-center items-center flex">
                                                    <span className="justify-center items-center flex">
                                                        <span className="justify-center items-center flex">
                                                            <span className="items-center justify-center flex">
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                                <span>Fund Transfer</span>
                                            </a>
                                            <a href="#" className="font-medium text-sm items-center rounded-lg text-blue-900 px-4 py-2.5 flex
                    transition-all duration-200 hover:bg-gray-200 group cursor-pointer">
                                                <span className="justify-center items-center flex">
                                                    <span className="justify-center items-center flex">
                                                        <span className="justify-center items-center flex">
                                                            <span className="items-center justify-center flex">
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                                <span>Account Statement</span>
                                            </a>
                                            <a href="withdraw" className="font-medium text-sm items-center rounded-lg text-blue-900 px-4 py-2.5 flex
                    transition-all duration-200 hover:bg-gray-200 group cursor-pointer">
                                                <span className="justify-center items-center flex">
                                                    <span className="justify-center items-center flex">
                                                        <span className="justify-center items-center flex">
                                                            <span className="items-center justify-center flex">
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                                <span>Withdraw</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div >

    );
}

export default AccountSidebar;
