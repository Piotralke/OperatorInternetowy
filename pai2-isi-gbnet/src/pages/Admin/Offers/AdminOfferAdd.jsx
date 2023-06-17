import React, { useState, useEffect, useRef } from "react";
import { Textarea } from "@material-tailwind/react";
import axios from "axios";
import jwt from "jwt-decode";
import { useAuthHeader } from "react-auth-kit";
import { Outlet } from "react-router-dom";
export default function AdminOfferAdd() {
  const [isDevice, setIsDevice] = useState(false);
  const [productData, setProductData] = useState();
  const [offerTypesData, setOfferTypesData] = useState();
  const [newDevice, setNewDevice] = useState(false);
  const [description, setDescription]=useState("");
  const [deviceDescription, setDeviceDescription]=useState("");
  const token = useAuthHeader();
  const nameRef = useRef();
  const priceRef = useRef();
  const typeRef = useRef();
  const deviceRef = useRef();
  const deviceNameRef=useRef();
  const devicePriceRef = useRef();
  const deviceTypeRef = useRef();
  const [productTypesData, setProductTypesData] = useState();
  useEffect(() => {
    async function fetchProduct() {
      const ResponseProductTypes = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-product-types`
      );
      setProductTypesData(ResponseProductTypes.data);
    }
    fetchProduct();
  }, []);
  useEffect(() => {
    async function fetchProduct() {
      const ResponseProduct = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-all-products`
      );
      console.log(ResponseProduct.data.content);
      setProductData(ResponseProduct.data.content);
    }
    fetchProduct();
  }, []);

  useEffect(() => {
    async function fetchProduct() {
      const ResponseProductTypes = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-types`
      );
      setOfferTypesData(ResponseProductTypes.data);
    }
    fetchProduct();
  }, []);

  async function handleSubmit(e) {
    e.preventDefault()
    const user = jwt(token());

    let data;
    if(isDevice)
    {
      if(newDevice)
      {
        data = {
          name: nameRef.current.value,
          description: description,
          price: parseFloat(priceRef.current.value),
          SaveProductWithOfferRequestDto: {
            uuid: null,
            name: deviceNameRef.current.value,
            price: parseFloat(devicePriceRef.current.value),
            description: deviceDescription,
            productType: deviceTypeRef.current.value
          },
          withDevice: true,
          offerType: typeRef.current.value
          
        }
      }
      else
      {
        data = {
          name: nameRef.current.value,
          description: description,
          price:parseFloat(priceRef.current.value) ,
          SaveProductWithOfferRequestDto: {
            uuid: deviceRef.current.options[deviceRef.current.selectedIndex].id,
          },
          withDevice: true,
          offerType: typeRef.current.value,
          
        }
      }
    }
    else
    {
      data = {
        name: nameRef.current.value,
        description: description,
        price: parseFloat(priceRef.current.value),
        SaveProductWithOfferRequestDto: null,
        withDevice: false,
        offerType: typeRef.current.value,
       
      }
    }
    console.log(data)
     const apiUrl = "http://localhost:8080/upc/unsecured/v1/save-offer";
     const response = await axios.post(apiUrl, data);
     console.log(response);
     if(response.status === 200)
     {
        const tab = JSON.parse(localStorage.getItem("notifications"));
        let newTab;
        if(tab)
        {
          newTab = [...tab,`Pomyślnie dodano nową ofertę ${data.name}`];
        }else{
          newTab = [`Pomyślnie dodano nową ofertę ${data.name}`];
        }
        
        window.localStorage.setItem("notifications",JSON.stringify(newTab));
        window.dispatchEvent(new Event("storage"))
        window.location.reload();
     }
  }

  return (
    <div className="flex flex-col h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2 ">
        <span className="text-xl text-gray-800 font-semibold">
          Dodaj ofertę
        </span>
      </div>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col p-8 bg-gray-200 h-full justify-between"
      >
        <div className="grid gap-10 px-48 ">
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Nazwa</label>
            <input
              type="text"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
              ref={nameRef}
            />
          </div>
          <div className="grid gap-10 md:grid-cols-2">
            <div className="flex flex-col">
              <label className="text-md font-bold text-gray-700 ">Cena</label>
              <input
                type="number"
                className="border-2 border-gray-500 rounded-md mt-1 text-gray-900 px-4 py-1"
                required
                ref={priceRef}
              />
            </div>
            <div className="flex flex-col">
              <label className="text-md font-bold text-gray-700 ">Typ</label>
              <select
                type="text"
                ref={typeRef}
                className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              >
                {offerTypesData?.map((item, index) => (
                  <option id={index} value={item}>
                    {item}
                  </option>
                ))}
              </select>
            </div>
            <div className="flex flex-row items-center justify-between">
              <div className="flex flex-row items-center space-x-4 my-4">
                <input
                  type="checkbox"
                  className="scale-150"
                  onChange={() => {
                    if (isDevice) {
                      setIsDevice(false);
                      setNewDevice(false);
                      setDeviceDescription("");
                    } else {
                      setIsDevice(true);
                    }
                  }}
                />
                <label className="text-lg font-bold text-gray-700">
                  Urządzenie w zestawie
                </label>
              </div>

              {isDevice ? (
                <div className="flex flex-row">
                  <div className="flex flex-col flex-grow ml-4">
                    <label className="text-md font-bold text-gray-700 ">
                      Wybierz urządzenie
                    </label>
                    <select
                      type="text"
                      ref={deviceRef}
                      disabled={newDevice}
                      className="border-2 w-full border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
                      
                    >
                      {productData?.map((item, index) => (
                        <option  id={item.uuid} value={item.name}>
                          {item.name}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
              ) : null}
            </div>
            {isDevice ? (
              <div className="flex flex-row items-center space-x-4">
                <input
                  type="checkbox"
                  className="scale-150"
                  onChange={() => {
                    setNewDevice(!newDevice);
                    setDeviceDescription("")
                  }}
                />
                <label className="text-lg font-bold text-gray-700">
                  Dodaj nowe
                </label>
              </div>
            ) : null}
          </div>
          {newDevice ? (
            <div className="flex flex-col h-full border border-gray-400 rounded-xl">
              <div className=" rounded-t-xl flex flex-col w-full items-center bg-gray-400 p-2 ">
                <span className="text-xl text-gray-800 font-semibold">
                  Dodaj produkt
                </span>
              </div>
              <div className="grid gap-10 px-48 ">
                <div className="flex flex-col">
                  <label className="text-md font-bold text-gray-700 ">
                    Nazwa
                  </label>
                  <input
                    ref={deviceNameRef}
                    type="text"
                    className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
                    required
                  />
                </div>
                <div className="grid gap-10 md:grid-cols-2">
                  <div className="flex flex-col">
                    <label className="text-md font-bold text-gray-700 ">
                      Cena
                    </label>
                    <input
                      type="number"
                      ref={devicePriceRef}
                      className="border-2 border-gray-500 rounded-md mt-1 text-gray-900 px-4 py-1"
                      required
                    />
                  </div>
                  <div className="flex flex-col">
                    <label className="text-md font-bold text-gray-700 ">
                      Typ
                    </label>
                    <select
                      type="text"
                      ref={deviceTypeRef}
                      className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
                    >
                      {productTypesData?.map((item, index) => (
                        <option id={index} value={item}>
                          {item}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
                <div className="flex-grow ">
                  <label className="text-md font-bold text-gray-700 ">
                    Opis
                  </label>
                  <Textarea
                    rows={10}
                    color="orange"
                    value={deviceDescription}
                    onChange={(e)=>{setDeviceDescription(e.target.value)}}
                  ></Textarea>
                </div>
              </div>
            </div>
          ) : null}
          <div className="flex-grow ">
            <label className="text-md font-bold text-gray-700 ">Opis</label>
            <Textarea
              rows={10}
              color="orange"
              value={description}
              onChange={(e)=>{setDescription(e.target.value)}}
            ></Textarea>
          </div>
        </div>
        <div className="flex flex-col w-full mt-8 items-center">
          <button
            type="submit"
            className="bg-green-300 py-2 w-1/2 rounded-md text-gray-900 text-xl shadow-md hover:bg-green-500"
          >
            Dodaj
          </button>
        </div>
      </form>
    </div>
  );
}
