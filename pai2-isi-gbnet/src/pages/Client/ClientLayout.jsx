import { Outlet, Link, useNavigate } from "react-router-dom";
import { ImHome } from "react-icons/im";
import { CgProfile } from "react-icons/cg";
import { BiMenuAltLeft } from "react-icons/bi";
import { FaFileInvoiceDollar } from "react-icons/fa";
import { RiCopyleftLine } from "react-icons/ri";
import { BsClock } from "react-icons/bs";
import { GrClose } from "react-icons/gr";
import { TbBellRinging2Filled } from "react-icons/Tb";
import Notifications from "../../components/Notifications";
import {
  MdLocalOffer,
  MdOutlineLocalOffer,
  MdReportGmailerrorred,
} from "react-icons/md";
import { useSignOut } from "react-auth-kit";
import axios from "axios";
import jwt from "jwt-decode";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import {
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
  IconButton,
  Dialog,
  Typography,
  Drawer,
  Button,
  DialogHeader,
  DialogBody,
  Badge,
} from "@material-tailwind/react";
import { useState, useEffect, useCallback } from "react";
export default function ClientLayout() {
  const [notifications, setNotifications] = useState();
  const [ringNotifications, setRingNotifications] = useState();
  const [userData, setUserData] = useState();
  const [open, setOpen] = useState(false);
  const [openNotification, setOpenNotification] = useState(false);
  const [notificationData, setNotificationData] = useState();
  const openDrawer = () => setOpen(true);
  const closeDrawer = () => setOpen(false);
  const navigate = useNavigate();
  const signOut = useSignOut();
  const token = useAuthHeader();
  const userCred = useAuthUser();
  useEffect(() => {
    const not = JSON.parse(localStorage.getItem("notifications"));
    if (not) {
      setNotifications(not);
    }

    async function getUserData() {
      const data = jwt(token());
      axios.defaults.headers.common['Authorization'] = token();
      const protectedEndpointResponse = await axios.get(
        "http://localhost:8080/upc/v1/user-role/user",
        {
          params: {
            email: data.sub,
          },
          headers:{
            "Content-Type": "application/json"
          },
          data:{}
        }
      );
      setUserData(protectedEndpointResponse.data);
      const interval = setInterval(async () => {
        if (protectedEndpointResponse.data) {
          const response = await axios.get(
            `http://localhost:8080/upc/v1/user-role/get-user-notices/${protectedEndpointResponse.data.uuid}`
          );
          if (
            JSON.stringify(ringNotifications) !=
            JSON.stringify(response.data.content)
          ) {
            setRingNotifications(response.data.content);
          } 
        }
        return () => clearInterval(interval);
      }, 10000);
    }
    getUserData();
  }, []);


  async function handleClickNot(not){
    if(!not.isClicked){
      axios.defaults.headers.common['Authorization'] = token();
      const response = await axios.put(`http://localhost:8080/upc/v1/user-role/edit-notice/${not.uuid}`,null, {params:{isClicked:true}});
      console.log(response)
    }
    setNotificationData(not)
    handleNotificationOpen()
  }
  const handleNotificationOpen = () =>{
    setOpenNotification(!openNotification)
  }

  function sortNotificationsByDate(nots) {
    nots.sort((a, b) => {
      const dateA = new Date(a.noticeDate);
      const dateB = new Date(b.noticeDate);
      return dateB - dateA;
    });
  
    return nots;
  }

  const handleDelete = useCallback(() => {
    const newNotifications = JSON.parse(localStorage.getItem("notifications"));
    const updatedNotifications = newNotifications.slice(1); 
    setNotifications(updatedNotifications);
    window.localStorage.setItem(
      "notifications",
      JSON.stringify(updatedNotifications)
    );
  }, []);
  function formatNotificationTime(date) {
    const notificationDate = new Date(date); 
    const currentDate = new Date(); 

    const timeDiffInSeconds = Math.floor(
      (currentDate - notificationDate) / 1000
    ); 

    if (timeDiffInSeconds < 60) {
      return "< 1min";
    } else if (timeDiffInSeconds < 3600) {
      const minutes = Math.floor(timeDiffInSeconds / 60);
      return `${minutes} min`;
    } else {
      const hours = Math.floor(timeDiffInSeconds / 3600);
      return `${hours} h`;
    }
  }
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
              closeDrawer();
              navigate("/home");
            }}
          >
            <ImHome className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="text-xl group-hover:text-amber-500 truncate">
              STRONA GŁÓWNA
            </p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer();
              navigate("/offers");
            }}
          >
            <MdLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="group-hover:text-amber-500">OFERTY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer();
              navigate("/products");
            }}
          >
            <MdOutlineLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="group-hover:text-amber-500">PRODUKTY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer();
              navigate("/invoices");
            }}
          >
            <FaFileInvoiceDollar className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="group-hover:text-amber-500">FAKTURY</p>
          </button>

          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer();
              navigate("/profile");
            }}
          >
            <CgProfile className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="group-hover:text-amber-500">PROFIL</p>
          </button>
          <button
            className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
            onClick={() => {
              closeDrawer();
              navigate("/reports");
            }}
          >
            <MdReportGmailerrorred className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
            <p className="group-hover:text-amber-500">ZGŁOSZENIA</p>
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
        <div className="flex flex-row space-x-4">
          <div>
            <Menu placement="left-start">
              <Badge color="deep-orange" content={ (ringNotifications?.filter((not) => !not.isClicked).length)>0?ringNotifications?.filter((not) => !not.isClicked).length:null }>
                <MenuHandler>
                  <IconButton variant="text">
                    <TbBellRinging2Filled className="h-5 w-5 text-amber-500" />
                  </IconButton>
                </MenuHandler>
              </Badge>
              <MenuList className="flex flex-col gap-2 2xl:w-1/3 w-1/2 bg-transparent backdrop-blur-2xl border-amber-800 drop-shadow-md overflow-y-auto max-h-[40vh]">
                
                {ringNotifications? sortNotificationsByDate(ringNotifications).map((not) => {return(
                <MenuItem onClick={()=>{handleClickNot(not)}} className="hover:bg-blue-gray-800  flex items-center backdrop-blur-xl gap-4 py-2 pr-8 pl-2  ">
                    <div className="flex flex-col gap-1 truncate w-full">
                      <Typography
                        variant="small"
                        color="gray"
                        className="font-normal truncate"
                      >
                        <span
                          className={`text-white text-md ${
                            !not.isClicked ? "font-bold" : ""
                          }`}
                        >
                          {not.description}
                        </span>
                      </Typography>
                      <Typography
                        variant="small"
                        className="flex items-center gap-1 w-full"
                      >
                        <BsClock  className="text-sm text-amber-500" />
                        <span className="text-sm text-amber-500">{formatNotificationTime(not.noticeDate)}</span>
                        {!not.isClicked ? <span className="text-xs text-amber-500 ml-auto">NIEODCZYTANE</span>:null}
                      </Typography>
                    </div>
                  </MenuItem>) }):<span className="text-xs text-amber-500  mx-auto">BRAK POWIADOMIEŃ</span>}
              </MenuList>
            </Menu>
          </div>
          <button
            className="text-md bg-red-600 rounded-lg px-4 py-1 text-white hover:bg-red-500"
            onClick={() => signOut()}
          >
            Wyloguj
          </button>
        </div>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="hidden 2xl:flex flex-col basis-1/5 min-h-full bg-blue-gray-700 justify-between ">
          <div className="flex flex-col p-7 space-y-8 items-center h-full fixed ">
            <button
              className="group w-full  text-white font-bold  flex flex-row items-center justify-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/home")}
            >
              <ImHome className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="text-xl group-hover:text-amber-500 truncate">
                STRONA GŁÓWNA
              </p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/offers")}
            >
              <MdLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="group-hover:text-amber-500">OFERTY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/products")}
            >
              <MdOutlineLocalOffer className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="group-hover:text-amber-500">PRODUKTY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center  space-x-4 hover:animate-pulse"
              onClick={() => navigate("/invoices")}
            >
              <FaFileInvoiceDollar className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="group-hover:text-amber-500">FAKTURY</p>
            </button>

            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/profile")}
            >
              <CgProfile className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="group-hover:text-amber-500">PROFIL</p>
            </button>
            <button
              className="group w-full text-xl text-white font-bold flex flex-row items-center space-x-4 hover:animate-pulse"
              onClick={() => navigate("/reports")}
            >
              <MdReportGmailerrorred className="w-[40px] h-[40px] m-0 p-0 text-white self-center group-hover:text-amber-500" />
              <p className="group-hover:text-amber-500">ZGŁOSZENIA</p>
            </button>
          </div>
        </div>
        <div className="2xl:basis-4/5 w-full min-h-full ">
          <Outlet></Outlet>
        </div>
      </div>
      {openNotification? (<Dialog open={openNotification} handler={handleNotificationOpen} >
          <DialogHeader className="flex flex-row p-4">
            <div className="flex-grow">Powiadomienie</div>
            <IconButton color="amber" onClick={()=> {handleNotificationOpen()}}>
              <GrClose className="w-5 h-5"/>
            </IconButton>
          </DialogHeader>
          <DialogBody>{notificationData.description}</DialogBody>
        </Dialog>):null}
      
      <div className="absolute right-10 bottom-10 ">
        {notifications?.map((not, index) => {
          return (
            <Notifications
              index={index}
              not={not}
              handleDelete={handleDelete}
            ></Notifications>
          );
        })}
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
