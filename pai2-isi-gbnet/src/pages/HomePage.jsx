import Offer from "../components/Offer"
import AccountData from "../components/AccountData"
import Balance from "../components/Balance"
export default function HomePage(){
    return(
        <div className="flex flex-row flex-wrap justify-center w-full p-4 h-full basis-4/5">
            <AccountData></AccountData>
            <Balance></Balance>
            <AccountData></AccountData>
        </div>
    )
}