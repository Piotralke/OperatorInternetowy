import { useState } from "react"
import { TiTick } from 'react-icons/ti';
export default function Invoices(){
    const [invoicesPaid, setInvoicesPaid] = useState(true)

    const data = {
        balance: "0.00",
        invoices: [
            {

            }
        ]
    }

    return(
    <div className="flex flex-col basis-4/5">
        <div className="flex flex-row justify-between bg-gray-900 items-center">
            <a className="text-white text-xl font-medium ml-4">Płatności</a>
            <div className="flex flex-row bg-blue-500 p-4 items-end">
                <a className="text-white text-2xl font-medium mr-2">Saldo:</a>
                <a className="text-white font-bold text-3xl">{data.balance} zł</a>
            </div>
        </div>
        <div className="flex flex-col bg-gray-800">
            <a className="text-white text-xl font-medium ml-8 mt-4 mb-3">Pozycje nieopłacone</a>
            {invoicesPaid ? (
                <div className="flex flex-row mx-8 bg-gray-900 p-4 items-center">
                    <div className="border-4 border-green-900 rounded-full justify-center items-center">
                        <TiTick size={32} color="#14532d"/>
                    </div>
                    <a className="text-white ml-4">Wszystkie dokumenty zostały opłacone</a>
                </div>
            ) : null}  {/*Tu lista niezapłaconych faktur*/}
            <a className="text-white text-xl font-medium ml-8 mt-4 mb-3">Operacje finansowe</a>
            <div className="flex flex-col mx-8 bg-gray-900 p-4">
                <a className="text-white">W okresie</a>
                <div className="flex flex-row mt-4 space-x-4">
                    <a className="text-white">Od</a>
                    <input type="date" id="from" name="from"></input>
                    <a className="text-white">Do</a>
                    <input type="date" id="to" name="to"></input>
                </div>
                <div className="flex flex-row mt-8 space-x-4">
                    <input className="border-2 rounded-md border-white font-bold text-white p-4" type="button" value="WYCZYŚĆ"></input>
                    <input className="bg-blue-500 rounded-md text-white p-4 font-bold" type="button" value="SZUKAJ"></input>
                </div>
            </div>
            <div className="flex flex-row"> {/*Lista faktur*/}
                
            </div>
        </div>
    </div>)
}