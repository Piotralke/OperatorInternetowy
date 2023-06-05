import Offer from "../components/Offer"
import AccountData from "../components/AccountData"
import Balance from "../components/Balance"
import Messages from "../components/Messages"
import { useAuthUser } from "react-auth-kit"
export default function HomePage(){

    return(
        <div className="flex flex-row flex-wrap justify-center content-center w-full p-4 min-h-full basis-4/5 space-x-4 bg-gray-300">
            <Messages></Messages>
            <Balance></Balance>
            <AccountData></AccountData>
            <div className="w-3/4 mt-10 bg-gray-500">CIPECZKI LUBIE</div>
        </div>
    )
}