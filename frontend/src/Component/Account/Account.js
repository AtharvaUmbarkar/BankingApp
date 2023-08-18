import React, { useEffect, useState } from 'react'
import axios from "axios";
import { Card, Typography } from "@material-tailwind/react";

const BASE_URL = "http://localhost:8090/fetchAccounts/"
const TABLE_HEAD = ["Accounts"];
const USER_NAME = JSON.parse(sessionStorage.getItem('user'));

const Account = () => {
  const [accountsList, setAccounts] = useState([]);

  const fetchAccounts = () => {
    axios.get(BASE_URL + USER_NAME.username).then((response) => {
      console.log(response.data);
      setAccounts(response.data);
    }).catch(error => {
      //alert("Error while fetching data from user accounts...."+BASE_URL);
    })
  }

  useEffect(() => {
    fetchAccounts();
  }, []);

  return (
    <div>
      <div className="w-full border-b-2 border-gray-200"></div>
      <br></br>
      <div>
        <Card className="h-full overflow-scroll">
          <table className="bg-blue-50 min-w-max table-auto text-center">
            <thead>
              <tr>
                {TABLE_HEAD.map((head) => (
                  <th
                    key={head}
                    className="border-b border-blue-black-200 bg-blue-gray-200 p-4"
                  >
                    <Typography
                      variant="h3"
                      color="black"
                      className="font-100 leading-none opacity-100"
                      chil
                    >
                      {head}
                    </Typography>
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {
                accountsList.map(({ account, creationDate }, index) => {
                  const isLast = index === accountsList.length - 1;
                  const classes = isLast ? "p-4" : "p-4 border-b border-blue-gray-50";

                  return (
                    <tr key={account}>
                      <td className={classes}>
                        <Typography
                          variant="small"
                          color="blue-gray"
                          className="font-normal"
                        >
                          {account}
                        </Typography>
                      </td>
                      {/*<td className={creationDate}>
                  <Typography
                    variant="small"
                    color="blue-gray"
                    className="font-normal"
                  >
                    {creationDate}
                  </Typography>
            </td>*/}
                    </tr>
                  )
                })
              }
            </tbody>
          </table>
        </Card>
      </div>
    </div>
  )
}

export default Account