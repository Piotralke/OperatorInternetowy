import { Outlet, Link } from "react-router-dom";

export default function Layout() {
  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <div className="flex items-center w-full p-3 bg-gray-800">
        <text className="text-xl font-bold text-white">Gb net</text>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="flex flex-col basis-1/5 min-h-full bg-gray-700 justify-between" >
          <div className="flex flex-col pt-5 pl-5  h-full">
            <Link
              className="w-full text-xl text-white hover:font-bold hover:text-blue-500"
              to="/home"
            >
              Strona główna
            </Link>
            <Link
              className="w-full text-xl text-white hover:font-bold hover:text-blue-500"
              to="/offers"
            >
              Oferty
            </Link>
          </div>
        </div>
        <Outlet></Outlet>
      </div>

      <div className="bg-gray-700 h-10 basis-1/12">Stopka</div>
    </div>
  );
}
