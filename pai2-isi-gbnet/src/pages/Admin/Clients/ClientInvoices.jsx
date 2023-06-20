import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import jwt from "jwt-decode";
import { useState, useEffect } from "react";
import { useAuthHeader } from "react-auth-kit";
import { Button, Spinner } from "@material-tailwind/react";
import { TiTick,TiTimes } from "react-icons/ti";
import Table from "../../../components/Table";
import DateFormat from "../../../components/DateFormat"
const TABLE_HEAD = [
    { name: "Usługa", key: "name" },
    { name: "Data wystawienia", key: "creationDate" },
    { name: "Kwota", key: "amount" },
    { name: "Status", key: "paymentStatus" },
    { name: "Opłać", key: null },
    { name: "Pobierz", key: null },
  ];
export default function ClientInvoices(){


    const [invoices, setInvoices] = useState();
    const [loading, setLoading] = useState(true);
    const token = useAuthHeader();
    const pattern = /clients\/([^/]+)/;
    const currentUrl = window.location.href;
    const match = currentUrl.match(pattern);

    const clientId = match[1];
    useEffect(() => {
      async function getUserData() {
        
      axios.defaults.headers.common["Authorization"] = token();
        const response = await axios.get(
          `http://localhost:8080/upc/v1/user-role/get-invoices/${clientId}`
        );
        const invoices = response.data.content.map((u) => ({
          uuid: u.uuid,
          name: u.name,
          creationDate: DateFormat(u.creationDate),
          amount: u.amount.toFixed(2),
          paymentStatus: u.paymentStatus,
        }));
  
        console.log(invoices);
        console.log(invoices.filter((inv) => inv.paymentStatus==="NIEOPLACONE").length)
        setInvoices(invoices);
        setLoading(false);
      }
      getUserData();
    }, []);
    if (loading)
    return (
      <div className="flex flex-col bg-blue-gray-600 w-full h-full items-center justify-center">
        <Spinner color="amber" className="h-1/2 w-1/2"></Spinner>
      </div>
    );
  
    return(<div className="flex flex-direction flex-col bg-gray-100 w-full h-full">
<div className="flex flex-col bg-blue-gray-800 p-4 h-full">
        <p className="text-white text-xl font-medium ml-8 mt-4 mb-2">
          Status płatności
        </p>
        {invoices.filter((inv) => inv.paymentStatus==="NIEOPLACONE").length>0 ? (
          <div className="flex flex-row mx-8 bg-blue-gray-900 p-4 items-center">
          <div className="border-4 border-red-900 rounded-full justify-center items-center">
            <TiTimes size={32} color="red" />
          </div>
          <p className="text-white ml-4">
            Klient ma nieopłacone dokumenty  
          </p>
        </div>
        ) : (
          <div className="flex flex-row mx-8 bg-blue-gray-900 p-4 items-center">
          <div className="border-4 border-green-900 rounded-full justify-center items-center">
            <TiTick size={32} color="#14532d" />
          </div>
          <p className="text-white ml-4">
            Wszystkie dokumenty zostały opłacone
          </p>
        </div>
        )}
        <p className="text-white text-xl font-medium ml-8 mt-4 mb-2">
          Operacje finansowe
        </p>
        
        <div className="flex flex-row p-4">
          {invoices && <Table headers={TABLE_HEAD} rows={invoices} invoices={true} userUuid={clientId} isAdmin={true}></Table>}
        </div>
      </div>

    </div>)
}