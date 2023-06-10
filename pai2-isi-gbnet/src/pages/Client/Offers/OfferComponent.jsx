import { useEffect, useState } from "react";
import { CircleLoader } from "react-spinners";
import Internet_TVoffer from "../../../assets/Internet_TVoffer.png"
import Internetoffer from "../../../assets/Internetoffer.png"
import TVoffer from "../../../assets/TVoffer.png"
import { useNavigate } from "react-router-dom";
export default function Offer(props) {
  const navigate = useNavigate();
  const [img, setImg] = useState();
  useEffect(() => {
    const loadImage = () => {
      switch (props.type) {
        case 'INTERNET':
          setImg(Internetoffer)
          break;
        case 'TV':
          setImg(TVoffer)
          break;
        case 'INTERNET_PLUS_TV':
          setImg(Internet_TVoffer)
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
          {props.product ? "W zestawie z: " + props.product.name : <div className="h-[28px]"></div>}
        </div>
        <div className="flex flex-row border border-blue-gray-200">
          <div className="flex flex-col w-2/3">
            <text className="text-2xl font-bold text-center">
              {props.price ? props.price.toFixed(2) : "0.00"} zł
            </text>
            <text className="text-xl text-center">MIESIĘCZNIE</text>
          </div>
          <button className="w-1/3 font-bold bg-yellow-400 hover:bg-yellow-300" onClick={()=>navigate(`/offers/${props.id}`)}>Szczegóły</button>
        </div>
      </div>
    </div>
  );
}