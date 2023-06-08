import {useParams} from "react-router-dom"
import axios from "axios"
import jwt from "jwt-decode"
import {useState, useEffect} from "react"
export default function ClientDetail(){
    const [userData,setUserData] = useState()
    const {userId} = useParams();
    const token = useAuthHeader();
    useEffect(()=>{
        async function fetchUser(){
            
            axios.defaults.headers.common['Authorization'] = token();
            const protectedEndpointResponse = await axios.get('http://localhost:8080/upc/v1/user',{params: {
          email: userId
        }});
        setUserData(protectedEndpointResponse.data)
        }
        fetchUser()
    },[userId])

    return(
        <div>{userData&&({userData})}</div>
    )
}