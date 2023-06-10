import { Outlet, Link, useNavigate} from "react-router-dom";
import { ImHome } from 'react-icons/im'
import { CgProfile } from 'react-icons/cg'
import { FaFileInvoiceDollar } from 'react-icons/fa'
import { RiCopyleftLine } from 'react-icons/ri'
import { MdLocalOffer, MdOutlineLocalOffer,MdReportGmailerrorred } from 'react-icons/md'
import { useSignOut } from "react-auth-kit";
export default function ClientLayout() {
  const navigate = useNavigate();
  const signOut = useSignOut();
  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <div className="flex flex-row items-center w-full p-3 bg-blue-gray-800 sticky top-0">
        <text className="text-xl flex-grow font-bold text-white">Gb Net</text>
        <button className="text-xl bg-red-600 p-1 rounded-lg font-bold  text-white hover:bg-red-500" onClick={()=>signOut()}>Wyloguj</button>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="flex flex-col basis-1/5 min-h-full bg-blue-gray-700 justify-between " >
          <div className="flex flex-col p-7 space-y-8 items-center h-full fixed ">
            <button
              className="group w-full  text-white font-bold  flex flex-row items-center justify-center space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/home')}
            >
              <ImHome className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="text-xl group-hover:text-blue-500 truncate">STRONA GŁÓWNA</a>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/offers')}
            >
              <MdLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="group-hover:text-blue-500">OFERTY</a>
            </button>
            
            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/products')}
            >
              <MdOutlineLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="group-hover:text-blue-500">PRODUKTY</a>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/invoices')}
            >
              <FaFileInvoiceDollar className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="group-hover:text-blue-500">FAKTURY</a>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/profile')}
            >
              <CgProfile className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="group-hover:text-blue-500">PROFIL</a>
            </button>
            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={()=>navigate('/reports')}
            >
              <MdReportGmailerrorred className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500"/>
              <a className="group-hover:text-blue-500">ZGŁOSZENIA</a>
            </button>
          </div>
        </div>
        <div className="basis-4/5 min-h-full ">
          <Outlet></Outlet>
        </div>
      </div>

      <div className="flex flex-col bg-blue-gray-700 h-10 basis-1/12 items-center">
        <a className="flex flex-row text-blue-gray-100 items-center"> <span><RiCopyleftLine/></span> Copyleft by Barański, Dziewięcki, Rudnicki and Spychalski. 2023</a>
      </div>
    </div>
  );
}
