import { useParams } from "react-router-dom";
import axios from "axios";
import jwt from "jwt-decode";
import { useState, useEffect } from "react";
import { useAuthHeader, useAuthUser } from "react-auth-kit";
import { Textarea } from "@material-tailwind/react";

export default function AdminProductDetail() {
  const [productOriginalData, setProductOriginalData] = useState();
  const [productData, setProductData] = useState();
  const [productType, setProductType] = useState();
  const { productId } = useParams();
  const [isDisabled, setIsDisabled] = useState(true);
  const token = useAuthHeader();
  const userData = jwt(token())
    const [isAdmin,setIsAdmin] = useState(false)
    useEffect(()=>{
      if(userData.role.includes("ADMIN")){
        setIsAdmin(true)
      }
    },[])
  useEffect(() => {
    async function fetchProduct() {
      
      const protectedEndpointResponse = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-product`,
        {
          params: {
            uuid: productId,
          },
        }
      );
      setProductData(protectedEndpointResponse.data);
      setProductOriginalData(protectedEndpointResponse.data);
    }
    fetchProduct();
    setIsDisabled(true);
  }, [productId]);

  useEffect(() => {
    async function fetchProduct() {
      const productTypes = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-product-types`
      );
      setProductType(productTypes.data)
    }
    fetchProduct()
  }, []);

  async function handleSave(e) {
    e.preventDefault();
    axios.defaults.headers.common['Authorization'] = token();
    const apiUrl = "http://localhost:8080/upc/v1/worker-role/edit-product";
    const response = await axios.put(apiUrl, productData);
    if (response.status === 200) {
      const tab = JSON.parse(localStorage.getItem("notifications"));
      let newTab;
      const message = {
        message: `Pomyślnie zmieniono dane produktu ${productOriginalData.name}`,
        type: "SUCCESS",
      };
      if (tab) {
        newTab = [...tab, message];
      } else {
        newTab = [message];
      }

      window.localStorage.setItem("notifications", JSON.stringify(newTab));
      window.dispatchEvent(new Event("storage"));
      window.location.reload();
    }
  }
  
  return (
    <form onSubmit={handleSave} className="flex flex-direction flex-col bg-gray-100 w-full h-full">
      <div className="flex flex-col w-full items-center bg-gray-400 p-2">
        <span className="text-xl text-gray-800 font-semibold">
          {productOriginalData?.name}
        </span>
      </div>
      <div className="flex flex-col w-full space-y-8 items-center h-full">
        <div className="flex flex-col whitespace-nowrap p-8 h-full w-1/2 space-y-4">
          <div className="flex flex-row justify-between items-center">
            <a className="text-lg text-gray-700">Nazwa</a>
            <input
              value={productData?.name}
              disabled
              readOnly
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>
          <div className="flex flex-row  justify-between items-center">
            <a className="text-lg text-gray-700">Cena</a>
            <input
              value={productData?.price}
              disabled={isDisabled}
              onChange={(e) => {
                setProductData((prevState) => ({
                  ...prevState,
                  price: e.target.value,
                }));
              }}
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            />
          </div>

          <div className="flex flex-row justify-between items-center">
            <a className="text-lg text-gray-700">Typ</a>
            <select
              disabled
              readOnly
              className="px-2 py-2 border drop-shadow-lg border-gray-500 bg-gray-700 w-1/2 rounded-lg text-white text-lg disabled:bg-gray-500"
            >
                {productType?.map((item) => productData?.productType == item ? <option value={item} selected>{item}</option> : <option value={item}>{item}</option>)}
              
            </select>
          </div>
          <div className="flex flex-row flex-grow justify-between items-center space-x-8">
            <a className="text-lg text-gray-700">Opis</a>
            <div className="flex-grow ">
              <Textarea
                value={productData?.description}
                disabled={isDisabled}
                rows={10}
                color="orange"
                label="Opis"
                onChange={(e) => {
                  setProductData((prevState) => ({
                    ...prevState,
                    description: e.target.value,
                  }));
                }}
              ></Textarea>
            </div>
          </div>
          { isAdmin ?<div className="flex flex-row ml-auto items-center p-1 ">
            {isDisabled ? (
              <button
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
                  className="bg-gray-700 drop-shadow-md rounded-md mr-1 text-white font-bold text-md p-2 hover:bg-gray-800"
                  onClick={() => {
                    setIsDisabled(true);
                    setProductData(productOriginalData);
                  }}
                >
                  Anuluj
                </button>
                <button
                  className=" bg-green-400 drop-shadow-md rounded-md text-white font-bold text-md p-2 hover:bg-green-500"
                  type="submit"
                >
                  Zapisz
                </button>
              </div>
            )}
          </div>: null}
        </div>
      </div>
    </form>
  );
}
