import { Outlet, Link } from "react-router-dom";

export default function Layout() {

    return (
        <div className="flex flex-col justify-between h-screen">
            <div className='flex items-center w-full p-3 bg-gray-800'>
                <text className="text-xl font-bold text-white">Gb net</text>
            </div>
            <div className="flex flex-row w-full h-full">
                <div className="flex flex-col pt-5 pl-5 space-y-2 bg-gray-700 basis-1/5">
                    <Link className="w-full text-xl text-white hover:font-bold hover:text-blue-500" to="/home">Strona główna</Link>
                    <Link className="w-full text-xl text-white hover:font-bold hover:text-blue-500" to="/offers">Oferty</Link>
                </div>
                <Outlet></Outlet>
            </div>
            <div className="flex content-end bg-gray-700"></div>
        </div>

    )
}