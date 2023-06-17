import Offer from "../Offers/OfferComponent"
import AccountData from "./AccountData"
import Balance from "./Balance"
import Messages from "./Messages"
import { useAuthUser } from "react-auth-kit"
export default function HomePage(){

    return(
        <div className="flex flex-col justify-center items-center w-full p-4 min-h-full basis-4/5 space-x-4 bg-blue-gray-300">
            <div className="flex flex-col xl:flex-row xl:space-x-2 w-full justify-center items-center">
            <Messages></Messages>
            <Balance></Balance>
            <AccountData></AccountData>
            </div>
            <div className="w-3/4 mt-10 bg-blue-grey-500">CIPECZKI LUBIE</div>
        </div>
    )
}