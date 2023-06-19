import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import axios from "axios";
import { Button } from "@material-tailwind/react";
import Internet_TVoffer from "../../../assets/Internet_TVoffer.png";
import Internetoffer from "../../../assets/Internetoffer.png";
import TVoffer from "../../../assets/TVoffer.png";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import jwt from "jwt-decode";

export default function OfferDetail() {
  const token = useAuthHeader();
  const userCred = useAuthUser()
  const credentials = userCred().data
  const { offerId } = useParams();
  const [offer, setOffer] = useState();
  const [img, setImg] = useState();
  const navigate = useNavigate();
  useEffect(() => {
    async function fetchData() {
      const data = jwt(token());
      const response = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-by-uuid`,
        {
          params: {
            uuid: offerId,
          },
        }
      );
      setOffer(response.data);
      switch (response.data.offerType) {
        case "INTERNET":
          setImg(Internetoffer);
          break;
        case "TV":
          setImg(TVoffer);
          break;
        case "INTERNET_PLUS_TV":
          setImg(Internet_TVoffer);
          break;
      }
    }
    fetchData().catch(console.error);
  }, []);

  return (
    <div className="flex flex-col basis-4/5 h-full">
      <div className="flex flex-col bg-blue-gray-900 p-4">
        <p className="text-white text-2xl font-medium ml-4">Szczegóły oferty</p>
      </div>
      <div className="flex flex-col bg-blue-gray-800 space-y-4 h-full">
        {img && (
          <img src={img} className="h-[500px] w-full object-none object-center"></img>
        )}
        {offer && (
          <div className="flex lg:flex-row mx-8 mt-8 bg-blue-gray-900 p-4 items-center justify-between flex-col">
            <div className="flex flex-col w-4/5">
              <p className="text-white ml-4 font-medium text-xl">
                {offer.name}
              </p>
              <p className=" hidden sm:block bg-transparent text-white ml-4 text-sm ">{offer.description}</p>
            </div>
            <div className="flex flex-row item-center justify-center space-x-8">
              <div className="flex flex-col  items-center justify-between">
                <p className="text-white ">OPŁATA</p>
                <p className="text-white font-medium">
                  {offer.price?.toFixed(2)} zł
                </p>
                <p className="text-white ">MIESIĘCZNIE</p>
              </div>
              <Button onClick={()=>{ localStorage.removeItem("selectedProducts");
              localStorage.removeItem("offer");
              localStorage.removeItem("contract");
                navigate(`/order/${offerId}`) }} color="amber" className="flex flex-col  p-4 h-1/2 items-center justify-center self-center">
                <p className="font-bold ">ZAMÓW</p>
              </Button>
            </div>
          </div>
        )}
        {offer && offer.withDevice && (
          <div className="flex flex-row mx-8 mt-8 bg-blue-gray-900 p-4 items-center justify-between">
            <div className="flex flex-col w-4/5">
              <a className="text-white ml-4 font-medium text-xl">
                {offer.productDto.name}
              </a>
              <a className="text-white ml-4 text-sm">
                {offer.productDto.description}
              </a>
            </div>
            <div className="flex flex-col space-y-1 items-center justify-between">
              <a className="text-white ">CENA REGULARNA</a>
              <a className="text-red-600 line-through font-medium">
                {offer.productDto.price.toFixed(2)} zł
              </a>
              <a className="text-white ">OPŁATA w zestawie</a>
              <a className="text-white font-medium">0.00 zł</a>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
