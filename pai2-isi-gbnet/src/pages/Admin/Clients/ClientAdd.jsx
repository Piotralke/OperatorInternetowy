import React, { useRef, useState } from "react";
import axios from "axios";
import { Alert,Typography } from "@material-tailwind/react";
import {BsInfoCircle} from "react-icons/bs"
import { useEffect } from "react";

export default function ClientAdd() {
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [isBusiness, setIsBusiness] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);
  const nameRef = useRef();
  const lastNameRef = useRef();
  const emaiRef = useRef();
  const phoneRef = useRef();
  const passwordRef = useRef();
  const passwordConfirmRef = useRef();
  const peselRef = useRef();
  const adddressRef = useRef();
  const nipRef = useRef();  


 
  async function handleSubmit(e){
      e.preventDefault();
      const data = {
      firstName: nameRef.current.value,
      last_name: lastNameRef.current.value,
      email: emaiRef.current.value,
      password: passwordRef.current.value,
      address: adddressRef.current.value,
      phoneNumber: phoneRef.current.value,
      nip: isBusiness ? nipRef.current.value:null,
      pesel: peselRef.current.value,
      isBusinessClient: isBusiness
    };
    console.log(data);
    const apiUrl = "http://localhost:8080/upc/unsecured/v1/client-register";
     const response = await axios.post(apiUrl, data);
     console.log(response);
     if(response.status === 200)
     {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const message = {
          message:`Pomyślnie dodano nowego użytkownika ${data.email}`,
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
  };
  return (
    <div className="flex flex-col h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2 ">
        <span className="text-xl text-gray-800 font-semibold">
          Dodaj klienta
        </span>
      </div>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col p-8 bg-gray-200 h-full justify-between"
      >
        <div className="grid gap-10 px-48 md:grid-cols-2">
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Imię</label>
            <input
              type="text"
              ref={nameRef}
              pattern="[A-Z]{1}[a-z]{1,}"
              title="Imię zaczyna się z dużej litery."
              className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500   rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Nazwisko</label>
            <input
              type="text"
              ref={lastNameRef}
              pattern="[A-Z]{1}[a-z]{1,}"
              title="Nazwisko zaczyna się z dużej litery."
              className="border-2 border-gray-500  invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900 px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Adres e-mail
            </label>
            <input
              type="email"
              ref={emaiRef}
              className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Numer Telefonu
            </label>
            <input
              type="text"
              ref={phoneRef}
              pattern="[0-9]{9}"
              title="Numer telefonu powinien składać się z 9 cyfr."
              className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Hasło</label>
            <input
              type="password"
              pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
              title="Hasło powino zawierać: conajmniej 8 znaków, conajmniej 1 wielką litere, conajmniej 1 cyfrę!"
              onChange={(e) => setPassword(e.target.value)}
              ref={passwordRef}
              className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <div className="flex flex-row space-x-2">
              <label className="text-md font-bold text-gray-700 ">
                Powtórz hasło
              </label>
              {passwordConfirm.length > 0 && password != passwordConfirm ? (
                <p className="text-red-500 text-xs mt-1">
                  Hasła nie zgadzają się
                </p>
              ) : null}
            </div>
            <input
              type="password"
              minLength={8}
              onChange={(e) => setPasswordConfirm(e.target.value)}
              ref={passwordConfirmRef}
              className={`border-2 border-gray-500 ${
                passwordConfirm.length > 0 && password != passwordConfirm
                  ? "border-red-500 outline-red-500"
                  : "border-gray-500 outline-gray-500"
              } rounded-md mt-1 text-gray-900  px-4 py-1`}
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">PESEL</label>
            <input
              type="text"
              minLength={11}
              maxLength={11}
              ref={peselRef}
              pattern="[0-9]{11}"
              title="PESEL powinien składać się z 11 cyfr."
              className={`border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1`}
              required
            />
          </div>
                  
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">NIP</label>
            <input
              required = {isBusiness ? true : false}
              disabled={!isBusiness}
              type="text"
              ref={nipRef}
              minLength={10}
              maxLength={10}
              pattern="[0-9]{10}"
              title="NIP powinien mieć 10 cyfr!"
              className={`border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1`}
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Adres</label>
            <input
              type="text"
              ref={adddressRef}
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>  
          <div className="flex flex-row items-center space-x-4">
            <input
              type="checkbox"
              className="scale-150"
              onChange={() => {
                setIsBusiness(!isBusiness);
              }}
            />
            <label className="text-lg font-bold text-gray-700">
              Klient biznesowy
            </label>
          </div>
        </div>
        <div className="flex flex-col w-full mt-8 items-center">
          <button
            type="submit"
            className="bg-green-300 py-2 w-1/2 rounded-md text-gray-900 text-xl shadow-md hover:bg-green-500"
          >
            Dodaj
          </button>
        </div>
      </form>
    </div>
  );
}
