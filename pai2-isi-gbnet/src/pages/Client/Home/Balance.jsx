import { Link } from "react-router-dom";
import axios from "axios"
import {useAuthHeader,useAuthUser} from 'react-auth-kit'
import { useState,useEffect } from "react";
import jwt from "jwt-decode"
import { Spinner } from "@material-tailwind/react";
export default function Balance() {

  const [userData,setUserData] = useState([]);
  const token = useAuthHeader();
  const [loading, setLoading] = useState(true);
  useEffect(()=>{
      async function getUserData(){
          const data = jwt(token());
          axios.defaults.headers.common['Authorization'] = token();
          const protectedEndpointResponse = await axios.get(
            "http://localhost:8080/upc/v1/user-role/user",
            {
              params: {
                email: data.sub,
              },
              headers:{
                "Content-Type": "application/json"
              },
              data:{}
            }
          );
      setUserData(protectedEndpointResponse.data);
      setLoading(false);
      }
      getUserData();
  },[])
  if (loading) {
    return (
      <div className="flex flex-col w-full h-full items-center justify-center">
        <Spinner color="amber" className="h-1/6 w-1/6"></Spinner>
      </div>
    );
  }
  return (
    <div className="flex flex-col w-full xl:w-1/2 ">
        <div className="text-xl text-white">
            Płatności
        </div>
      <div className="flex flex-col bg-blue-gray-700 h-full overflow-y-auto">
        <div className="flex flex-row p-4 border-b border-blue-gray-600">
          <p className="flex-grow text-white">Stan salda</p>
        </div>
        <div className="flex flex-col items-center justify-center p-4 h-full">
          <div className={`flex flex-row w-full h-1/3 m-4 ${userData?.balance>=0?"shadow-green-700 bg-green-500" :"shadow-red-700 bg-red-500" }  shadow-md  justify-center align-middle`}>
            <div className="flex flex-col justify-center ">
              <p className=" text-5xl  text-white ">{userData?.balance.toFixed(2)} zł</p>
            </div>
          </div>
          <div className="flex flex-row w-full m-4 h-1/3 bg-blue-gray-800 justify-center align-middle">
            <Link className="flex flex-col justify-center" to="/invoices">
              <div className="text-lg text-amber-500 align-middle">
                Wszystkie faktury
              </div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
