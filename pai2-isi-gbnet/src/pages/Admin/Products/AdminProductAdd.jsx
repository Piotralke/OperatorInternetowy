import React, { useState, useEffect,useRef } from "react";
import { Textarea } from "@material-tailwind/react";
import axios from "axios";

export default function AdminProductAdd() {
  const [productTypesData, setProductTypesData] = useState();
  const [deviceDescription, setDeviceDescription] = useState("");
  const deviceNameRef = useRef();
  const devicePriceRef = useRef();
  const deviceTypeRef = useRef();
  useEffect(() => {
    async function fetchProduct() {
      const ResponseProductTypes = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-product-types`
      );
      setProductTypesData(ResponseProductTypes.data);
    }
    fetchProduct();
  }, []);

  async function handleSubmit(e) {
    e.preventDefault()
    // const user = jwt(token());

    const  data ={
      name: deviceNameRef.current.value,
      price: parseFloat(devicePriceRef.current.value),
      description: deviceDescription,
      productType: deviceTypeRef.current.value
    };
    
    console.log(data)
     const apiUrl = "http://localhost:8080/upc/unsecured/v1/save-product";
     const response = await axios.post(apiUrl, data);
     console.log(response);
  }

  return (
    <div className="flex flex-col h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2 ">
        <span className="text-xl text-gray-800 font-semibold">
          Dodaj produkt
        </span>
      </div>
      <form onSubmit={handleSubmit} className="flex flex-col p-8 bg-gray-200 h-full justify-between">
        <div className="grid gap-10 px-48 ">
          <div className="flex flex-col">
            <label className="text-md font-bold text-gray-700 ">Nazwa</label>
            <input
              type="text"
              className="border-2 border-gray-500 rounded-md mt-1 text-gray-900  px-4 py-1"
              required
              ref={deviceNameRef}
            />
          </div>
          <div className="grid gap-10 md:grid-cols-2">
            <div className="flex flex-col">
              <label className="text-md font-bold text-gray-700 ">Cena</label>
              <input
                type="number"
                className="border-2 border-gray-500 rounded-md mt-1 text-gray-900 px-4 py-1"
                required
                ref={devicePriceRef}
              />
            </div>
            <div className="flex flex-col">
              <label className="text-md font-bold text-gray-700 ">Typ</label>
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
            <label className="text-md font-bold text-gray-700 ">Opis</label>
            <Textarea
              value={deviceDescription}
              onChange={(e)=>{setDeviceDescription(e.target.value)}}
              rows={10}
              color="orange"
              className="mt-"
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
