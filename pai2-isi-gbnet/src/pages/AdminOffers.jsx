
import { useEffect, useState } from "react";
import Table from "../components/Table"
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
import { Outlet } from "react-router-dom";
const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ urządzenia",key:"productType"}, {name:"Cena",key:"price"},{name:"Z urządzeniem?",key:"withDevice"} ,{name:"Szczegóły",key:null} ];
 
export default function AdminOffers(){
    const [offers,setOffers] = useState([])
    const [loading,setLoading] = useState(true)
    useEffect(()=>{    
        
        axios.get("http://localhost:8080/upc/unsecured/v1/get-all-offers").then(res=>{
            console.log(res.data.content)
            const tab = res.data.content.map(u=>({
                uuid: u.uuid,
                name: u.name,
                productType: u.offerType,
                price: u.price,
                withDevice: u.withDevice            
            }))
            setOffers(tab)
            setLoading(false)
        })
    },[])
    if(loading){
        return(
            <div>Loading...</div>
        )
    }
    return (
        <div className="">
            <Table headers={TABLE_HEAD} rows={offers}></Table>
            <Outlet></Outlet>
        </div>
        
      );
}