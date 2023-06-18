import { useState, useEffect } from "react";
import { TiTick,TiTimes } from "react-icons/ti";
import { BiFileFind } from "react-icons/bi";
import axios from "axios";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import Table from "../../../components/Table";
import { Button, Spinner } from "@material-tailwind/react";
import jwt from "jwt-decode";
import DateFormat from "../../../components/DateFormat"

const TABLE_HEAD = [
  { name: "Usługa", key: "name" },
  { name: "Data wystawienia", key: "creationDate" },
  { name: "Kwota", key: "amount" },
  { name: "Status", key: "paymentStatus" },
  { name: "Opłać", key: null },
  { name: "Pobierz", key: null },
];

export default function Invoices() {
  const [invoicesPaid, setInvoicesPaid] = useState(true);
  const [invoices, setInvoices] = useState();
  const [userData, setUserData] = useState([]);
  const [fromDate, setFromDate] = useState(
    new Date(Date.now() - 7 * 24 * 60 * 60 * 1000)
      .toISOString()
      .substring(0, 10)
  );
  const [toDate, setToDate] = useState(
    new Date().toISOString().substring(0, 10)
  );
  const [loading, setLoading] = useState(true);
  const token = useAuthHeader();
  const a = useAuthHeader();
  const userCred = useAuthUser();
  const data = {
    balance: "0.00",
    invoices: [{}],
  };
  useEffect(() => {
    async function getUserData() {
      const data = jwt(token());
      const credentials = userCred().data
      axios.defaults.headers.common["Authorization"] = token();
      const protectedEndpointResponse = await axios.get(
        "http://localhost:8080/upc/v1/user-role/user",
        {
          params: {
            email: data.sub,
          },
          auth : {
            username: credentials.email,
            password: credentials.password
          },
          headers:{
            "Content-Type": "application/json"
          },
          data:{}
        }
      );
      console.log(protectedEndpointResponse.data);
      setUserData(protectedEndpointResponse.data);
      const response = await axios.get(
        `http://localhost:8080/upc/v1/user-role/get-invoices/${protectedEndpointResponse.data.uuid}`,
        {
          params: {
            email: data.sub,
          },
          auth : {
            username: credentials.email,
            password: credentials.password
          },
          headers:{
            "Content-Type": "application/json"
          },
          data:{}
        }
      );
      console.log(response);
      const invoices = response.data.content.map((u) => ({
        uuid: u.uuid,
        name: u.name,
        creationDate: DateFormat(u.creationDate),
        amount: u.amount.toFixed(2),
        paymentStatus: u.paymentStatus,
      }));
      console.log(invoices);
      setInvoices(invoices);
      setLoading(false);
    }
    getUserData();
  }, []);
  if (loading) {
    return (
      <div className="flex flex-col w-full h-full items-center justify-center">
        <Spinner color="amber" className="h-1/2 w-1/2"></Spinner>
      </div>
    );
  }
  return (
    <div className="flex flex-col basis-4/5 h-full">
      <div className="flex flex-row justify-between bg-blue-gray-900 items-center">
        <p className="text-white text-xl font-medium ml-4">Płatności</p>
        <div className="flex flex-row bg-amber-500 p-4 items-end">
          <p className="text-black text-2xl font-medium mr-2">Saldo:</p>
          <p className="text-black font-bold text-3xl">{userData?.balance.toFixed(2)} zł</p>
        </div>
      </div>
      <div className="flex flex-col bg-blue-gray-800 p-4 h-full">
        <p className="text-white text-xl font-medium ml-8 mt-4 mb-2">
          Status płatności
        </p>
        {invoices.filter((inv) => inv.paymentStatus==="NIEOPŁACONE").length>0 ? (
          <div className="flex flex-row mx-8 bg-blue-gray-900 p-4 items-center">
            <div className="border-4 border-green-900 rounded-full justify-center items-center">
              <TiTick size={32} color="#14532d" />
            </div>
            <p className="text-white ml-4">
              Wszystkie dokumenty zostały opłacone
            </p>
          </div>
        ) : (
          <div className="flex flex-row mx-8 bg-blue-gray-900 p-4 items-center">
            <div className="border-4 border-red-900 rounded-full justify-center items-center">
              <TiTimes size={32} color="red" />
            </div>
            <p className="text-white ml-4">
              Masz nieopłacone dokumenty
            </p>
          </div>
        )}
        <p className="text-white text-xl font-medium ml-8 mt-4 mb-2">
          Operacje finansowe
        </p>
        
        <div className="flex flex-row p-4">
          {" "}
          {/*Lista faktur*/}
          {invoices && <Table headers={TABLE_HEAD} rows={invoices} invoices={true} userUuid={userData.uuid}></Table>}
        </div>
      </div>
    </div>
  );
}
