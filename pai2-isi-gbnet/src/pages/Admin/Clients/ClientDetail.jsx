import { useParams } from "react-router-dom";
import axios from "axios";
import jwt from "jwt-decode";
import { useState, useEffect } from "react";
import { useAuthHeader } from "react-auth-kit";
export default function ClientDetail() {
  const [userOriginalData, setUserOriginalData] = useState({});
  const [userData, setUserData] = useState({});
  const { clientId } = useParams();
  const [isDisabled, setIsDisabled] = useState(true);
  const token = useAuthHeader();
  useEffect(() => {
    async function fetchUser() {
      axios.defaults.headers.common["Authorization"] = token();
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/user/${clientId}`
      );
      console.log(protectedEndpointResponse.data)
      setUserOriginalData(protectedEndpointResponse.data);
      setUserData(protectedEndpointResponse.data);
    }
    fetchUser();
    setIsDisabled(true)
  }, [clientId]);

  return (
    <div className="flex flex-direction flex-col bg-gray-100 w-full h-full">
      <div className="flex flex-col w-full items-center bg-gray-400">
        <span className="text-xl text-gray-800 font-semibold">
          {userData.firstName} {userData.lastName}
        </span>
      </div>
      <div className="flex flex-row w-full h-full p-4">
        <div className="flex flex-col w-1/2 p-2 space-y-2 justify-center">
          <div className="flex flex-col whitespace-nowrap p-8 space-y-8">
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">Imie</a>
              <input
                value={userData.firstName}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    firstName: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row  justify-between items-center">
              <a className="text-lg text-gray-700">Nazwisko</a>
              <input
                value={userData.lastName}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    lastName: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">Adres</a>
              <input
                value={userData.address}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    address: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">Telefon komórkowy</a>
              <input
                value={userData.phoneNumber}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    phoneNumber: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
          </div>
        </div>
        <div className="flex flex-col w-1/2 p-2 space-y-2 justify-center">
          <div className="flex flex-col whitespace-nowrap p-8 space-y-8">
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">E-mail</a>
              <input
                value={userData.email}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    email: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">PESEL</a>
              <input
                value={userData.pesel}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    pesel: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">NIP</a>
              <input
                value={userData.nip}
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    nip: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">Klient biznesowy</a>
              <select
                disabled={isDisabled}
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    isBusinessClient: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              >
                <option value={true} selected={ userData?.isBusinessClient? true:false }>TAK</option>
                <option value ={false} selected={ userData?.isBusinessClient? false:true }>NIE</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div className="flex flex-row ml-auto items-center p-1">
        {isDisabled ? (
          <button
            className="bg-gray-700 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-gray-800"
            onClick={() => {
              setIsDisabled(false);
            }}
          >
            Edytuj
          </button>
        ) : (
          <div>
            <button
              className="bg-gray-700 drop-shadow-md rounded-md mr-1 text-white font-bold text-md p-2 hover:bg-gray-800"
              onClick={() => {
                setIsDisabled(true);
                setUserData(userOriginalData);
              }}
            >
              Anuluj
            </button>
            <button
              className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500"
              onClick={() => {}}
            >
              Zapisz
            </button>
          </div>
        )}
      </div>
    </div>
  );
}
