import userPic from "../../../assets/userPic.jpg";
import { useState, useEffect, useRef, useLayoutEffect } from "react";
import axios from "axios";
import {
  Button,
} from "@material-tailwind/react";
export default function UserData(props) {

  const [phoneNumber, setPhoneNumber] = useState(props.phoneNumber);
  const [isDisabled, setIsDisabled] = useState(true);
  const [loading, setLoading] = useState(true);



  async function handleSave ()
  {
    const newData = {
      uuid: props.userData.uuid,
      phoneNumber: phoneNumber,
      email: props.userData.email,
      address: props.userData.address,
      balance: props.userData.balance,
      firstName: props.userData.firstName,
      lastName: props.userData.lastName,
      nip: props.userData.nip,
      isBusinessClient: props.userData.isBusinessClient
    };

    console.log(email);
    const apiUrl = "http://localhost:8080/upc/unsecured/v1/edit-client";
     const response = await axios.put(apiUrl, newData);
     if(response.status === 200)
     {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const message = {
          message:`Pomyślnie zmieniono dane`,
          type: "SUCCESS"
        }
        if(tab)
        {
          newTab = [...tab,message];
        }else{
          newTab = [message];
        }
        
        window.localStorage.setItem("notifications",JSON.stringify(newTab));
        window.dispatchEvent(new Event("storage"))
        window.location.reload();
     }
    console.log(response);
  }

  return (
    <div className="flex flex-col bg-blue-gray-700 my-7 basis-11/12  xl:basis-3/5">
      <div className="flex flex-row p-4 border-b border-blue-gray-600">
        <a className=" text-xl font-bold underline underline-offset-1 text-white">
          Moje dane
        </a>
      </div>
      <div className="flex flex-col p-4">
        <div className="flex flex-row drop-shadow-lg bg-blue-gray-800 p-4">
          <img className="w-10 h-10 rounded-full" src={userPic} alt="User" />
          <div className="flex flex-col">
            <div className="text-lg text-amber-500 ml-3">
              {props.userData.firstName} {props.userData.lastName}
            </div>
            
          </div>
        </div>
        <div className="flex flex-col whitespace-nowrap p-8">
          <div className="flex flex-col xl:flex-row mb-4 justify-between">
            <a className="text-lg text-white">Telefon komórkowy</a>
            <input
              disabled={isDisabled}
              className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 ${isDisabled ? "bg-blue-gray-700" : "bg-blue-gray-600"}  w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
              value={loading ? props.userData.phoneNumber : phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
          </div>
          <div className="flex flex-col xl:flex-row mb-4 justify-between">
            <text className="text-lg text-white">Adres e-mail</text>
            <input
              type="email"
              disabled={isDisabled}
              className="px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg"
              value={props.userData.email}
              readOnly
              
            />
          </div>

          <div className="flex flex-col xl:flex-row mb-4 justify-between">
            <a className="text-lg text-white">Adres</a>
            <input
              className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
              value={props.userData.address}
              readOnly
            />
          </div>

          <div className="flex flex-col xl:flex-row mb-4 justify-between">
            <a className="text-lg text-white">NIP</a>
            <input
              className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
              value={props.userData.nip}
              readOnly
            />
          </div>

          <div className="flex flex-col xl:flex-row mb-4 justify-between">
            <a className="text-lg text-white">PESEL</a>
            <input
              className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
              value={props.userData.pesel}
              readOnly
            />
          </div>
          {isDisabled && (
            <Button
              color='deep-orange'
              className="w-full xl:w-1/3 xl:ml-auto mb-2"
              onClick={() => {
                setIsDisabled(false)
                setPhoneNumber(props.userData.phoneNumber)
                setLoading(false);
              }}
            >
              Edytuj
            </Button>
          )}
          {!isDisabled && (
            <Button
            color='deep-orange'
            className="w-full xl:w-1/3 xl:ml-auto mb-2"
              onClick={() => {
                setPhoneNumber(props.userData.phoneNumber)
                setIsDisabled(true)
                setLoading(false);
              }}
                
            >
              Anuluj
            </Button>
          )}
          <Button color='amber' onClick={()=> handleSave()}>
            Zapisz
          </Button>
        </div>
      </div>
    </div>
  );
}
