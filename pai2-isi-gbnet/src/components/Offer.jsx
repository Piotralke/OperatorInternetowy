import { useEffect } from "react"
import offerImg from "../assets/offerBackground.jpg"



export default function Offer(props){
   
    return(
        <div className="flex flex-col w-1/4 pb-4 m-4 space-y-3 border rounded-md h-1/2">
            <img src={offerImg} className="w-full rounded-t-md"></img>
            <div className="px-3 space-y-2">
                <div className="text-xl font-bold">{props.title}</div>
                {/* {props.installation? (<div className="text-lg text-blue-500">Instalacja za: {props.installation} zł</div>):null}
                {props.activation? (<div className="text-lg text-blue-500">Aktywacja za: {props.activation} zł</div>):null} */}
                <div className="text-lg text-blue-500">Okres zobowiązania: {props.period?props.period:"Brak"}</div>
                <div className="flex flex-row border border-gray-200">
                    <div className="flex flex-col w-2/3">
                        <text className="text-2xl font-bold text-center">{props.price?props.price:"0,00"} zł</text>
                        <text className="text-xl text-center">MIESIĘCZNIE</text>
                    </div>
                    <button className="w-1/3 font-bold bg-yellow-400">
                        Szczegóły
                    </button>
                </div>
            </div>
            
        </div>
    )
}