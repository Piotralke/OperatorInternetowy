
import { useEffect, useState } from "react";
import Table from "../components/Table"
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
const TABLE_HEAD = ["Imię", "Nazwisko", "Email","Nr telefonu", "Adres","NIP","Szczegóły","Edycja" ];
 
export default function Clients(){
    const token = useAuthHeader();
    const [users,setUsers] = useState([])
    const [loading,setLoading] = useState(true)
    useEffect(()=>{    

        axios.defaults.headers.common['Authorization'] = token();
        
        axios.get("http://localhost:8080/upc/v1/user/all").then(res=>{
            const users = res.data.content.map(u=>({
                uuid: u.uuid,
                FirstName: u.firstName,
                LastName: u.lastName,
                Email: u.email,
                phone: u.phoneNumber,
                address: u.address,
                nip: u.nip
            }))
            console.log(users)
            setUsers(users)
            setLoading(false)
        })
    },[])
    if(loading){
        return(
            <div>Loading...</div>
        )
    }
    return (
        <Table headers={TABLE_HEAD} rows={users}></Table>
      );
}