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
      const data = jwt(token());
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/v1/user-role/user/${clientId}`,
        {
          params: {
            email: data.sub,
          },
        }
      );
      setUserOriginalData(protectedEndpointResponse.data);
      setUserData(protectedEndpointResponse.data);
    }
    fetchUser();
    setIsDisabled(true);
  }, [clientId]);

  async function handleSave(e) {
    e.preventDefault();

    const apiUrl = "http://localhost:8080/upc/v1/user-role/edit-client";
    const response = await axios.put(apiUrl, userData);
    if (response.status === 200) {
      const tab = JSON.parse(localStorage.getItem("notifications"));
      let newTab;
      const message = {
        message: `Pomyślnie zmieniono dane klienta`,
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

  const handleSelectChange = (event) => {
    const value = event.target.value;
    setUserData((prevState) => ({
      ...prevState,
      isBusinessClient: value,
    }));
  };

  return (
    <form
      onSubmit={handleSave}
      className="flex flex-direction flex-col bg-gray-100 w-full h-full"
    >
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
                pattern="[A-Z]{1}[a-z]{1,}"
                title="Imię zaczyna się z dużej litery."
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    firstName: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
                required
              />
            </div>
            <div className="flex flex-row  justify-between items-center">
              <a className="text-lg text-gray-700">Nazwisko</a>
              <input
                value={userData.lastName}
                disabled={isDisabled}
                pattern="[A-Z]{1}[a-z]{1,}"
                title="Nazwisko zaczyna się z dużej litery."
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    lastName: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
                required
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
                pattern="[0-9]{9}"
                maxLength={9}
                title="Numer telefonu powinien składać się z 9 cyfr."
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    phoneNumber: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
                required
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
                disabled
                readOnly
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">PESEL</a>
              <input
                value={userData.pesel}
                disabled
                readOnly
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">NIP</a>
              <input
                value={userData.nip}
                minLength={10}
                maxLength={10}
                pattern="[0-9]{10}"
                disabled={isDisabled || !userData.isBusinessClient}
                title="NIP powinien mieć 10 cyfr!"
                onChange={(e) => {
                  setUserData((prevState) => ({
                    ...prevState,
                    nip: e.target.value,
                  }));
                }}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
                required={userData.isBusinessClient}
              />
            </div>
            <div className="flex flex-row justify-between items-center">
              <a className="text-lg text-gray-700">Klient biznesowy</a>
              <select
                disabled={isDisabled}
                onChange={handleSelectChange}
                className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
              >
                <option
                  value={true}
                  selected={userData?.isBusinessClient ? true : false}
                >
                  TAK
                </option>
                <option
                  value={false}
                  selected={userData?.isBusinessClient ? false : true}
                >
                  NIE
                </option>
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
              type="submit"
            >
              Zapisz
            </button>
          </div>
        )}
      </div>
    </form>
  );
}
