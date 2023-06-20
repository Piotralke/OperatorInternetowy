import React, { useState, useEffect, useRef, useLayoutEffect } from "react";
import axios from "axios";
import jwt from "jwt-decode";
import { Button } from "@material-tailwind/react";
import { useAuthHeader } from "react-auth-kit";
import {FaUserCircle} from "react-icons/fa"
export default function UserData(props) {
  const [phoneNumber, setPhoneNumber] = useState(props.phoneNumber);
  const [isDisabledData, setIsDisabledData] = useState(true);
  const [isDisabledPassword, setIsDisabledPassword] = useState(true);
  const [valid, setValid] = useState(false);
  const [loading, setLoading] = useState(true);
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const token = useAuthHeader();

  async function handlePasswordChange(e) {
    e.preventDefault();
    const newData = {
      uuid: props.userData.uuid,
      password: newPassword,
    };

     const data = jwt(token());
    const config = {
      params: {
        email: data.sub,
      },
      headers: {
        "Content-Type": "application/json",
      },
      data: {},
    };
    axios.defaults.headers.common["Authorization"] = token();
    const apiUrl =
      "http://localhost:8080/upc/v1/user-role/edit-client-password";
    const response = await axios.put(apiUrl, newData,config);
    if (response.status == 200) {
      const tab = JSON.parse(localStorage.getItem("notifications"));
      let newTab;
      const message = {
        message: `Pomyślnie ustawiono nowe hasło`,
        type: "SUCCESS",
      };
      if (tab) {
        newTab = [...tab, message];
      } else {
        newTab = [message];
      }

      window.localStorage.setItem("notifications", JSON.stringify(newTab));
      window.dispatchEvent(new Event("storage"));
      window.location.reload();
    }
  }

  async function handleSave(e) {
    e.preventDefault();
    const newData = {
      uuid: props.userData.uuid,
      phoneNumber: phoneNumber,
      email: props.userData.email,
      address: props.userData.address,
      balance: props.userData.balance,
      firstName: props.userData.firstName,
      lastName: props.userData.lastName,
      nip: props.userData.nip,
      isBusinessClient: props.userData.isBusinessClient,
    };
    
    const apiUrl = "http://localhost:8080/upc/v1/user-role/edit-client";
    const response = await axios.put(apiUrl, newData);
    if (response.status === 200) {
      const tab = JSON.parse(localStorage.getItem("notifications"));
      let newTab;
      const message = {
        message: `Pomyślnie zmieniono dane`,
        type: "SUCCESS",
      };
      if (tab) {
        newTab = [...tab, message];
      } else {
        newTab = [message];
      }

      window.localStorage.setItem("notifications", JSON.stringify(newTab));
      window.dispatchEvent(new Event("storage"));
      window.location.reload();
    }
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
        <div className=" justify-center align-middle items-center mr-8 w-1/5 xl:w-[10%]">
              <FaUserCircle className="self-center flex w-full h-full text-gray-300"> </FaUserCircle>
            </div>
          <div className="flex flex-col">
            <div className="text-3xl xl:text-lg text-amber-500 ml-3">
              {props.userData.firstName} {props.userData.lastName}
            </div>
          </div>
        </div>
        <div className="flex flex-col whitespace-nowrap p-8">
          <div className="flex flex-col p-3 bg-yellow-800 rounded-md text-xl text-center w-full mb-5">
            Dane klienta
          </div>
          <form onSubmit={handleSave}> 
            <div className="flex flex-col xl:flex-row mb-4 justify-between">
              <label for="Telefon komórkowy" className="text-lg text-white">Telefon komórkowy</label>
              <input
                disabled={isDisabledData}
                pattern="[0-9]{9}"
                id="Telefon komórkowy"
                title="Numer telefonu powinien składać się z 9 cyfr."
                required
                className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 ${
                  isDisabledData ? "bg-blue-gray-700" : "bg-blue-gray-600"
                }  w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
                value={loading ? props.userData.phoneNumber : phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
              />
            </div>
            <div className="flex flex-col xl:flex-row mb-4 justify-between">
              <label className="text-lg text-white">Adres e-mail</label>
              <input
                type="email"
                className="px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg"
                value={props.userData.email}
                readOnly
              />
            </div>

            <div className="flex flex-col xl:flex-row mb-4 justify-between">
              <label for="Adres" className="text-lg text-white">Adres</label>
              <input
                id="Adres"
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
              <label for="PESEL" className="text-lg text-white">PESEL</label>
              <input
                id="PESEL"
                className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 bg-blue-gray-700 w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
                value={props.userData.pesel}
                readOnly
              />
            </div>

            <div className="flex flex-row w-full">
              {isDisabledData && (
                <Button
                  color="deep-orange"
                  disabled={!isDisabledPassword}
                  className="w-full xl:w-1/2 ml-auto"
                  onClick={() => {
                    setIsDisabledData(false);
                    setPhoneNumber(props.userData.phoneNumber);
                    setLoading(false);
                  }}
                >
                  Zmień dane
                </Button>
              )}
              {!isDisabledData && (
                <Button
                  color="deep-orange"
                  className="w-1/2 xl:w-1/4 ml-auto"
                  onClick={() => {
                    setPhoneNumber(props.userData.phoneNumber);
                    setIsDisabledData(true);
                    setLoading(false);
                  }}
                >
                  Anuluj
                </Button>
              )}
              {!isDisabledData && (
                <Button color="amber" type="submit" className="w-1/2 xl:w-1/4">
                  Zapisz
                </Button>
              )}
            </div>
          </form>
          <div className="flex flex-col p-3 bg-yellow-800 rounded-md text-xl  text-center w-full mt-5 mb-5">
            Zmiana hasła
          </div>
          <form onSubmit={handlePasswordChange}>
            <div className="flex flex-col xl:flex-row mb-4 justify-between">
              <label for="Nowe hasło" className="text-lg text-white">Nowe hasło</label>
              <input
                type="password"
                minLength={8}
                id="Nowe hasło"
                disabled={isDisabledPassword}
                pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
                title="Hasło powino zawierać: conajmniej 8 znaków, conajmniej 1 wielką litere, conajmniej 1 cyfrę!"
                className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 invalid:border-red-500 invalid:outline-red-500 ${
                  isDisabledPassword ? "bg-blue-gray-700" : "bg-blue-gray-600"
                } w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
                required
              />
            </div>

            <div className="flex flex-col xl:flex-row mb-4 justify-between">
              <label for="Powtórz hasło" className="text-lg text-white">Powtórz hasło</label>
              <input
                type="password"
                id="Powtórz hasło"
                minLength={8}
                disabled={isDisabledPassword}
                pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"
                title="Hasło powino zawierać: conajmniej 8 znaków, conajmniej 1 wielką litere, conajmniej 1 cyfrę!"
                className={`px-2 py-2 border drop-shadow-lg border-blue-gray-500 invalid:border-red-500 invalid:outline-red-500 
              ${
                confirmNewPassword.length > 0 &&
                newPassword != confirmNewPassword
                  ? "border-red-500 outline-red-500"
                  : "border-gray-500 outline-gray-500"
              }
              ${
                isDisabledPassword ? "bg-blue-gray-700" : "bg-blue-gray-600"
              } w-full xl:w-1/2 rounded-sm text-amber-500 text-lg`}
                value={confirmNewPassword}
                onChange={(e) => setConfirmNewPassword(e.target.value)}
                required
              />
            </div>
            <div className="flex flex-col w-full">
              {confirmNewPassword.length > 0 &&
              newPassword != confirmNewPassword ? (
                <p className="text-red-500 text-xs ml-auto mr-5 mb-2">
                  Hasła nie zgadzają się
                </p>
              ) : null}
              <div className="flex flex-row w-full">
                {isDisabledPassword && (
                  <Button
                    color="deep-orange"
                    disabled={!isDisabledData}
                    className="w-full xl:w-1/2 ml-auto"
                    onClick={() => {
                      setIsDisabledPassword(false);
                      setPhoneNumber(props.userData.phoneNumber);
                      setLoading(false);
                    }}
                  >
                    Zmień hasło
                  </Button>
                )}
                {!isDisabledPassword && (
                  <Button
                    color="deep-orange"
                    className="w-1/2 xl:w-1/4 ml-auto"
                    onClick={() => {
                      setPhoneNumber(props.userData.phoneNumber);
                      setIsDisabledPassword(true);
                      setLoading(false);
                    }}
                  >
                    Anuluj
                  </Button>
                )}
                {!isDisabledPassword && (
                  <Button
                    type="submit"
                    color="amber"
                    disabled={newPassword !== confirmNewPassword}
                    className="w-1/2 xl:w-1/4"
                  >
                    Zapisz
                  </Button>
                )}
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
