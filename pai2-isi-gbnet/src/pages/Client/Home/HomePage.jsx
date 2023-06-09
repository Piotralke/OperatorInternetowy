import Offer from "../Offers/OfferComponent"
import AccountData from "./AccountData"
import Balance from "./Balance"
import { useAuthUser,useAuthHeader } from "react-auth-kit"
import jwt from "jwt-decode"
export default function HomePage(){
    const token = useAuthHeader();
    const data = jwt(token());
    return(
        <div className="flex flex-col items-center w-full p-4 min-h-full basis-4/5 space-x-4 bg-blue-gray-300">
            <div className="flex flex-col xl:flex-row xl:space-x-2 w-full h-1/2 ">
            <Balance data={data}></Balance>
            <AccountData></AccountData>
            </div>
        </div>
    )
}