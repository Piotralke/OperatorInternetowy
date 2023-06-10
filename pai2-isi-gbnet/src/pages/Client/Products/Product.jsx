import { useEffect, useState } from "react";
import { CircleLoader } from "react-spinners";
import MobileProduct from "../../../assets/MobileProduct.png"
import InternetProduct from "../../../assets/InternetProduct.png"
import TVproduct from "../../../assets/TVproduct.png"
import DeviceProduct from "../../../assets/DeviceProduct.png"
import { useNavigate } from "react-router-dom";
export default function Product(props) {
  const navigate = useNavigate();
  const [img, setImg] = useState();
  useEffect(() => {
    const loadImage = () => {
      switch (props.type) {
        case 'INTERNET':
          setImg(InternetProduct)
          break;
        case 'TV':
          setImg(TVproduct)
          break;
        case 'MOBILE':
          setImg(MobileProduct)
          break;
        case 'DEVICE':
          setImg(DeviceProduct)
          break;
      }
    };
    loadImage();
  }, []);
  return (
    <div className="flex flex-col  pb-4 m-4 space-y-3 border rounded-md bg-white ">
      {img ? (
        <img src={img} className="w-full rounded-t-md h-[20vh] "></img>
      ) : (
        <div className="w-full rounded-t-md h-[20vh] flex justify-center items-center">
          <div className="text-xl">...</div>
        
        </div>
      )}

      <div className="px-3 space-y-2">
        <div className="text-xl font-bold">{props.title}</div>
        {/* {props.installation? (<div className="text-lg text-blue-500">Instalacja za: {props.installation} zł</div>):null}
                {props.activation? (<div className="text-lg text-blue-500">Aktywacja za: {props.activation} zł</div>):null} */}
        <div className="text-lg text-blue-500">
        </div>
        <div className="flex flex-row border border-blue-gray-200">
          <div className="flex flex-col w-2/3">
            <text className="text-2xl font-bold text-center">
              {props.price ? props.price.toFixed(2) : "0.00"} zł
            </text>
          </div>
          <button className="w-1/3 font-bold bg-yellow-400 hover:bg-yellow-300" >Szczegóły</button>
        </div>
      </div>
    </div>
  );
}
