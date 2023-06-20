import axios from "axios";
import { useSignIn } from "react-auth-kit";
import { useNavigate } from "react-router-dom";
import jwt from "jwt-decode";
import React, { useRef, useState } from "react";
import { RiCopyleftLine } from "react-icons/ri";
import { FcGoogle } from "react-icons/fc";
import { Button,Alert } from "@material-tailwind/react";
export default function LoginPage() {
  const [registering, setRegistering] = useState(false);
  const loginRef = useRef();
  const passwordRef = useRef();
  const signIn = useSignIn();
  const navigate = useNavigate();
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [isBusiness, setIsBusiness] = useState(false);
  const [isSuccess, setIsSuccess] = useState(false);
  const [isError,setIsError] = useState(false);
  const nameRef = useRef();
  const lastNameRef = useRef();
  const emaiRef = useRef();
  const phoneRef = useRef();
  const passwordConfirmRef = useRef();
  const peselRef = useRef();
  const adddressRef = useRef();
  const nipRef = useRef();

  async function handleSubmit(e) {
    e.preventDefault();
    const data = {
      firstName: nameRef.current.value,
      lastName: lastNameRef.current.value,
      email: emaiRef.current.value,
      password: passwordRef.current.value,
      address: adddressRef.current.value,
      phoneNumber: phoneRef.current.value,
      nip: isBusiness ? nipRef.current.value : null,
      pesel: peselRef.current.value,
      isBusinessClient: isBusiness,
    };
    const apiUrl = "http://localhost:8080/upc/unsecured/v1/client-register";
    const response = await axios.post(apiUrl, data);
    if (response.status === 200) {
      
      const tab = JSON.parse(localStorage.getItem("notifications"));
      let newTab;
      const message = {
        message: `Pomyślnie dodano nowego użytkownika ${data.email}`,
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
  const [info, setInfo] = useState('');
  async function handleLogin(event) {
    event.preventDefault();
    
    try {
      const data = {
        email: loginRef.current.value,
        password: passwordRef.current.value,
      };
      const apiUrl = "http://localhost:8080/upc/unsecured/v1/login";
      const response = await axios.post(apiUrl, data).then(
        setInfo('Zalogowano')
      );
      signIn({
        token: response.data,
        expiresIn: 3600,
        tokenType: "Bearer",
        authState: {data},
      });

      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${response.data}`;

      const userData = jwt(response.data);

      if (userData?.role.includes("USER")) {
       
        navigate("/home");
      } else navigate("/admin");
    } catch (error) {
      console.error(error);
      setIsError(true)
    }
  }
  return (
    <>
      {registering ? (
        <div className="flex flex-col w-full h-full justify-center  items-center self-cecnter bg-blue-gray-600">
          <div className="flex flex-row w-full items-center justify-between bg-blue-gray-900 p-2 ">
            <span className="text-xl text-white font-semibold">Gb Net</span>
            <span className="text-xl text-white font-semibold">
              Rejestracja
            </span>
            <span className="text-xl text-white font-semibold">3ID12A</span>
          </div>
          <form
            onSubmit={handleSubmit}
            className="flex flex-col my-32 rounded-3xl drop-shadow-2xl py-8 px-4 bg-blue-gray-100 h-full justify-between"
          >
            <div className="grid gap-10 px-48 md:grid-cols-2">
              <div className="flex flex-col">
                <label className="text-md font-bold text-gray-700 " for="imie">Imię</label>
                <input
                  id="imie"
                  type="text"
                  ref={nameRef}
                  pattern="[A-Z]{1}[a-z]{1,}"
                  title="Imię zaczyna się z dużej litery."
                  className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500   rounded-md mt-1 text-gray-900  px-4 py-1"
                  required
                />
              </div>
              <div className="flex flex-col">
                <label className="text-md font-bold text-gray-700 " for="nazwisko">
                  Nazwisko
                </label>
                <input
                id="nazwisko"
                  type="text"
                  ref={lastNameRef}
                  pattern="[A-Z]{1}[a-z]{1,}"
                  title="Nazwisko zaczyna się z dużej litery."
                  className="border-2 border-gray-500  invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900 px-4 py-1"
                  required
                />
              </div>
              <div className="flex flex-col">
                <label className="text-md font-bold text-gray-700 "for="email">
                  Adres e-mail
                </label>
                <input
                id="email"
                  type="email"
                  ref={emaiRef}
                  className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1"
                  required
                />
              </div>
              <div className="flex flex-col">
                <label className="text-md font-bold text-gray-700 " for="tele">
                  Numer Telefonu
                </label>
                <input
                  id="tele"
                  type="text"
                  ref={phoneRef}
                  pattern="[0-9]{9}"
                  title="Numer telefonu powinien składać się z 9 cyfr."
                  className="border-2 border-gray-500 invalid:border-red-500 invalid:outline-red-500 rounded-md mt-1 text-gray-900  px-4 py-1"
                  required
                />
              </div>
              <div className="flex flex-col">
                <label className="text-md font-bold text-gray-700" for="pass">
                  Hasło
                </label>
                <input
                  type="password"
                  id="pass"
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
                <label className="text-md font-bold text-gray-700 ">
                  PESEL
                </label>
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
                  required={isBusiness ? true : false}
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
                <label className="text-md font-bold text-gray-700 ">
                  Adres
                </label>
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
            <div className="flex flex-col xl:flex-row w-full xl:space-x-2 mt-8 items-center">
              <button
                onClick={() => {
                  setRegistering(false);
                }}
                className="bg-deep-orange-300 py-2 w-1/4 rounded-md text-gray-900 text-xl shadow-md hover:bg-deep-orange-500"
              >
                Logowanie
              </button>
              <button
                type="submit"
                className="bg-amber-300 py-2 w-3/4 rounded-md text-gray-900 text-xl shadow-md hover:bg-amber-500"
              >
                Zarejestruj
              </button>
            </div>
          </form>
          <div className="flex flex-col bg-blue-gray-800 h-10 w-full items-center">
            <p className="flex flex-row text-blue-gray-100 items-center">
              {" "}
              <span>
                <RiCopyleftLine />
              </span>{" "}
              Copyleft by Barański, Dziewięcki, Rudnicki and Spychalski. 2023
            </p>
          </div>
        </div>
      ) : ( ///###################################### LOGOWANIE ###################################################
        <form>
          <div className="flex flex-col w-full h-screen  items-center self-cecnter bg-blue-gray-200">
          <div className="flex flex-row w-full items-center justify-between bg-blue-gray-900 p-2 ">
            <span className="text-xl text-white font-semibold">Gb Net</span>
            <span className="text-xl text-white font-semibold">
              Logowanie
            </span>
            <span className="text-xl text-white font-semibold">3ID12A</span>
          </div>
            <div className="flex flex-col items-center justify-center mt-40 border border-black w-1/2 rounded-xl min-h-[50vh] bg-blue-gray-600 drop-shadow-2xl p-2">
              <p className="font-bold mb-3 text-xl text-amber-500">
                Wprowadź dane do logowania
              </p>
              <input
                className="p-3 m-3 xl:w-1/2 border border-black rounded-xl"
                type="text"
                id="login"
                name="login"
                ref={loginRef}
                placeholder="login"
              />
              <input
                className="p-3 m-3 xl:w-1/2 border border-black rounded-xl"
                type="password"
                id="password"
                name="password"
                ref={passwordRef}
                placeholder="haslo"
              />
              <div className="flex flex-col xl:flex-row w-full xl:w-1/2 xl:space-x-3 my-3">
                <Button
                color="deep-orange"
                  className=" p-6 w-full "
                  onClick={() => {
                    setRegistering(true);
                  }}
                >
                  Rejestracja
                </Button>
                <Button
                color="amber"
                  className="p-6 w-full text-center"
                  onClick={handleLogin}
                >
                  Zaloguj
                </Button>
              </div>
              <Button
                size="lg"
                variant="outlined"
                color="blue-gray"
                className="flex items-center gap-3 bg-white"
                onClick={ async ()=>{
                   window.location.href = "http://localhost:8080/login"; 
                  console.log(response);
                }}
              >
                <FcGoogle className="w-6 h-6"/>
                Continue with Google
              </Button>
              
            </div>
            <Alert open={isError} color="red" className="mt-4 mx-auto w-1/3 items-center justify-center">Podano błędne dane logowania</Alert>
            <div className="flex flex-col bg-blue-gray-800 h-10 w-full mt-auto pt-2 items-center">
            <p className="flex flex-row text-blue-gray-100 items-center">
              {" "}
              <span>
                <RiCopyleftLine />
              </span>{" "}
              Copyleft by Barański, Dziewięcki, Rudnicki and Spychalski. 2023
              {info? <span className="text-blue-gray-800">
                {info}
              </span> :null}
            </p>
          </div>
          </div>
        </form>
        
      )}
    </>
  );
}
