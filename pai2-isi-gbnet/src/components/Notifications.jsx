import React,{useState,useEffect} from 'react'
import { Alert,Typography,Progress } from "@material-tailwind/react";
import {BsInfoCircle} from "react-icons/bs"



export default function Notifications(props) {
    const [showInformaton,setInformation] = useState(true);
    let counter=0;
    useEffect(() => {
        
        if (showInformaton) {
          const interval = setInterval(() => {
            counter++;
            if(counter===100)
            {
 
                props.handleDelete();
                clearInterval(interval);
            }
          }, 50);  
        }
      }, []);
    return (
    <div>
    <Alert key={props.index}
    open={showInformaton}
    animate={{
     mount: { y: 0 },
     unmount: { y: 100 },
   }}
    color="green"
    icon={
     <BsInfoCircle
       strokeWidth={1}
       className="h-6 w-6"
     />
   }
    >
     <Typography variant="h5" color="white">
       Sukces
     </Typography>
     <Typography color="white" className="mt-2 font-normal">
       {props.not}
     </Typography>
   </Alert>
   </div>
  )
}
