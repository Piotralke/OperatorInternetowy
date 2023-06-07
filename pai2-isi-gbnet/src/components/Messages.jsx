import { Link } from "react-router-dom";
import { AiOutlineInfoCircle } from "react-icons/ai";
import { useEffect, useState } from "react";
export default function Messages() {
  const [messages, setMessages] = useState();
  const tab = [
    { title: "OKURWA", description: "No mam problea akjsdhklausja asdkljhasa adasadsasdasd asdasd" },
    { title: "wyjebało neta", description: "No niestety nie masz neta:)" },
    { title: "chuj Ci w dupe", description: "HUEHUEHEUEHUHUEUHEHUEHUEUHHUEHUEUHEHUHUE" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "wyjebało neta", description: "Z powodu awarii w twojej dupie" },
    { title: "ąąąąąąąą", description: "No mam problem" },
  ];
  useEffect(() => {
    setTimeout(() => {
      setMessages(tab);
    }, 2000);
  }, []);
  return (
    <div className="w-1/4 h-1/3 ">
      <div className="h-full">
        <div className="flex flex-row">
        <div className="text-xl text-blue-500 flex-grow mr-4">Wiadomości</div>
        {messages && (<button className=" hover:font-bold  self-center  text-blue-500 truncate">Wszystkie wiadomości</button>)}
        </div>
        
        <div className="flex flex-col bg-blue-gray-700 h-full justify-center overflow-y-auto">
          {messages ? (
            <div className="flex flex-col divide-y">
              {messages.map((message, index) => {
                if(index<12){
                  return (
                    <button className="flex flex-row hover:bg-blue-gray-500 h-min">
                      <div className="text-lg text-white font-bold basis-2/5 truncate">{message.title}</div>
                      <div className="text-md text-white truncate basis-3/5">
                        {message.description}
                      </div>
                    </button>
                  );
                }
              })}
              
            </div>
          ) : (
            <div className="flex flex-row justify-center items-center">
              <AiOutlineInfoCircle className="w-8 h-8 text-blue-300"></AiOutlineInfoCircle>
              <div className="text-blue-300 text-md ml-2">Brak Wiadomości</div>
            </div>
          )}
          
        </div>
      </div>
    </div>
  );
}
