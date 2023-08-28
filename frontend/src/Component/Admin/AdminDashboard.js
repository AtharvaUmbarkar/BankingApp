import { Outlet, useLocation } from 'react-router-dom'
import AdminNavbar from './AdminNavbar'

const pathMap = {
  "stats": "Dashboard",
  "viewCustomers": "Customers",
  "openAccount": "Open Account",
  "addCustomer": "Add Customer",
  "registerCustomerForOnlineBanking": "Register Customer For Online Banking"
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
