
import { useEffect, useState } from "react";
import Table from "../../../components/Table"
import axios from "axios";
import { useAuthHeader, useAuthUser } from "react-auth-kit";
import { Outlet } from "react-router-dom";
import { Spinner } from "@material-tailwind/react";
const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ oferty",key:"productType"}, {name:"Cena",key:"price"},{name:"Urządzenie w zestawie",key:"productDto.name"} ,{name:"Szczegóły",key:null} ];
 
export default function AdminOffers(){
    const [offers,setOffers] = useState([])
    const [loading,setLoading] = useState(true)
    useEffect(()=>{    
        async function fetchData(){
            
            await axios.get("http://localhost:8080/upc/unsecured/v1/get-all-offers").then(res=>{
                console.log(res.data.content)
                const tab = res.data.content.map(u=>  ({
                    uuid: u.uuid,
                    name: u.name,
                    productType: u.offerType,
                    price: u.price,
                    "productDto.name": u.productDto.name? u.productDto.name:"Brak"         
                }))
                setOffers(tab)
                setLoading(false)
            })
        }
        fetchData()
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
            <Table headers={TABLE_HEAD} rows={offers}></Table>
            <Outlet></Outlet>
        </div>
      );
}