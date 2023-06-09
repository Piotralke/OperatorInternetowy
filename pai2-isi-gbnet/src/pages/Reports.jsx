import { useEffect, useState } from "react";
import Table from "../components/Table"
import axios from "axios";
import { Outlet, useNavigate } from "react-router-dom";
import jwt from "jwt-decode";
import { useAuthHeader } from "react-auth-kit";
import { Button,Spinner } from "@material-tailwind/react";

const TABLE_HEAD = [{name: "Nr zgłoszenia",key: "userProblemId"},{name:"Data wysłania",key:"userProblemStartDate"}, {name:"Status zgłoszenia",key:"userProblemStatus"} ,{name:"Szczegóły",key:null} ];
// const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ urządzenia",key:"productType"}, {name:"Cena",key:"price"} ,{name:"Szczegóły",key:null} ];
export default function Reports(){
    const navigate = useNavigate()
    const [products,setProducts] = useState([])
    const [loading,setLoading] = useState(true)
    const token = useAuthHeader()

    
    useEffect(()=>{    
        const data = jwt(token());
            axios.defaults.headers.common['Authorization'] = token();
        axios.get("http://localhost:8080/upc/unsecured/v1/get-user-problems",{params:{
            email: data.sub
        }}).then(res=>{
            console.log(res.data.content)
            const tab = res.data.content.map(u=>({
                uuid: u.uuid,
                userProblemId: u.uuid,
                userProblemStartDate: u.userProblemStartDate,
                userProblemStatus: u.userProblemStatus              
            }))
            setProducts(tab)
             setLoading(false)
        })
    },[])
    if(loading){
        return(
            <div className="flex flex-col w-full h-full items-center justify-center">
                <Spinner className="h-1/2 w-1/2"></Spinner>
            </div>
            
        )
    }
    
    return (
        <div className="flex flex-col h-full">
            <div className="">
            <Button
                  className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500 m-2"
                  onClick={() => {navigate("add")}}
                >
                  DODAJ ZGŁOSZENIE
                </Button>
            </div>

            <Table headers={TABLE_HEAD} rows={products}></Table>
            <Outlet></Outlet>
        </div>
    )
}