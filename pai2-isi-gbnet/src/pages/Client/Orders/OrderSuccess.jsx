import { useEffect, useState } from "react";
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
import {FcApproval} from 'react-icons/fc'
import jwt from "jwt-decode";
import {
  Spinner
} from "@material-tailwind/react";
export default function OrderSuccess() {
  const [payerId, setPayerId] = useState();
  const [paymentId, setPaymentId] = useState();
  const [loading,setLoading]=useState(true)
  const token = useAuthHeader();
  useEffect(() => {
    async function executePayment() {
      const currentUrl = window.location.href;
      const pattern = /order\/([^/]+)/;

      const match = currentUrl.match(pattern);

      const orderId = match[1];
      const searchParams = new URLSearchParams(new URL(currentUrl).search);
      const paymentId = searchParams.get("paymentId");
      const payerId = searchParams.get("PayerID");
      setPaymentId(paymentId);
      setPayerId(payerId);
      const userData = jwt(token());

      axios.defaults.headers.common["Authorization"] = token();
      const protectedEndpointResponse = await axios.get(
        "http://localhost:8080/upc/v1/user-role/user",
        {
          params: {
            email: userData.sub,
          },
          headers: {
            "Content-Type": "application/json",
          },
          data: {},
        }
      );
      const paymentUuid = localStorage.getItem("payment");
      console.log(paymentUuid);
      const uuu  = {
        payerId: payerId,
            clientUuid: protectedEndpointResponse.data.uuid,
            orderUuid: orderId,
            paymentUuid: paymentUuid
      }
      console.log(uuu)
      const apiUrl = `http://localhost:8080/upc/v1/user-role/payment/execute/${paymentId}`;
      axios.defaults.headers.common["Authorization"] = token();
      const response = await axios.post(
        apiUrl,
        {},
        {
          params: {
            payerId: payerId,
            clientUuid: protectedEndpointResponse.data.uuid,
            orderUuid: orderId,
            paymentUuid: paymentUuid
          },
        }
      );
      if(response.status === 200)
      {
        setLoading(false);
      }
    }

    executePayment();
  }, []);

  if (loading)
    return (
      <div className="flex flex-col bg-blue-gray-600 w-full h-full items-center justify-center">
        <Spinner color="amber" className="h-1/2 w-1/2"></Spinner>
      </div>
    );
  
  return(
    <div className="flex flex-col bg-blue-gray-600 w-full h-full items-center justify-center">
        <FcApproval className='text-amber-500 w-1/3 h-1/3'/>
        <span className='text-white text-2xl'>Płatność zaakceptowana!</span>
    </div>
)
}
