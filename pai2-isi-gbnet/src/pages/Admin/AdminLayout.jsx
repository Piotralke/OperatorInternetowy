import React, { useState, useEffect, useCallback } from "react";
import { FiUsers, FiUserPlus, FiBox } from "react-icons/fi";
import { FaPeopleCarry, FaNetworkWired } from "react-icons/fa";
import { LuWarehouse } from "react-icons/lu";
import { Drawer } from "@material-tailwind/react";
import {
  RiUser2Fill,
  RiUserSearchLine,
  RiCopyleftLine,
  RiUser3Fill,
} from "react-icons/ri";
import { MdOutlineMiscellaneousServices } from "react-icons/md";
import { BiMenuAltLeft } from "react-icons/bi";
import {
  AiOutlineNotification,
  AiFillDatabase,
  AiOutlineDropbox,
} from "react-icons/ai";
import { TiWarningOutline } from "react-icons/ti";
import { HiCloudUpload } from "react-icons/hi";
import { useNavigate, Outlet } from "react-router-dom";
import { useSignOut ,useAuthHeader} from "react-auth-kit";
import jwt from "jwt-decode";
import Notifications from "../../components/Notifications";
function TreeNode({ node, level, handleNodeClick }) {
  const [isExpanded, setIsExpanded] = useState(false);
  
  const handleItemClick = (event) => {
    event.stopPropagation();
    handleNodeClick(node.name);
    setIsExpanded(!isExpanded);
  };

  return (
    <div style={{ paddingLeft: 15 * (level - 1) }}>
      <div className="flex flex-row items-center space-x-1 2xl:space-x-2 ">
        <div className=" text-orange-500 ">{node?.icon}</div>
        <span
          className=" cursor-pointer hover:text-orange-400 hover:animate-pulse"
          onClick={handleItemClick}
        >
          {node.name}
        </span>
      </div>
      {isExpanded &&
        node.children &&
        node.children.map((child) => (
          <TreeNode
            key={child.name}
            node={child}
            level={level + 1}
            handleNodeClick={handleNodeClick}
          />
        ))}
    </div>
  );
}

