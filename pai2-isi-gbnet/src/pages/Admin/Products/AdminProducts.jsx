
import { useEffect, useState } from "react";
import Table from "../../../components/Table"
import axios from "axios";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import { Outlet } from "react-router-dom";
import { Spinner } from "@material-tailwind/react";
const TABLE_HEAD = [{name: "Nazwa",key: "name"},{name:"Typ urządzenia",key:"productType"}, {name:"Cena",key:"price"} ,{name:"Szczegóły",key:null} ];
 
export default function AdminProducts(){
    const [products,setProducts] = useState([])
    const [loading,setLoading] = useState(true)
    const userCred = useAuthUser()
    useEffect(()=>{    
        async function fetchData(){
            const credentials = userCred().data
        await axios.get("http://localhost:8080/upc/unsecured/v1/get-all-products",{
            auth : {
              username: credentials.email,
              password: credentials.password
            },
            headers:{
              "Content-Type": "application/json"
            },
            data:{}
          }).then(res=>{
            console.log(res.data.content)
            const tab = res.data.content.map(u=>({
                uuid: u.uuid,
                name: u.name,
                productType: u.productType,
                price: u.price              
            }))
            setProducts(tab)
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
            <Table headers={TABLE_HEAD} rows={products}></Table>
            <Outlet></Outlet>
        </div>
        
      );
}