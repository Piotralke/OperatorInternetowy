import { useRef, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { useSignIn } from "react-auth-kit";
import { useNavigate } from "react-router-dom";
import jwt from "jwt-decode"
export default function LoginPage() {
  const [registering, setRegistering] = useState(false);
  const [isBusiness, setIsBusiness] = useState(false);
  const loginRef = useRef();
  const passwordRef = useRef();
  const signIn = useSignIn();
  const navigate = useNavigate();
  async function handleLogin(event) {
    event.preventDefault();
    try {
      const data={
        email :  loginRef.current.value,
        password : passwordRef.current.value
      }

        const apiUrl = 'http://localhost:8080/upc/unsecured/v1/login';
        console.log(data)
        const response = await axios.post(apiUrl, data);
        console.log(response);

        signIn(
          {
              token: response.data,
              expiresIn:3600,
              tokenType: "Bearer",
              authState: response.status
          })
        // Otrzymujemy odpowiedź z serwera
        console.log(response.data); // Token JWT
        //localStorage.setItem('token',response.data);
        
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;

    // Wykonaj żądanie do chronionego endpointu
          const userData = jwt(response.data)
          if(userData.roles.includes("USER"))
          {
            navigate("/home")
          }
          else
            navigate("/admin")
  } catch (error) {
      console.error(error);
    }
  }
  return (
    <>
      {registering ? (
        <div className="flex flex-col w-full min-h-screen bg-gray-300 items-center">
          <div className="flex flex-col items-center justify-center mt-40 border border-black w-1/2 rounded-xl min-h-[50vh] bg-blue-gray-800">
            <text className="font-bold mb-3 text-xl text-blue-500">
              Rejestracja
            </text>
            <div className="flex flex-row w-full p-4">
              <div className="flex flex-col w-1/2 p-4 items-center justify-center">
                <div className="flex flex-row w-full space-x-2 m-3">
                  <input
                    className="p-3 w-1/2 border border-black rounded-xl"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="imię"
                  />
                  <input
                    className="p-3 w-1/2 border border-black rounded-xl"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="nazwisko"
                  />
                </div>
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="text"
                  id="login"
                  name="login"
                  placeholder="login"
                />
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="text"
                  id="login"
                  name="login"
                  placeholder="e-mail"
                />
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="password"
                  id="password"
                  name="password"
                  placeholder="haslo"
                />
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="password"
                  id="password"
                  name="password"
                  placeholder="potwierdź haslo"
                />
              </div>
              <div className="flex flex-col w-1/2 p-4 items-center justify-center">
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="text"
                  id="password"
                  name="password"
                  placeholder="pesel"
                />
                <input
                  className="p-3 m-3 w-full border border-black rounded-xl"
                  type="text"
                  id="password"
                  name="password"
                  placeholder="nr telefonu"
                />
                <div className="flex flex-row w-full space-x-2 m-3">
                  <input
                    className="p-3 border border-black rounded-xl w-3/5"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="ulica"
                  />
                  <input
                    className="p-3 w-2/5 border border-black rounded-xl"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="nr domu/lokalu"
                  />
                </div>
                <div className="flex flex-row w-full space-x-2 m-3">
                  <input
                    className="p-3 border border-black rounded-xl w-2/5"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="kod pocztowy"
                  />
                  <input
                    className="p-3  w-3/5 border border-black rounded-xl"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="miasto"
                  />
                </div>
                <div className="w-1/2 flex flex-row items-center space-x-2 p-2">
                  <input
                    type="checkbox"
                    onChange={() => {
                      setIsBusiness(!isBusiness);
                    }}
                  ></input>
                  <div className="text-white">Klient biznesowy</div>
                </div>
                {isBusiness ? (
                  <input
                    className="p-3 m-3 w-full border border-black rounded-xl"
                    type="text"
                    id="password"
                    name="password"
                    placeholder="nip"
                  />
                ) : null}
              </div>
            </div>

            <div className="flex flex-row w-1/2 space-x-3 my-3">
              <button
                className=" border border-blue-500 p-6 w-full rounded-xl text-blue-500"
                onClick={() => {
                  setRegistering(false);
                }}
              >
                Logowanie
              </button>
              <button className=" border border-blue-500 p-6 w-full text-white bg-blue-500 rounded-xl">
                Zarejestruj
              </button>
            </div>
          </div>
        </div>
      ) : (
        <form>
          <div className="flex flex-col w-full min-h-screen bg-gray-300 items-center">
            <div className="flex flex-col items-center justify-center mt-40 border border-black w-1/2 rounded-xl min-h-[50vh] bg-gray-800">
              <text className="font-bold mb-3 text-xl text-blue-500">
                Logowanie
              </text>
              <input
                className="p-3 m-3 w-1/2 border border-black rounded-xl"
                type="text"
                id="login"
                name="login"
                ref={loginRef}
                placeholder="login"
              />
              <input
                className="p-3 m-3 w-1/2 border border-black rounded-xl"
                type="password"
                id="password"
                name="password"
                ref={passwordRef}
                placeholder="haslo"
              />
              <div className="flex flex-row w-1/2 space-x-3 my-3">
                <button
                  className=" border border-blue-500 p-6 w-full rounded-xl text-blue-500"
                  onClick={() => {
                    setRegistering(true);
                  }}
                >
                  Rejestracja
                </button>
                <button
                  className=" border border-blue-500 p-6 w-full text-white bg-blue-500 rounded-xl text-center"
                  onClick={handleLogin}
                >
                  Zaloguj
                </button>
              </div>
              <div className="text-white text-xs">TU BEDZIE GOOGLE</div>
            </div>
          </div>
        </form>
      )}
    </>
  );
}
