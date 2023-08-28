import { Outlet, useLocation } from 'react-router-dom'
import AdminNavbar from './AdminNavbar'
import withAuthorization from '../../Utilities/context/withAuthorization'
import { LOGIN } from '../../Utilities/routes'

const pathMap = {
  "stats": "Dashboard",
  "viewCustomers": "Customers",
  "openAccount": "Open Account",
  "addCustomer": "Add Customer",
  "registerCustomerForOnlineBanking": "Register Customer For Online Banking"
}

const condition = (user) => !user || !user.isAdmin

export default withAuthorization(condition, LOGIN) (() =>  {
  const { pathname } = useLocation()

  return (
    <>
      <AdminNavbar active={pathMap[pathname.split("/").reverse()[0]]}/>
      <Outlet />
    </>
  )
}
)