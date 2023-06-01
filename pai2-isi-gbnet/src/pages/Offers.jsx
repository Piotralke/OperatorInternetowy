import Offer from "../components/Offer"

export default function Offers(){
    return(
        <div className="flex flex-row flex-wrap justify-center w-full p-4 ">
            <Offer  title="DJWIELKI CHUJ" period="Dlugo w pizde"></Offer>
            <Offer  title="DJWIELKI CHUJ" installation="50,00"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
            <Offer  title="DJWIELKI CHUJ" activation="0,00" price="49,99"></Offer>
        </div>
    )
}