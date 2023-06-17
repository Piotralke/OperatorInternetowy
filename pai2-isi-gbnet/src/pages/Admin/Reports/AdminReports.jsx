
import { useEffect, useState } from "react";
import Table from "../../../components/Table"
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
import { Outlet } from "react-router-dom";
import { Spinner } from "@material-tailwind/react";
const TABLE_HEAD = [{name: "Nr zgłoszenia",key: "userProblemId"},{name:"Data wysłania",key:"userProblemStartDate"}, {name:"Status zgłoszenia",key:"userProblemStatus"} ,{name:"Szczegóły",key:null} ];
 
export default function AdminReports(){
    const [products,setProducts] = useState([])
    const [loading,setLoading] = useState(true)
    useEffect(()=>{    
        
        axios.get("http://localhost:8080/upc/unsecured/v1/get-all-user-problems").then(res=>{
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
                <Spinner color="amber"  className="h-1/2 w-1/2"></Spinner>
            </div>
        )
    }
    return (
        <div className="flex flex-col h-full">
            <Table headers={TABLE_HEAD} rows={products}></Table>
            <Outlet></Outlet>
        </div>
        
      );
}