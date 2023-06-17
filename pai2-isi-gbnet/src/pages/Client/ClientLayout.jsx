import { Outlet, Link, useNavigate } from "react-router-dom";
import { ImHome } from "react-icons/im";
import { CgProfile } from "react-icons/cg";
import { BiMenuAltLeft } from "react-icons/bi";
import { FaFileInvoiceDollar } from "react-icons/fa";
import { RiCopyleftLine } from "react-icons/ri";
import Notifications from "../../components/Notifications"
import {
  MdLocalOffer,
  MdOutlineLocalOffer,
  MdReportGmailerrorred,
} from "react-icons/md";
import { useSignOut } from "react-auth-kit";
import {
  Drawer,
  Button,
  Typography,
  IconButton,
} from "@material-tailwind/react";
import { useState,useEffect,useCallback } from "react";
export default function ClientLayout() {
  const [notifications,setNotifications] = useState();
  const [open, setOpen] = useState(false);
  const openDrawer = () => setOpen(true);
  const closeDrawer = () => setOpen(false);
  const navigate = useNavigate();
  const signOut = useSignOut();

  useEffect(()=>{
    const not = JSON.parse(localStorage.getItem("notifications"));
    if(not){
      setNotifications(not);
    }
  },[])
  useEffect(()=>{
    const handleStorageChange = (event) =>{
      console.log("LISTENER przed if")
      if(event.key="notifications"){
        console.log("LISTENER")
        const newNotifications = JSON.parse(localStorage.getItem("notifications"));
        setNotifications(newNotifications);
        }
    }

    window.addEventListener('storage',handleStorageChange);

    return()=>{
      window.removeEventListener('storage',handleStorageChange);
    }

  },[])

  const handleDelete = useCallback(() =>{
    console.log("DELETE")
    const newNotifications = JSON.parse(localStorage.getItem("notifications"));
    const updatedNotifications = newNotifications.slice(1); // Usuwanie pierwszego powiadomienia
    console.log(updatedNotifications)
        setNotifications(updatedNotifications);
        window.localStorage.setItem('notifications', JSON.stringify(updatedNotifications));
  }, [])





  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <Drawer
        open={open}
        onClose={closeDrawer}
        className="p-4 bg-blue-gray-700"
      >
        <div className="flex flex-col p-7 space-y-8 items-center h-full fixed ">
          <button
            className="group w-full  text-white font-bold  flex flex-row items-center justify-center space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/home")}}
          >
            <ImHome className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="text-xl group-hover:text-blue-500 truncate">
              STRONA GŁÓWNA
            </p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/offers")}}
          >
            <MdLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="group-hover:text-blue-500">OFERTY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/products")}}
          >
            <MdOutlineLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="group-hover:text-blue-500">PRODUKTY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/invoices")}}
          >
            <FaFileInvoiceDollar className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="group-hover:text-blue-500">FAKTURY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/profile")}}
          >
            <CgProfile className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="group-hover:text-blue-500">PROFIL</p>
          </button>
          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer()
              navigate("/reports")}}
          >
            <MdReportGmailerrorred className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
            <p className="group-hover:text-blue-500">ZGŁOSZENIA</p>
          </button>
        </div>
      </Drawer>
      <div className="flex flex-row items-center w-full p-3 bg-blue-gray-800 sticky top-0 space-x-4">
        <BiMenuAltLeft
          onClick={openDrawer}
          size={32}
          className="2xl:hidden text-white hover:cursor-pointer"
        ></BiMenuAltLeft>

        <text className="text-xl flex-grow font-bold text-white">Gb Net</text>
        <button
          className="text-xl bg-red-600 p-1 rounded-lg font-bold  text-white hover:bg-red-500"
          onClick={() => signOut()}
        >
          Wyloguj
        </button>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="hidden 2xl:flex flex-col basis-1/5 min-h-full bg-blue-gray-700 justify-between ">
          <div className="flex flex-col p-7 space-y-8 items-center h-full fixed ">
            <button
              className="group w-full  text-white font-bold  flex flex-row items-center justify-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/home")}
            >
              <ImHome className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="text-xl group-hover:text-blue-500 truncate">
                STRONA GŁÓWNA
              </p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/offers")}
            >
              <MdLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="group-hover:text-blue-500">OFERTY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/products")}
            >
              <MdOutlineLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="group-hover:text-blue-500">PRODUKTY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/invoices")}
            >
              <FaFileInvoiceDollar className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="group-hover:text-blue-500">FAKTURY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/profile")}
            >
              <CgProfile className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="group-hover:text-blue-500">PROFIL</p>
            </button>
            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/reports")}
            >
              <MdReportGmailerrorred className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-blue-500" />
              <p className="group-hover:text-blue-500">ZGŁOSZENIA</p>
            </button>
          </div>
        </div>
        <div className="2xl:basis-4/5 w-full min-h-full ">
          <Outlet></Outlet>
        </div>
      </div>
      <div className="absolute right-10 bottom-10 ">
      {notifications?.map((not,index)=>{
          return (
            <Notifications index={index} not={not} handleDelete={handleDelete}></Notifications>
        )})}
        </div>
      <div className="flex flex-col bg-blue-gray-800 h-10 basis-1/12 items-center">
        <p className="flex flex-row text-blue-gray-100 items-center">
          {" "}
          <span>
            <RiCopyleftLine />
          </span>{" "}
          Copyleft by Barański, Dziewięcki, Rudnicki and Spychalski. 2023
        </p>
      </div>
    </div>
  );
}