export default function AdminLayout() {
  const [notifications, setNotifications] = useState();
  const navigate = useNavigate();
  const signOut = useSignOut();
  
  const handleNodeClick = (nodeName) => {
    if (nodeName === "Wyświetl klientów") {
      closeDrawer();
      navigate("clients");
    } else if (nodeName === "Dodaj klienta") {
      closeDrawer();
      navigate("clientAdd");
    } else if (nodeName === "Wyświetl pracowników") {
      closeDrawer();
      navigate("employees");
    } else if (nodeName === "Dodaj pracownika") {
      closeDrawer();
      navigate("employeeAdd");
    } else if (nodeName === "Wyświetl produkty") {
      closeDrawer();
      navigate("products");
    } else if (nodeName === "Dodaj produkt") {
      closeDrawer();
      navigate("productAdd");
    } else if (nodeName === "Wyświetl oferty") {
      closeDrawer();
      navigate("offers");
    } else if (nodeName === "Dodaj ofertę") {
      closeDrawer();
      navigate("offerAdd");
    } else if (nodeName === "Zgłoszenia") {
      closeDrawer();
      navigate("reports");
    } else if (nodeName === "Powiadamianie") {
      closeDrawer();
      navigate("notifications");
    }
  };
  const token = useAuthHeader();
  const userData = jwt(token())
  const [isAdmin,setIsAdmin] = useState(false)
  useEffect(()=>{
    if(userData?.role.includes("ADMIN")){
      setIsAdmin(true)
    }
  },[])
  useEffect(() => {
    const not = JSON.parse(localStorage.getItem("notifications"));
    if (not) {
      setNotifications(not);
    }
  }, []);
  useEffect(() => {
    const handleStorageChange = (event) => {
      if ((event.key = "notifications")) {
        const newNotifications = JSON.parse(
          localStorage.getItem("notifications")
        );
        setNotifications(newNotifications);
      }
    };

    window.addEventListener("storage", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  const handleDelete = useCallback(() => {
    const newNotifications = JSON.parse(localStorage.getItem("notifications"));
    const updatedNotifications = newNotifications.slice(1); // Usuwanie pierwszego powiadomienia
    setNotifications(updatedNotifications);
    window.localStorage.setItem(
      "notifications",
      JSON.stringify(updatedNotifications)
    );
  }, []);

  const folder = {
    name: "Zarządzaj",
    icon: <AiFillDatabase />,
    children: [
      {
        name: "Użytkownicy",
        icon: <FiUsers />,
        children: [
          {
            name: "Pracownicy",
            icon: <RiUser2Fill />,
            children: [
              {
                name: "Wyświetl pracowników",
                icon: <RiUserSearchLine />,
              },
              {
                name: "Dodaj pracownika",
                icon: <FiUserPlus />,
              },
            ],
          },
          {
            name: "Klienci",
            icon: <RiUser3Fill />,
            children: [
              {
                name: "Wyświetl klientów",
                icon: <RiUserSearchLine />,
              },
              {
                name: "Dodaj klienta",
                icon: <FiUserPlus />,
              },
            ],
          },
        ],
      },
      {
        name: "Produkty i oferty",
        icon: <LuWarehouse />,
        children: [
          {
            name: "Produkty",
            icon: <FiBox />,
            children: [
              {
                name: "Wyświetl produkty",
                icon: <AiOutlineDropbox />,
              },
              {
                name: "Dodaj produkt",
                icon: <FaPeopleCarry />,
              },
            ],
          },
          {
            name: "Oferty",
            icon: <MdOutlineMiscellaneousServices />,
            children: [
              {
                name: "Wyświetl oferty",
                icon: <FaNetworkWired />,
              },
              {
                name: "Dodaj ofertę",
                icon: <HiCloudUpload />,
              },
            ],
          },
        ],
      },
      {
        name: "Zgłoszenia",
        icon: <TiWarningOutline />,
      },
      {
        name: "Powiadamianie",
        icon: <AiOutlineNotification />,
      },
    ],
  };

  
  const folderWorker = {
    name: "Zarządzaj",
    icon: <AiFillDatabase />,
    children: [
      {
        name: "Klienci",
        icon: <RiUser3Fill />,
        children: [
          {
            name: "Wyświetl klientów",
            icon: <RiUserSearchLine />,
          },
          {
            name: "Dodaj klienta",
            icon: <FiUserPlus />,
          },
        ],
      },
      {
        name: "Produkty i oferty",
        icon: <LuWarehouse />,
        children: [
          {
            name: "Produkty",
            icon: <FiBox />,
            children: [
              {
                name: "Wyświetl produkty",
                icon: <AiOutlineDropbox />,
              },
            ],
          },
          {
            name: "Oferty",
            icon: <MdOutlineMiscellaneousServices />,
            children: [
              {
                name: "Wyświetl oferty",
                icon: <FaNetworkWired />,
              },
            ],
          },
        ],
      },
      {
        name: "Zgłoszenia",
        icon: <TiWarningOutline />,
      },
      {
        name: "Powiadamianie",
        icon: <AiOutlineNotification />,
      },
    ],
  };
  const openDrawer = () => setOpen(true);
  const closeDrawer = () => setOpen(false);
  const [open, setOpen] = useState(false);

  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <Drawer open={open} onClose={closeDrawer} className=" bg-gray-700">
        <div className="flex flex-col  space-y-8 items-center h-full fixed ">
          <div className="flex flex-col basis-1/5 min-h-full bg-gray-700 justify-between ">
            <div className="flex text-white p-4 h-full fixed">
              <TreeNode
                node={isAdmin ? folder: folderWorker}
                level={1}
                handleNodeClick={handleNodeClick}
              />
            </div>
          </div>
        </div>
      </Drawer>
      <div className="flex flex-row items-center w-full p-3 bg-gray-800 sticky top-0">
        <BiMenuAltLeft
          onClick={openDrawer}
          size={32}
          className="2xl:hidden text-white hover:cursor-pointer"
        ></BiMenuAltLeft>
        <span className="text-xl flex-grow font-bold text-white">Gb net</span>
        <div className="bg-red-500"></div>
        <button
          className="text-xl bg-red-600 p-1 rounded-lg font-bold  text-white hover:bg-red-500"
          onClick={() => signOut()}
          data-testid="logout"
        >
          Wyloguj
        </button>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="hidden 2xl:flex flex-col basis-1/5 min-h-full bg-gray-700 justify-between px-2">
          <div className="flex text-white pt-5  h-full fixed">
            <TreeNode
              data-testid = "tree-node"
              node={isAdmin? folder:folderWorker}
              level={1}
              handleNodeClick={handleNodeClick}
            />
          </div>
        </div>
        <div className="w-full 2xl:basis-4/5 min-h-full ">
          <Outlet></Outlet>
        </div>
      </div>
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
      <div className="flex flex-col bg-gray-800 h-10 basis-1/12 items-center">
        <a className="flex flex-row text-blue-gray-100 items-center">
          {" "}
          <span>
            <RiCopyleftLine />
          </span>{" "}
          Copyleft by Barański, Dziewięcki, Rudnicki and Spychalski. 2023
        </a>
      </div>
    </div>
  );
}
