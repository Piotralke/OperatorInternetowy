import { useEffect, useState } from "react";
import axios from "axios";
import { useAuthHeader } from "react-auth-kit";
import jwt from "jwt-decode";
export default function OrderSuccess() {
  const [payerId, setPayerId] = useState();
  const [paymentId, setPaymentId] = useState();
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
    
      const apiUrl = `http://localhost:8080/upc/v1/user-role/payment/execute/${paymentId}`;
      axios.defaults.headers.common["Authorization"] = token();
      const response = await axios.post(
        apiUrl,
        {},
        {
          params: {
            payerId: payerId,
            clientUuid: protectedEndpointResponse.data.uuid,
          },
        }
      );
      console.log(response);
    }

    executePayment();
  }, []);
  return (
    <div>
      <div>PayerID: {payerId}</div>
      <div>Payment Id : {paymentId}</div>
    </div>
  );
}
