import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const Navbar = () => {
    const [open, setOpen] = useState(false);
    const toggleOpen = (e) => {
        setOpen((open) => !open);
        console.log(open);
    }
    return (
        <nav className='sticky h-16 w-full shadow-lg'>
            <div className='w-full h-full flex flex-row items-center'>
                <Link to='/' className='text-2xl text-blue-500 font-semibold px-4'>BankingApp</Link>
                <div className='flex flex-row grow items-center'>
                    {/* <button onClick={() =>toggleOpen()}>open</button> */}
                </div>
            </div>
            <div>

            </div>
        </nav>
    )
}

export default Navbar