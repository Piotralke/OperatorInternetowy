import { useParams,useNavigate } from "react-router-dom";
import axios from "axios";
import jwt from "jwt-decode";
import { useState, useEffect } from "react";
import { Textarea } from "@material-tailwind/react";
import { BiShowAlt } from "react-icons/bi"
import { useAuthHeader,useAuthUser } from "react-auth-kit";
export default function AdminOfferDetail() {
    const [offerOriginalData, setOfferOriginalData] = useState();
    const [offerData, setOfferData] = useState();
    const [devicesToChoose, setDevicesToChoose] = useState();
    const { offerId } = useParams();
    const [isDisabled, setIsDisabled] = useState(true);
    const token = useAuthHeader();
    const navigate = useNavigate();
    const userCred = useAuthUser()
  useEffect(() => {
    async function fetchProduct() {
      const credentials = userCred().data
      // axios.defaults.headers.common['Authorization'] = token();
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-by-uuid/${offerId}`
      );
      console.log(protectedEndpointResponse.data)
      setOfferData(protectedEndpointResponse.data);
      setOfferOriginalData(protectedEndpointResponse.data);
    }
    fetchProduct();
  }, [offerId]);

  return (
    <div className="flex flex-col bg-gray-100 w-full h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2">
        <span className="text-xl text-gray-800 font-semibold">
          {offerOriginalData?.name}
        </span>
      </div>
      <div className="flex flex-col w-full space-y-8 items-center h-full">
        <div className="flex flex-col whitespace-nowrap p-8 h-full w-1/2 space-y-4">
          <div className="flex flex-row justify-between items-center">
            <a className="text-lg text-gray-700">Nazwa</a>
            <input
              value={offerData?.name}
              disabled={isDisabled}
            
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>
          <div className="flex flex-row  justify-between items-center">
            <a className="text-lg text-gray-700">Cena</a>
            <input
               value={offerData?.price}
              disabled={isDisabled}
             
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>

          <div className="flex flex-row justify-between items-center">
            <a className="text-lg text-gray-700">Typ</a>
            <input 
              value={offerData?.offerType}
              disabled={isDisabled} 
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />

          </div>
          {
            offerData?.withDevice && (<div className="flex flex-row justify-between items-center">
            <a className="text-lg text-gray-700">Produkt w zestawie</a>
            <input 
              value={offerData?.productDto.name}
              disabled={isDisabled} 
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
            <button onClick={()=>{navigate(`/admin/products/${offerData?.productDto.uuid}`)}}>
              <BiShowAlt  className="w-8 h-8 "/>
            
            </button>
          </div>)
          }
          
          <div className="flex flex-row flex-grow justify-between items-center space-x-8">
            <a className="text-lg text-gray-700">Opis</a>
            <div className="flex-grow ">
              <Textarea
                value={offerData?.description}
                disabled={isDisabled}
                rows={10}
                color="orange"
                label="Opis"
              ></Textarea>
            </div>
          </div>
         
        </div>
      </div>
    </div>
  )
}
