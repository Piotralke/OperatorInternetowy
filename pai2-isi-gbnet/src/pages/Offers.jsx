import Offer from "../components/Offer"
import axios from "axios"
import { useEffect } from "react"
export default function Offers(){
    useEffect(()=>{
        async function fetchData (){
            await axios.get("http://localhost:8080/upc/unsecured/v1/get-all-offers")
        .then((data) => {
            console.log(data);
        })
        .catch((err) => console.log(err));
        }
        fetchData().catch(console.error)
        
    },[])
    return(
        <div className="flex flex-row flex-wrap justify-center w-full h-full basis-4/5">
            <Offer  title="DJWIELKI CHUJ" period="Dlugo w pizde"></Offer>
            <Offer  title="DJWIELKI CHUJ" installation="50,00"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
        </div>
    )
}