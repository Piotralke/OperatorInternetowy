import userPic from "../assets/userPic.jpg";
import { useState, useEffect } from "react";

export default function UserData() {
  const user = {
    name: "Jan",
    surname: "Dyrduł",
    phoneNumber: "731-007-454",
    landlineNumber: "41-314-23-12",
    email: "dawid.noob2000@gmail.com",
    town: "Kielce",
    zipCode: "25-345",
    street: "ul.Mazurska 66",
    houseNumber: "",
    apartamentNumber: "79",
    pic: userPic,
  };

  const [isHouse, setIsHouse] = useState(false);

  useEffect(() => {
    const handleApartamentOrHouse = () => {
      if (user.houseNumber === "") {
        setIsHouse(false);
      } else {
        setIsHouse(true);
      }
    };
    handleApartamentOrHouse();
  }, []);

  return (
    <div className="flex flex-col bg-gray-700 my-7 basis-3/5">
      <div className="flex flex-row p-4 border-b border-gray-600">
        <a className="text-white text-xl font-bold underline underline-offset-1 text-white">Moje dane</a>
        <text className="text-blue-300">{user.accountId}</text>
      </div>
      <div className="flex flex-col p-4">
        <div className="flex flex-row drop-shadow-lg bg-gray-800 p-4">
          <img className="w-10 h-10 rounded-full" src={user.pic} alt="User" />
          <div className="flex flex-col">
            <div className="text-lg text-blue-300 ml-3">
              {user.name} {user.surname}
            </div>
            {!isHouse ? (
              <text className="text-slate-300 mt-3 ml-3 flex flex-col">
                Adres nabywcy: {user.street}/{user.apartamentNumber}{" "}
                {user.zipCode} {user.town}
              </text>
            ) : (
              <text className="text-slate-300 mt-3 ml-3">
                Adres nabywcy: {user.street} {user.houseNumber} {user.zipCode}{" "}
                {user.town}
              </text>
            )}
          </div>
        </div>
        <div className="flex flex-col whitespace-nowrap p-8">
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Telefon komórkowy</a>
            <input
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.phoneNumber}
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <text className="text-lg text-white">Telefon stacjonarny</text>
            <input
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-sm  text-blue-400  text-lg"
              value={user.landlineNumber}
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <text className="text-lg text-white">Adres e-mail</text>
            <input
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.email}
            />
          </div>
          <div className="mt-7">
            <a className=" text-2xl  text-blue-400">Adres Korespondencyjny</a>
          </div>
          <div className="flex flex-row mb-4 justify-between mt-9">
            <a className="text-lg text-white">Kod pocztowy</a>
            <input
              className="px-2 py-2 drop-shadow-lg border border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.zipCode}
              disabled
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Miejscowość</a>
            <input
              className="px-2 py-2 drop-shadow-lg border border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.town}
              disabled
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Ulica</a>
            <input
              className="px-2 py-2 drop-shadow-lg border border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.street}
              disabled
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Numer domu</a>
            <input
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.houseNumber}
              disabled
            />
          </div>
          <div className="flex flex-row mb-4 justify-between">
            <a className="text-lg text-white">Numer mieszkania</a>
            <input
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-sm text-blue-400  text-lg"
              value={user.apartamentNumber}
              disabled
            />
          </div>
          <button className="bg-blue-900 drop-shadow-md rounded-md text-white font-bold h-12 w-1/4 text-md  ml-auto">Edytuj</button>
          <button className="bg-gray-800 drop-shadow-md rounded-md text-white font-bold text-md h-12 mt-4">Zapisz</button>
        </div>
      </div>
    </div>
  );
}
