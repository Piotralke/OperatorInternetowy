import { useState } from "react"
import { TiTick } from 'react-icons/ti';
import axios from "axios";
import {useAuthHeader} from 'react-auth-kit'
import { useAuthUser } from "react-auth-kit"
import {
    Button,
    Typography,
    Accordion,
    Card,
    CardBody,
    AccordionHeader,
    AccordionBody,
  } from "@material-tailwind/react";
import jwt from "jwt-decode"
export default function Invoices(){
    const [invoicesPaid, setInvoicesPaid] = useState(true)
    const a = useAuthHeader();
    const data = {
        balance: "0.00",
        invoices: [
            {

            }
        ]
    }
    async function functiona(){
        console.log(a());
        const data = jwt(a());
        console.log(data);
        axios.defaults.headers.common['Authorization'] = a();
        const protectedEndpointResponse = await axios.get('http://localhost:8080/upc/v1/user',{params: {
      email: data.sub
    }});
    console.log(protectedEndpointResponse.data); // Odpowiedź z chronionego endpointu
    }
    return(
    <div className="flex flex-col basis-4/5">
        <div className="flex flex-row justify-between bg-blue-gray-900 items-center">
            <a className="text-white text-xl font-medium ml-4">Płatności</a>
            <div className="flex flex-row bg-amber-500 p-4 items-end">
                <a className="text-black text-2xl font-medium mr-2">Saldo:</a>
                <a className="text-black font-bold text-3xl">{data.balance} zł</a>
            </div>
        </div>
        <div className="flex flex-col bg-blue-gray-800 p-4">
            <a className="text-white text-xl font-medium ml-8 mt-4 mb-3">Pozycje nieopłacone</a>
            {invoicesPaid ? (
                <div className="flex flex-row mx-8 bg-blue-gray-900 p-4 items-center">
                    <div className="border-4 border-green-900 rounded-full justify-center items-center">
                        <TiTick size={32} color="#14532d"/>
                    </div>
                    <a className="text-white ml-4">Wszystkie dokumenty zostały opłacone</a>
                </div>
            ) : null}  {/*Tu lista niezapłaconych faktur*/}
            <a className="text-white text-xl font-medium ml-8 mt-4 mb-3">Operacje finansowe</a>
            <div className="flex flex-col mx-8 bg-blue-gray-900 p-4">
                <a className="text-white text-lg font-bold">W okresie</a>
                <div className="flex flex-row mt-4 space-x-4 items-center">
                    <a className="text-white font-bold">Od</a>
                    <input className="cursor-pointer  bg-blue-gray-700 text-white hover:bg-blue-gray-800 p-1" type="date" id="from" name="from"></input>
                    <a className="text-white font-bold">Do</a>
                    <input className="cursor-pointer  bg-blue-gray-700 text-white hover:bg-blue-gray-800 p-1" type="date" id="to" name="to"></input>
                </div>
                <div className="flex flex-row mt-8 space-x-4 items-center">
                    
                    <Button color='deep-orange'>Wyczyść</Button>
                    <Button color='amber'>Szukaj</Button>
                </div>
            </div>
            <div className="flex flex-row"> {/*Lista faktur*/}
                
            </div>
        </div>
    </div>)
}