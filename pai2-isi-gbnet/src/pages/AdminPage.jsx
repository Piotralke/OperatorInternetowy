import { useState } from "react";
import {FiUsers,FiUserPlus, FiBox} from "react-icons/fi"
import { FaPeopleCarry,FaNetworkWired, } from "react-icons/fa"
import { LuWarehouse } from "react-icons/lu"
import {RiUser2Fill, RiUserSearchLine, RiUser3Fill,} from "react-icons/ri"
import { MdOutlineMiscellaneousServices} from "react-icons/md"
import { AiOutlineNotification, AiFillDatabase, AiOutlineDropbox } from "react-icons/ai"
import {TiWarningOutline} from "react-icons/ti"
import {HiCloudUpload} from "react-icons/hi"
import { useNavigate, Outlet } from "react-router-dom";
import { useSignOut } from "react-auth-kit";
function TreeNode({ node, level, handleNodeClick }) {
  const [isExpanded, setIsExpanded] = useState(false);
  
  const handleItemClick = (event) => {
    event.stopPropagation();
    handleNodeClick(node.name);
    setIsExpanded(!isExpanded);
  };

  

  return (
    <div style={{ paddingLeft: 15 * (level - 1) }}>
      <div className="flex flex-row items-center space-x-4 ">
        <div className="text-[1.25vw] text-orange-500 ">
          {node?.icon}
        </div>
        <span className="  text-[1vw] cursor-pointer hover:font-bold hover:text-orange-400 hover:animate-pulse" onClick={handleItemClick}>{node.name}</span>
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

export default function AdminPage() {
  const navigate = useNavigate()
  const signOut = useSignOut()
  const handleNodeClick = (nodeName) => {
    if (nodeName === "Wyświetl klientów") {
      console.log("Kliknięto Użytkownicy");
     navigate('clients');
    } else if (nodeName === "Wyświetl pracowników") {
      navigate('employees');
    }
    else if (nodeName === "Wyświetl produkty") {
      navigate('products');
    }
    else if (nodeName === "Wyświetl oferty") {
      navigate('offers');
    }
  };

  const folder = {
    name: "Zarządzaj",
    icon: <AiFillDatabase/>,
    children: [
      {
        name: "Użytkownicy",
        icon: <FiUsers/>,
        children: [
          {
            name: "Pracownicy",
            icon: <RiUser2Fill />,
            children: [
              {
                name: "Wyświetl pracowników",
                icon: <RiUserSearchLine/>
              },
              {
                name: "Dodaj pracownika",
                icon: <FiUserPlus/>
              },
            ],
          },
          {
            name: "Klienci",
            icon: <RiUser3Fill/>,
            children: [
              {
                name: "Wyświetl klientów",
                icon: <RiUserSearchLine/>
              },
              {
                name: "Dodaj klienta",
                icon: <FiUserPlus/>
              },
            ],
          },
        ],
      },
      {
        name: "Produkty i oferty",
        icon: <LuWarehouse/>,
        children: [
          {
            name: "Produkty",
            icon: <FiBox/>,
            children: [
              {
                name: "Wyświetl produkty",
                icon: <AiOutlineDropbox/>,
              },
              {
                name: "Dodaj produkt",
                icon: <FaPeopleCarry/>,
              },
            ],
          },
          {
            name: "Oferty",
            icon: <MdOutlineMiscellaneousServices/>,
            children: [
              {
                name: "Wyświetl oferty",
                icon: <FaNetworkWired/>
              },
              {
                name: "Dodaj ofertę",
                icon: <HiCloudUpload/>
              },
            ],
          },
        ],
      },
      {
        name: "Zgłoszenia",
        icon: <TiWarningOutline/>,
      },
      {
        name: "Powiadamianie",
        icon: <AiOutlineNotification/>,
      },
    ],
  };

  return (
    <div className="flex flex-col min-h-screen min-w-full justify-stretch">
      <div className="flex flex-row items-center w-full p-3 bg-gray-800 sticky top-0">
        <span className="text-xl flex-grow font-bold text-white">Gb net</span>
        <button
          className="text-xl bg-red-600 p-1 rounded-lg font-bold  text-white hover:bg-red-500"
          onClick={() => signOut()}
        >
          Wyloguj
        </button>
      </div>
      <div className="flex-grow flex flex-row w-full m-0 p-0">
        <div className="flex flex-col basis-1/5 min-h-full bg-gray-700 justify-between p-8">
          <div className="flex text-white pt-5  h-full fixed">
              <TreeNode
                
                node={folder}
                level={1}
                handleNodeClick={handleNodeClick}
              />
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

