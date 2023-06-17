import React, { useRef, useState } from "react";
import axios from "axios";

export default function WorkerAdd() {
  const [selectedOption, setSelectedOption] = useState("B2B");
  const [NIP, setNIP] = useState(true);
  const [PESEL, setPESEL] = useState(true);
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const nameRef = useRef();
  const lastNameRef = useRef();
  const emaiRef = useRef();
  const phoneRef = useRef();
  const passwordRef = useRef();
  const passwordConfirmRef = useRef();
  const peselRef = useRef();
  const adddressRef = useRef();
  const workplaceRef = useRef();
  const salaryRef = useRef();
  const contractFormRef = useRef();
  const nipRef = useRef();

  const handleSelectChange = (event) => {
    setSelectedOption(event.target.value);
  };


  async function handleSubmit(e){
    e.preventDefault();
    const data = {
      firstName: nameRef.current.value,
      last_name: lastNameRef.current.value,
      email: emaiRef.current.value,
      password: passwordRef.current.value,
      address: adddressRef.current.value,
      workplace: workplaceRef.current.value,
      salary: salaryRef.current.value,
      contractForm: contractFormRef.current.value,
      phoneNumber: phoneRef.current.value,
      nip: contractFormRef.current.value=="B2B"? nipRef.current.value:null,
      pesel: peselRef.current.value,
    };
    console.log(data);
    const apiUrl = "http://localhost:8080/upc/unsecured/v1/employee-register";
     const response = await axios.post(apiUrl, data);
     console.log(response);
     if(response.status === 200)
     {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const message = {
          message:`Pomyślnie dodano nowego pracownika ${data.email}`,
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
          Dodaj pracownika
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
            <label className="text-md font-bold text-gray-700 ">Adres</label>
            <input
              type="text"
              ref={adddressRef}
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
              ref={workplaceRef}
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
            >
              <option value="Kielce">Kielce</option>
              <option value="Warszawa">Warszawa</option>
              <option value="Kraków">Kraków</option>
              <option value="Morawica">Morawica</option>
            </select>
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Pensja</label>
            <input
              type="number"
              ref={salaryRef}
              min={0}
              step="0.01"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
            />
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">
              Rodzaj umowy
            </label>
            <select
              type="text"
              ref={contractFormRef}
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              onChange={handleSelectChange}
              value={selectedOption}
            >
              <option value="B2B">B2B</option>
              <option value="PERMANENT">PERMANENT</option>
            </select>
          </div>
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">NIP</label>
            <input
              required = {selectedOption == "B2B" ? true : false}
              disabled={selectedOption == "B2B" ? false : true}
              type="text"
              ref={nipRef}
              minLength={10}
              maxLength={10}
              pattern="[0-9]{10}"
              title="NIP powinien mieć 10 cyfr!"
              className={`border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1`}
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
