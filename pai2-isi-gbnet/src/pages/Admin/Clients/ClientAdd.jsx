import React, { useState } from "react";

export default function ClientAdd() {
  const [isBusiness, setIsBusiness] = useState(false);
  return (
    <div className="flex flex-col h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2 ">
        <span className="text-xl text-gray-800 font-semibold">
          Dodaj klienta
        </span>
      </div>
      <form className="flex flex-col p-8 bg-gray-200 h-full justify-between">
        <div className="grid gap-10 px-48 md:grid-cols-2">
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Imię</label>
            <input
              type="text"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Nazwisko</label>
            <input
              type="text"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900 px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Adres e-mail
            </label>
            <input
              type="email"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Numer Telefonu
            </label>
            <input
              type="tel"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Hasło</label>
            <input
              type="password"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Powtórz hasło
            </label>
            <input
              type="password"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">PESEL</label>
            <input
              type="number"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">NIP</label>
            <input
              type="number"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              disabled={!isBusiness}
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Adres</label>
            <input
              type="text"
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
