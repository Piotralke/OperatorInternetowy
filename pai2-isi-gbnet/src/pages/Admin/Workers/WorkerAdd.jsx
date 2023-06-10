import React from "react";

export default function WorkerAdd() {
  return (
    <div className="flex flex-col h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2 ">
        <span className="text-xl text-gray-800 font-semibold">
          Dodaj pracownika
        </span>
      </div>
      <form className="flex flex-col p-8 bg-gray-200 h-full justify-between">
        <div className="grid gap-10 px-48 md:grid-cols-2">
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Imię</label>
            <input
              type="text"
              id="first_name"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Nazwisko</label>
            <input
              type="text"
              id="last_name"
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
              id="company"
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
              id="phone"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Hasło</label>
            <input
              type="password"
              id="website"
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
              id="visitors"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">PESEL</label>
            <input
              type="number"
              id="visitors"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">NIP</label>
            <input
              type="number"
              id="visitors"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Adres</label>
            <input
              type="text"
              id="visitors"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Miejsce pracy
            </label>
            <select
              type="text"
              id="email"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
            >
              <option value="Kielce">Kielce</option>
              <option value="Warszawa">Warszawa</option>
              <option value="Kraków">Kraków</option>
              <option value="Morawica">Morawica</option>
            </select>
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Rodzaj umowy
            </label>
            <select
              type="text"
              id="password"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
            >
              <option value="b2b">b2b</option>
              <option value="b2c">b2c</option>
            </select>
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Pensja</label>
            <input
              type="number"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
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
