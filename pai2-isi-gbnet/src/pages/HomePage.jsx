import Offer from "../components/Offer"
import AccountData from "../components/AccountData"
export default function HomePage(){
    return(
        <div className="flex flex-row flex-wrap justify-center w-full p-4 ">
            <AccountData></AccountData>
            <AccountData></AccountData>
            <AccountData></AccountData>
        </div>
    )
}