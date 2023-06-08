import {useParams} from "react-router-dom"
import axios from "axios"
import jwt from "jwt-decode"
import {useState, useEffect} from "react"
import { useAuthHeader } from "react-auth-kit"

export default function AdminProductDetail(){
    const [productData,setProductData] = useState()
    const {productId} = useParams();
    const [isDisabled, setIsDisabled] = useState(true);
    const token = useAuthHeader();
    useEffect(()=>{
        async function fetchProduct(){
            
            // axios.defaults.headers.common['Authorization'] = token();
            const protectedEndpointResponse = await axios.get(`http://localhost:8080/upc/unsecured/v1/get-product/${productId}`);
            setProductData(protectedEndpointResponse.data)
        }
        fetchProduct()
    },[productId])

    return(
        <div>{JSON.stringify(productData)}</div>
    )
}