import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

import Internet_TVoffer from "../assets/Internet_TVoffer.png";
import Internetoffer from "../assets/Internetoffer.png";
import TVoffer from "../assets/TVoffer.png";

export default function OfferDetail() {
  const { offerId } = useParams();
  const [offer, setOffer] = useState();
  const [img, setImg] = useState();
  useEffect(() => {
    async function fetchData() {
      const response = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-by-uuid/${offerId}`
      );
      console.log(response.data);
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
    <div className="flex flex-col basis-4/5">
      <div className="flex flex-col bg-blue-gray-900 p-4">
        <a className="text-white text-2xl font-medium ml-4">Szczegóły oferty</a>
      </div>
      <div className="flex flex-col bg-blue-gray-800 space-y-4 h-full">
        {img && (
          <img src={img} className="h-[500px] w-full object-none object-center"></img>
        )}
        {offer && (
          <div className="flex flex-row mx-8 mt-8 bg-blue-gray-900 p-4 items-center justify-between">
            <div className="flex flex-col w-4/5">
              <a className="text-white ml-4 font-medium text-xl">
                {offer.name}
              </a>
              <a className="text-white ml-4 text-sm">{offer.description}</a>
            </div>
            <div className="flex flex-row item-center h-full space-x-8">
              <div className="flex flex-col space-y-1 items-center justify-between">
                <a className="text-white ">OPŁATA</a>
                <a className="text-white font-medium">
                  {offer.price?.toFixed(2)} zł
                </a>
                <a className="text-white ">MIESIĘCZNIE</a>
              </div>
              <button className="flex flex-col bg-yellow-400 p-4 h-full items-center justify-center">
                <a className="font-bold ">ZAMÓW</a>
              </button>
            </div>
          </div>
        )}
        {offer && offer.withDevice && (
          <div className="flex flex-row mx-8 mt-8 bg-blue-gray-900 p-4 items-center justify-between">
            <div className="flex flex-col w-4/5">
              <a className="text-white ml-4 font-medium text-xl">
                {offer.productEntity.name}
              </a>
              <a className="text-white ml-4 text-sm">
                {offer.productEntity.description}
              </a>
            </div>
            <div className="flex flex-col space-y-1 items-center justify-between">
              <a className="text-white ">CENA REGULARNA</a>
              <a className="text-red-600 line-through font-medium">
                {offer.productEntity.price.toFixed(2)} zł
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
