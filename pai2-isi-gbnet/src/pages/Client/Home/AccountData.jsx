import userPic from "../../../assets/userPic.jpg";
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
    <div className="w-full xl:w-1/4 h-1/3">
      <div className="text-xl text-white">Moje konto</div>
      <div className="flex flex-col bg-blue-gray-700 h-full overflow-y-auto">
        <div className="flex flex-row p-4 border-b border-blue-gray-600">
          <text className="flex-grow text-white">Numer klienta</text>
          <text className="text-amber-500 font-bold">{user.accountId}</text>
        </div>
        <div className="flex flex-col items-center justify-center p-4 h-full w-full">
          <div className="flex flex-row w-full p-5 m-4 bg-blue-gray-800 items-center">
            <div className=" justify-center align-middle items-center mr-2 w-1/5 xl:w-1/3">
              <img className="self-center rounded-full " src={user.pic}></img>
            </div>
            <div className="flex flex-col w-4/5 xl:w-2/3">
              <div className="text-sm text-amber-500">
                {user.name} {user.surname}
              </div>
              <div className="text-sm text-white">Adres</div>
              <div className="text-sm text-amber-500 truncate">{user.address}</div>
            </div>
          </div>
          <div className="flex flex-row">
            <AiOutlineInfoCircle className="w-[2vw] h-[2vh] text-amber-500"></AiOutlineInfoCircle>
            <div className="text-amber-500 text-sm ml-2">
              Przejdz do zakładki MOJE KONTO, aby wyświetlić lub edytować dane
            </div>
          </div>
          <div className="flex flex-row w-full m-4 h-1/3 bg-blue-gray-800 justify-center align-middle">
            <Link className="flex flex-col justify-center" to="/profile">
              <div className="text-lg text-amber-500 align-middle">
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
