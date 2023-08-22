import { Outlet, useLocation } from 'react-router-dom'
import AdminNavbar from './AdminNavbar'

const pathMap = {
  "stats": "Dashboard",
  "viewCustomers": "Customers"
}

export default function AdminDashboard() {

  const { pathname } = useLocation()

  return (
    <>
      <AdminNavbar active={pathMap[pathname.split("/").reverse()[0]]}/>
      <Outlet />
    </>
  )
}
