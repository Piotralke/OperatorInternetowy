import { Textarea } from "@material-tailwind/react";
import axios from "axios";
import jwt from "jwt-decode";
import { useAuthHeader } from "react-auth-kit";
import { useRef, useState } from "react";


export default function AddReport() {
    const token = useAuthHeader();
    const [description,setDescription] = useState()
    async function handleSubmit() {        
        const user = jwt(token());
        const data={
          email :  user.sub,
          description : description
        }
        const apiUrl = 'http://localhost:8080/upc/unsecured/v1/save-user-problem';
              const response = await axios.post(apiUrl, data);
              console.log(response)
              
      }
  return (
    <form className="w-full flex flex-col flex-grow bg-blue-gray-100" onSubmit={handleSubmit}>
      <div className="flex flex-col w-full items-center bg-blue-gray-300 p-2">
        <span className="text-xl text-blue-gray-800 font-semibold">
          NOWE ZGŁOSZENIE
        </span>
      </div>
      <div className="w-full flex flex-col items-center justify-center mt-10">
        <div className="w-1/2">
          <Textarea className="text-xl" label="Opis problemu" onChange={(e) => {
            setDescription(e.target.value);
          }} rows={10}></Textarea>
        </div>
        <button
          className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500 m-2"
          type="submit"
        >
          Wyślij zgłoszenie
        </button>
      </div>
    </form>
  );
}
