import { Link } from "react-router-dom";

export default function Balance() {
  return (
    <div className="w-full xl:w-1/4 h-1/3">
        <div className="text-xl text-blue-500">
            Płatności
        </div>
      <div className="flex flex-col bg-blue-gray-700 h-full overflow-y-auto">
        <div className="flex flex-row p-4 border-b border-blue-gray-600">
          <text className="flex-grow text-white">Stan salda</text>
        </div>
        <div className="flex flex-col items-center justify-center p-4 h-full">
          <div className="flex flex-row w-full h-1/3 m-4 bg-green-500 shadow-md shadow-green-700 justify-center align-middle">
            <div className="flex flex-col justify-center">
              <div className=" text-5xl  text-white">0,00 zł</div>
            </div>
          </div>
          <div className="flex flex-row w-full m-4 h-1/3 bg-blue-gray-800 justify-center align-middle">
            <Link className="flex flex-col justify-center" to="/invoices">
              <div className="text-lg text-blue-300 align-middle">
                Wszystkie faktury
              </div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
