import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import axios from "axios";
import { Button } from "@material-tailwind/react";
import DeviceProduct from "../../../assets/DeviceProduct.png";
import InternetProduct from "../../../assets/InternetProduct.png";
import MobileProduct from "../../../assets/MobileProduct.png";
import TVproduct from "../../../assets/TVproduct.png";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
import jwt from "jwt-decode";

export default function ProductDetail() {
  const token = useAuthHeader();
  const userCred = useAuthUser()
  const { productId } = useParams();
  const [product, setProduct] = useState();
  const [img, setImg] = useState();
  const navigate = useNavigate();
  useEffect(() => {
    async function fetchData() {
      const data = jwt(token());
      const response = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-product`,
        {
          params: {
            uuid: productId,
          },
        }
      );
      setProduct(response.data);
      switch (response.data.productType) {
        case "MOBILE":
          setImg(MobileProduct);
          break;
        case "TV":
          setImg(TVproduct);
          break;
        case "INTERNET":
          setImg(InternetProduct);
          break;
        case "DEVICE":
          setImg(DeviceProduct);
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
        {product && (
          <div className="flex flex-row mx-8 mt-8 bg-blue-gray-900 p-4 items-center justify-between">
          <div className="flex flex-col w-4/5">
            <a className="text-amber-500 ml-4 font-medium text-xl">
              {product.name}
            </a>
            <a className="text-white ml-4 text-sm">
              {product.description}
            </a>
          </div>
          <div className="flex flex-col space-y-1 items-center justify-between">
            <a className="text-white ">CENA</a>
            <a className="text-amber-500 font-bold animate-bounce">
              {product.price.toFixed(2)} zł
            </a>
          </div>
        </div>
        )}
        
      </div>
    </div>
  );
}
