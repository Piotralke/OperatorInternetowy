import { Outlet, Link, useNavigate} from "react-router-dom";
import { ImHome } from 'react-icons/im'
import { CgProfile } from 'react-icons/cg'
import { FaFileInvoiceDollar } from 'react-icons/fa'
import { MdLocalOffer, MdOutlineLocalOffer } from 'react-icons/md'
export default function AdminPage() {
  const navigate = useNavigate();
  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <div className="flex items-center w-full p-3 bg-gray-800 sticky top-0">
        <text className="text-xl font-bold text-white">Gb net</text>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="flex flex-col basis-1/5 min-h-full bg-gray-700 justify-between items-center" >
          <div className="flex flex-col pt-5 space-y-8 items-center h-full fixed ">
            
          </div>
        </div>
        {/* <Outlet></Outlet> */}
      </div>

      <div className="bg-gray-700 h-10 basis-1/12">Stopka</div>
    </div>
  );
}
