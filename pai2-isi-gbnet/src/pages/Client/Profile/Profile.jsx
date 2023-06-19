import UserData from "./UserData"
import axios from "axios"
import {useAuthHeader,useAuthUser} from 'react-auth-kit'
import { useState,useEffect } from "react";
import jwt from "jwt-decode"

export default function Profile(){
    const [userData,setUserData] = useState([]);
    const token = useAuthHeader();
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
        }
        getUserData();
    },[])
    return(
        <div className="flex flex-row flex-wrap justify-center w-full min-h-full basis-4/5 bg-blue-gray-300">
        <UserData userData={userData}  ></UserData>
        
        </div>
         //<UserData phoneNumber={userData.phoneNumber} email={userData.email} firstName={userData.firstName} lastName={userData.lastName} address={userData.address}  ></UserData>
        

    )
}