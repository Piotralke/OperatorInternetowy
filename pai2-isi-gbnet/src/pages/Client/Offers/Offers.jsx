import Offer from "./OfferComponent";
import axios from "axios";
import { useEffect, useState, useRef } from "react";

export default function Offers() {
  const [offers, setOffers] = useState([]);
  const telewizjaRef = useRef(null);
  const internetRef = useRef(null);
  const internetTelewizjaRef = useRef(null);

  useEffect(() => {
    async function fetchData() {
      await axios
        .get("http://localhost:8080/upc/unsecured/v1/get-all-offers")
        .then((data) => {
          setOffers(data.data.content);
        })
        .catch((err) => console.log(err));
    }
    fetchData().catch(console.error);
  }, []);

  const scrollToRef = (ref) => {
    window.scrollTo({
      top: ref.current.offsetTop,
      behavior: "smooth",
    });
  };

  return (
    <div className="flex flex-col w-full min-h-full bg-blue-gray-300 basis-4/5">
      <div className="w-full" ref={telewizjaRef} >
        <div className="flex flex-row fixed space-x-5 bg-amber-500 w-full bg-opacity-50 text-black">
            <button className="rounded-xl text-2xl hover:font-bold italic "onClick={() => scrollToRef(telewizjaRef)}>Telewizja</button>
            <button className="rounded-xl text-2xl hover:font-bold italic"onClick={() => scrollToRef(internetRef)}>Internet</button>
            <button className="rounded-xl text-2xl hover:font-bold italic"onClick={() => scrollToRef(internetTelewizjaRef)}>Internet + Telewizja</button>
        </div>
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r mt-8 from-amber-500 text-black p-3 font-bold"
          
        >
          Telewizja
        </div>
        <div className="xl:grid xl:grid-cols-3 xl:gap-4" >
          {offers
            ?.filter((offer) => offer.offerType === "TV")
            .map((offer) => (
              <Offer
                id={offer.uuid}
                title={offer.name}
                price={offer.price}
                key={offer.uuid}
                type={offer.offerType}
                product={offer.productDto}
                withDevice={offer.withDevice}
              />
            ))}
        </div>
      </div>
      <div className="w-full"ref={internetRef} >
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r from-amber-500 text-black p-3 font-bold"
          
        >
          Internet
        </div>
        <div className="xl:grid xl:grid-cols-3 xl:gap-4" >
          {offers
            ?.filter((offer) => offer.offerType === "INTERNET")
            .map((offer) => (
              <Offer
                id={offer.uuid}
                title={offer.name}
                price={offer.price}
                key={offer.uuid}
                type={offer.offerType}
                product={offer.productDto}
                withDevice={offer.withDevice}
              />
            ))}
        </div>
      </div>
      <div className="w-full" ref={internetTelewizjaRef} >
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r from-amber-500 text-black p-3 font-bold"
          
        >
          Internet + telewizja
        </div>
        <div className="xl:grid xl:grid-cols-3 xl:gap-4">
          {offers
            ?.filter((offer) => offer.offerType === "INTERNET_PLUS_TV")
            .map((offer) => (
              <Offer
                id={offer.uuid}
                title={offer.name}
                price={offer.price}
                key={offer.uuid}
                type={offer.offerType}
                product={offer.productDto}
                withDevice={offer.withDevice}
              />
            ))}
        </div>
      </div>
    </div>
  );
}
