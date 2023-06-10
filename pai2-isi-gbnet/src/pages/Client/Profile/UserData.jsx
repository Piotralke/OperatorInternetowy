import userPic from "../../../assets/userPic.jpg";
import { useState, useEffect, useRef, useLayoutEffect } from "react";

export default function UserData(props) {

  const [phoneNumber, setPhoneNumber] = useState(props.phoneNumber);
  const [email, setEmail] = useState(props.email);
  const [isDisabled, setIsDisabled] = useState(true);
  const [loading, setLoading] = useState(true);



  return (
    <div className="flex flex-col bg-blue-gray-700 my-7 basis-3/5">
      <div className="flex flex-row p-4 border-b border-blue-gray-600">
        <a className=" text-xl font-bold underline underline-offset-1 text-white">
          Moje dane
        </a>
      </div>
      <div className="flex flex-col p-4">
        <div className="flex flex-row drop-shadow-lg bg-blue-gray-800 p-4">
          <img className="w-10 h-10 rounded-full" src={userPic} alt="User" />
          <div className="flex flex-col">
            <div className="text-lg text-blue-300 ml-3">
              {props.firstName} {props.lastName}
            </div>
            <div className="flex flex-row">
              <text className="text-slate-300 mt-3 ml-3 flex flex-col">
                Status konta:
              </text>
              <text className="text-slate-300 mt-3 ml-3 flex flex-col font-bold">
                {props.accountStatus}
              </text>
            </div>
          </div>
        </div>
        <div className="flex flex-col whitespace-nowrap p-8">
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Telefon komórkowy</a>
            <input
              disabled={isDisabled}
              className="px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-1/2 rounded-sm text-blue-400 text-lg"
              value={loading ? props.phoneNumber : phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <text className="text-lg text-white">Adres e-mail</text>
            <input
              disabled={isDisabled}
              className="px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-1/2 rounded-sm text-blue-400 text-lg"
              value={loading ? props.email : email }
              onChange={(e) => setEmail(e.target.value)}
              
            />
          </div>

          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Adres Korespondencyjny</a>
            <input
              className="px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={props.address}
              readOnly
            />
          </div>
          {isDisabled && (
            <button
              className="bg-blue-900 drop-shadow-md rounded-md text-white font-bold h-12 w-1/4 text-md  ml-auto"
              onClick={() => {
                setIsDisabled(false)
                setEmail(props.email)
                setPhoneNumber(props.phoneNumber)
                setLoading(false);
              }}
            >
              Edytuj
            </button>
          )}
          {!isDisabled && (
            <button
              className="bg-blue-900 drop-shadow-md rounded-md text-white font-bold h-12 w-1/4 text-md  ml-auto"
              onClick={() => {
                setEmail(props.email)
                setPhoneNumber(props.phoneNumber)
                setIsDisabled(true)
                setLoading(false);
              }}
                
            >
              Anuluj
            </button>
          )}
          <button className="bg-blue-gray-800 drop-shadow-md rounded-md text-white font-bold text-md h-12 mt-4">
            Zapisz
          </button>
        </div>
      </div>
    </div>
  );
}
