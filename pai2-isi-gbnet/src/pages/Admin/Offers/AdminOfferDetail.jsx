import { useParams,useNavigate } from "react-router-dom";
import React from 'react';
import axios from "axios";
import jwt from "jwt-decode";
import { useState, useEffect,useRef } from "react";
import { Textarea } from "@material-tailwind/react";
import { BiShowAlt } from "react-icons/bi"
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import { BsWindowSidebar } from "react-icons/bs";
export default function AdminOfferDetail(){
    const [isAdmin,setIsAdmin] = React.useState(false)
    const [isDisabled, setIsDisabled] = useState(true);
    const [offerOriginalData, setOfferOriginalData] = useState();
    const [offerData, setOfferData] = useState();
    const [name, setName] = useState()
    const [price, setPrice] = useState()
    const [devicesToChoose, setDevicesToChoose] = useState();
    const { offerId } = useParams();
    const token = useAuthHeader();
    const navigate = useNavigate();
    const nameRef = useRef();
    const priceRef = useRef();
    const deviceRef = useRef();
    const typeRef = useRef();
    const [description,setDescription] = useState();
    

    useEffect(()=>{
      const userData = jwt(token())
      if(userData?.role.includes("ADMIN")){
        setIsAdmin(true)
      }
    },[])
  useEffect(() => {
    
    async function fetchProduct() {
      // axios.defaults.headers.common['Authorization'] = token();
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-by-uuid`,
        {
          params: {
            uuid: offerId,
          },
        }
      );
      setName(protectedEndpointResponse.data.name)
      setPrice(protectedEndpointResponse.data.price)
      setDescription(protectedEndpointResponse.data.description)
      setOfferData(protectedEndpointResponse.data);
      setOfferOriginalData(protectedEndpointResponse.data);
    }
    fetchProduct();
  }, [offerId]);
  async function handleEdit(){
    axios.defaults.headers.common['Authorization'] = token();
    const protectedEndpointResponse = await axios.put(
      `http://localhost:8080/upc/v1/worker-role/edit-offer-status/${offerId}`,{},
      {
        params: {
          isArchival: true,
        }
      }
    );
    let data;
    if(offerData?.withDevice)
    {

        data = {
          name: name,
          description: description,
          price:parseFloat(price) ,
          SaveProductWithOfferRequestDto: {
            uuid: offerData.productDto.uuid,
          },
          withDevice: true,
          offerType: typeRef.current.value,
      }
    }
    else
    {
      data = {
        name: name,
          description: description,
          price:parseFloat(price) ,
        SaveProductWithOfferRequestDto: null,
        withDevice: false,
        offerType: typeRef.current.value,
       
      }
    }
    axios.defaults.headers.common['Authorization'] = token();
     const apiUrl = "http://localhost:8080/upc/v1/worker-role/save-offer";
     const response = await axios.post(apiUrl, data);
     if(response.status === 200)
     {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        const message = {
          message:`Pomyślnie edytowano ofertę nową ofertę ${data.name}`,
          type: "SUCCESS"
        }
        if(tab)
        {
          newTab = [...tab,message];
        }else{
          newTab = [message];
        }
        
        window.localStorage.setItem("notifications",JSON.stringify(newTab));
        window.dispatchEvent(new Event("storage"))
        navigate(`/admin/offers/${response.data.uuid}`)
        window.location.reload();
     }
  }
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
            <label className="text-lg text-gray-700 " for="name">Nazwa</label>
            <input
              id="name"
              name='name'
              onChange={(e)=>{
                setName(e.target.value)
              }}
              value={name}
              disabled={isDisabled}
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>
          <div className="flex flex-row  justify-between items-center">
            <label className="text-lg text-gray-700" for="price">Cena</label>
            <input
            id="price"
            name='price'
              onChange={(e)=>{
                setPrice(e.target.value)
              }}
              value={price}
              disabled={isDisabled}
             
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>

          <div className="flex flex-row justify-between items-center">
            <label className="text-lg text-gray-700">Typ</label>
            <input 
              ref={typeRef}
              value={offerData?.offerType}
              disabled
              readOnly
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />

          </div>
          {
            offerData?.withDevice && (<div className="flex flex-row justify-between items-center">
            <label className="text-lg text-gray-700">Produkt w zestawie</label>
            <input 
              ref={deviceRef}
              value={offerData?.productDto.name}
              disabled
              readOnly
              className="px-4 py-1 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
            <button onClick={()=>{navigate(`/admin/products/${offerData?.productDto.uuid}`)}}>
              <BiShowAlt  className="w-8 h-8 "/>
            
            </button>
          </div>)
          }
          
          <div className="flex flex-row flex-grow justify-between items-center space-x-8">
            <label className="text-lg text-gray-700">Opis</label>
            <div className="flex-grow ">
              <Textarea
                value={description}
                disabled={isDisabled}
                rows={10}
                color="orange"
                label="Opis"
                onChange={(e)=>{
                  setDescription(e.target.value)
                }}
              ></Textarea>
            </div>
          </div>
         
        </div>
        <div className="flex flex-row ml-auto items-center p-1 ">
             
            {isDisabled ? (
              <button
              data-testid="edytuj-button"
                disabled={!isAdmin}
                className="bg-gray-700 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-gray-800"
                onClick={() => {
                  setIsDisabled(false);
                
                }}
              >
                Edytuj
              </button>
            ) : (
              <div>
                <button
                  data-testid="Anuluj"
                  disabled={!isAdmin}
                  className="bg-gray-700 drop-shadow-md rounded-md mr-1 text-white font-bold text-md p-2 hover:bg-gray-800"
                  onClick={() => {
                    setIsDisabled(true);
                    setName(offerOriginalData.name);
                    setPrice(offerOriginalData.price);
                    setDescription(offerOriginalData.description);
                  }}
                >
                  Anuluj
                </button>
                <button
                data-testid="Zapisz"
                disabled={!isAdmin}
                  className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500"
                  type="submit"
                  onClick={handleEdit}
                >
                  Zapisz
                </button>
              </div>
            )}
          </div>
      </div>
    </div>
  )
}
