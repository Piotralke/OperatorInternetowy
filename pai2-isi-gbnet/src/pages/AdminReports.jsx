
import { useEffect, useState } from "react";
import Table from "../components/Table"
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
import { Outlet } from "react-router-dom";
const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ urządzenia",key:"productType"}, {name:"Cena",key:"price"} ,{name:"Szczegóły",key:null} ];
 
export default function AdminReports(){
    const [products,setProducts] = useState([])
    const [loading,setLoading] = useState(true)
    useEffect(()=>{    
        
        axios.get("http://localhost:8080/upc/unsecured/v1/get-user-problems").then(res=>{
            console.log(res.data.content)
            const tab = res.data.content.map(u=>({
                uuid: u.uuid,
                name: u.description,
                productType: u.productType,
                price: u.price              
            }))
            setProducts(tab)
            setLoading(false)
        })
    },[])
    if(loading){
        return(
            <div>Loading...</div>
        )
    }
    return (
        <div className="flex flex-col h-full">
            <Table headers={TABLE_HEAD} rows={products}></Table>
            <Outlet></Outlet>
        </div>
        
      );
}