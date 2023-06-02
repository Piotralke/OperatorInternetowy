import userPic from "../assets/userPic.jpg";
import { Link } from "react-router-dom";
import { AiOutlineInfoCircle } from "react-icons/ai";
export default function AccountData() {
  const user = {
    accountId: "213742069",
    name: "Jan",
    surname: "Dyrduł",
    pic: userPic,
    address: "ul. Johansona 69, 21-370 Busko Zdrój",
  };

  return (
    <div className="w-1/4 h-1/3">
      <div className="text-xl text-blue-500">Moje konto</div>
      <div className="flex flex-col bg-gray-700 h-full">
        <div className="flex flex-row p-4 border-b border-gray-600">
          <text className="flex-grow text-white">Numer klienta</text>
          <text className="text-blue-300 font-bold">{user.accountId}</text>
        </div>
        <div className="flex flex-col items-center justify-center p-4">
          <div className="flex flex-row w-full p-5 m-4 bg-gray-800 ">
            <div className="basis-1/5 justify-center align-middle">
              <img className="w-10 h-10 rounded-full" src={user.pic}></img>
            </div>
            <div className="flex flex-col basis-4/5">
              <div className="text-lg text-blue-300">
                {user.name} {user.surname}
              </div>
              <div className="text-md text-white">Adres</div>
              <div className="text-lg text-blue-300 truncate">{user.address}</div>
            </div>
          </div>
          <div className="flex flex-row">
            <AiOutlineInfoCircle className="w-8 h-8 text-blue-300"></AiOutlineInfoCircle>
            <div className="text-blue-300 text-md ml-2">
              Przejdz do zakładki MOJE KONTO, aby wyświetlić lub edytować dane
            </div>
          </div>
          <div className="flex flex-row w-full m-4 h-20 bg-gray-800 justify-center align-middle">
            <Link className="flex flex-col justify-center" to="/profile">
              <div className="text-lg text-blue-300 align-middle">
                Moje konto
              </div>
            </Link>
          </div>
          <div></div>
        </div>
      </div>
    </div>
  );
}
