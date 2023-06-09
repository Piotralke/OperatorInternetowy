
import { Link } from "react-router-dom";
import { AiOutlineInfoCircle } from "react-icons/ai";
import axios from "axios";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import React, { useState, useEffect } from "react";
import jwt from "jwt-decode";
import { FaUserCircle } from "react-icons/fa"
export default function AccountData() {
  const [userData, setUserData] = useState([]);
  const token = useAuthHeader();
  useEffect(() => {
    async function getUserData() {
      const data = jwt(token());
      
      axios.defaults.headers.common["Authorization"] = token();
      const protectedEndpointResponse = await axios.get(
        "http://localhost:8080/upc/v1/user-role/user",
        {
          params: {
            email: data?.sub,
          },
          headers:{
            "Content-Type": "application/json"
          },
          data:{}
        }
      );
      setUserData(protectedEndpointResponse?.data);
    }
    getUserData();
  }, []);

  return (
    <div className="flex flex-col w-full xl:w-1/2 ">
      <div className="text-xl text-white">Moje konto</div>
      <div className="flex flex-col bg-blue-gray-700 h-full overflow-y-auto">
        <div className="flex flex-row p-4 border-b border-blue-gray-600">
          <p className="flex-grow text-white">Numer klienta</p>
          <p data-testid="client-number" className="text-amber-500 font-bold">{userData?.uuid}</p>
        </div>
        <div className="flex flex-col items-center justify-center p-4 h-full w-full">
          <div className="flex flex-row w-full p-5 m-4 bg-blue-gray-800 items-center">
            <div className=" justify-center align-middle items-center mr-8 w-1/5 xl:w-[10%]">
              <FaUserCircle className="self-center flex w-full h-full text-gray-300"> </FaUserCircle>
            </div>
            <div className="flex flex-col w-4/5 xl:w-2/3">
              <div data-testid="client-firstnameandlastname" className="text-sm text-amber-500">
                {userData?.firstName} {userData?.lastName}
              </div>
              <div className="text-sm text-white">Adres</div>
              <div data-testid="client-address" className="text-sm text-amber-500 truncate">
                {userData?.address}
              </div>
            </div>
          </div>
          <div className="flex flex-row">
            <AiOutlineInfoCircle className="w-[2vw] h-[2vh] text-amber-500"></AiOutlineInfoCircle>
            <div className="text-amber-500 text-sm ml-2">
              Przejdz do zakładki PROFIL, aby wyświetlić lub edytować dane
            </div>
          </div>
          <div className="flex flex-row w-full m-4 h-1/3 bg-blue-gray-800 justify-center align-middle">
            <Link className="flex flex-col justify-center" to="/profile">
              <div className="text-lg text-amber-500 align-middle">
                PROFIL
              </div>
            </Link>
          </div>
          <div></div>
        </div>
      </div>
    </div>
  );
}
