import { useParams, useNavigate, Navigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import {useAuthHeader,useAuthUser} from 'react-auth-kit'
import axios from "axios";
import jwt from "jwt-decode";
import {
  Button,
  Typography,
  Accordion,
  Card,
  CardBody,
  AccordionHeader,
  AccordionBody,
  List,
  ListItem,
  ListItemSuffix,
  IconButton,
  Checkbox,
} from "@material-tailwind/react";
import {
  AiFillInfoCircle,
  AiOutlineArrowLeft,
  AiOutlineArrowRight,
} from "react-icons/ai";
import { BiPlusMedical } from "react-icons/bi";
import { ImMinus } from "react-icons/im";
export default function OrderSummary() {
  const [offer, setOffer] = useState();
  const [userData, setUserData] = useState();
  const [products, setProducts] = useState(null);
  const [contractLength, setContractLength] = useState();
  const [selectedPages, setSelectedPages] = useState();
  const [activeSelected, setActiveSelected] = React.useState(1);
  const [checked, setChecked] = useState(false);
  const itemsPerPage = 5;
  const navigate = useNavigate();
  const token = useAuthHeader();
  const userCred = useAuthUser()
  useEffect(() => {
    async function getUserData() {
      const data = jwt(token());
      const credentials = userCred().data
      const protectedEndpointResponse = await axios.get(
        "http://localhost:8080/upc/v1/user-role/user",
        {
          params: {
            email: data.sub,
          },
          auth : {
            username: credentials.email,
            password: credentials.password
          },
          headers:{
            "Content-Type": "application/json"
          },
          data:{}
        }
      );
      console.log(protectedEndpointResponse.data);
      setUserData(protectedEndpointResponse.data);
    }
    getUserData();
    const of = JSON.parse(localStorage.getItem("offer"));
    const contractLen = JSON.parse(localStorage.getItem("contract"));
    const items = JSON.parse(localStorage.getItem("selectedProducts"));
    if (items) {
      setProducts(items);
    }
    setOffer(of);
    setContractLength(contractLen);
  }, []);
  useEffect(() => {
    const itemCount = products?.length;
    const totalPages = Math.ceil(itemCount / itemsPerPage);
    setSelectedPages(totalPages);
  }, [products]);
  const calculatePrice = () => {
    let price = 0;
    products.forEach((p) => {
      price += p.price;
    });
    return price;
  };
  const getPaginatedSelectedProducts = () => {
    const startIndex = (activeSelected - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    return products ? products.slice(startIndex, endIndex) : [];
  };
  const nextSelected = () => {
    if (activeSelected === selectedPages) return;

    setActiveSelected(activeSelected + 1);
  };

  const prevSelected = () => {
    if (activeSelected === 1) return;

    setActiveSelected(activeSelected - 1);
  };

  async function handleSubmit() {
    console.log(products);
    let prodUuids;
    if (products?.length > 0) prodUuids = products.map((p) => p.uuid);
    else prodUuids = null;
    const data = {
      clientEmail: userData.email,
      employeeEmail: "",
      productUuids: prodUuids,
      offerUuid: offer.uuid,
      contractLength: contractLength,
    };
    const credentials = userCred().data
    console.log(data);
    const response = await axios.post(
      `http://localhost:8080/upc/v1/user-role/save-order`,
      data,{
        auth : {
          username: credentials.email,
          password: credentials.password
        },
        headers:{
          "Content-Type": "application/json"
        },
        data:{}
      }
    );

    const paymentData = {
      orderUuid: response.data,
      clientUuid: userData.uuid,
      serviceUuid: "",
      successUrl: `http://localhost:5173/order/${response.data}/success/`,
      cancelUrl: `http://localhost:5173/order/${response.data}/cancel/`,
    };
    console.log(paymentData);
    const link = await axios.post(
      `http://localhost:8080/upc/v1/user-role/payment/create`,
      paymentData,
      {
        auth : {
          username: credentials.email,
          password: credentials.password
        },
        headers:{
          "Content-Type": "application/json"
        },
        data:{}
      }
    );
    window.location.href = link.data;
  }

  return (
    <div className="basis-4/5 h-full flex flex-col bg-blue-gray-600">
      <div className="flex flex-col m-4 w-11/12 h-hull bg-blue-gray-700 p-4 mx-auto">
        <Button
          onClick={() => {
            navigate(`/order/${offer?.uuid}`);
          }}
          color="amber"
          className="w-1/6"
        >
          Powrót
        </Button>
        <Typography
          variant="h1"
          color="amber"
          className="border-b border-gray-300"
        >
          Podsumowanie
        </Typography>
        <div className="hidden lg:flex flex-row self-center space-x-4 my-4 p-4 w-11/12 bg-blue-gray-500">
          <AiFillInfoCircle className="basis-1/12 text-amber-500 h-full w-full"></AiFillInfoCircle>
          <p className="text-xl text-white basis-11/12">
            Poniżej znajdują się szczegóły dotyczące Twojego zamówienia.
            Sprawdź, czy wszystko się zgadza.
          </p>
        </div>
        <Typography
          variant="h1"
          color="amber"
          className="border-b border-gray-300"
        >
          Wybrana oferta:
        </Typography>
        <Typography variant="h2" color="amber" className="mt-2">
          {offer?.name}
        </Typography>
        <p className="text-lg text-white bg-blue-gray-600 p-4">
          {offer?.description}
        </p>
        {offer?.withDevice ? (
          <div className="my-4">
            <Typography variant="h1" color="white">
              W zestawie z urządzeniem:
            </Typography>
            <div className="flex flex-col xl:flex-row xl:items-end ">
              <Typography variant="h2" color="amber" className="xl:flex-grow">
                {offer?.productDto.name}
              </Typography>
              <div className="flex flex-row space-x-2">
                <p className="text-red-600 text-xl line-through font-medium">
                  {offer.productDto.price.toFixed(2)} zł
                </p>
                <p className="text-white text-xl ">OPŁATA w zestawie</p>
                <p className="text-white text-xl font-bold tru">0.00 zł</p>
              </div>
            </div>
            <p className="text-lg text-white bg-blue-gray-600 p-4">
              {offer?.productDto.description}
            </p>
          </div>
        ) : null}
        <Typography variant="h3" color="amber" className="my-2">
          Okres zoobowiązania:{" "}
          {contractLength === "TWENTY_FOUR" ? "24 miesiące" : "12 miesiący"}
        </Typography>
        {products?.length > 0 ? (
          <Card color="blue-gray" className="w-full my-4">
            <Typography variant="h3" color="amber" className="mx-auto">
              Dodatkowe produkty
            </Typography>
            <CardBody className="p-1 ">
              <List>
                {getPaginatedSelectedProducts().map((product, index) => {
                  return (
                    <ListItem key={index} className="text-white">
                      <span className="flex-grow">{product.name}</span>
                    </ListItem>
                  );
                })}
                <div className="flex items-center gap-8 justify-center">
                  <IconButton
                    size="sm"
                    color="amber"
                    onClick={prevSelected}
                    disabled={activeSelected === 1}
                  >
                    <AiOutlineArrowLeft strokeWidth={2} className="h-4 w-4" />
                  </IconButton>
                  <Typography color="white" className="font-normal">
                    Page{" "}
                    <strong className="text-amber-500">{activeSelected}</strong>{" "}
                    of{" "}
                    <strong className="text-amber-500">{selectedPages}</strong>
                  </Typography>
                  <IconButton
                    size="sm"
                    color="amber"
                    onClick={nextSelected}
                    disabled={activeSelected === selectedPages}
                  >
                    <AiOutlineArrowRight strokeWidth={2} className="h-4 w-4" />
                  </IconButton>
                </div>
              </List>
            </CardBody>
          </Card>
        ) : null}
        <Typography
          variant="h2"
          color="amber"
          className="border-b border-gray-300"
        >
          Dane zamawiającego:
        </Typography>
        <div className="flex flex-col w-full bg-blue-gray-600 p-4 my-4 text-white text-xl space-y-2 font-bold">
          <div>
            <span className="font-normal">Imię i nazwisko: </span>
            {userData?.firstName} {userData?.lastName}
          </div>
          <div>
            <span className="font-normal">PESEL: </span>
            {userData?.pesel}
          </div>
          <div>
            <span className="font-normal">Adres e-mail: </span>
            {userData?.email}
          </div>
          <div>
            <span className="font-normal">Adres świadczenia usług: </span>
            {userData?.address}
          </div>
          <div>
            <span className="font-normal">Nr telefonu: </span>
            {userData?.phoneNumber}
          </div>
          {userData?.isBusinessClient ? (
            <div>
              <span className="font-normal">NIP: </span>
              {userData?.nip}
            </div>
          ) : null}
        </div>
        <div className="flex flex-col w-full bg-blue-gray-600 p-4">
          <Typography
            variant="h3"
            color="amber"
            className="border-b w-full border-gray-300"
          >
            Koszta
          </Typography>
          <Typography variant="h4" className="ml-auto text-white mt-2">
            Opłata miesięczna: {offer?.price.toFixed(2)} zł
          </Typography>
          {products?.length > 0 ? (
            <Typography variant="h4" className="ml-auto text-white">
              Opłata jednorazowa w pierwszym miesiącu:{" "}
              {calculatePrice().toFixed(2)} zł
            </Typography>
          ) : null}
        </div>
        <div className="flex flex-row ">
          <Checkbox
            color="amber"
            className="text-white"
            checked={checked}
            onChange={()=>{setChecked(!checked)}}
            label={
              <>
                <Typography color="white" className="font-medium flex">
                  Wyrażam zgodę na przetwarzanie moich danych osobowych.
                </Typography>
                <Typography color="amber" className="font-medium flex">
                  Jestem świadom/a, że po kliknięciu przycisku "ZAPŁAĆ"
                  zostaje zawarta umowa.
                </Typography>
              </>
            }
          ></Checkbox>
          <Button onClick={handleSubmit} disabled={!checked} color="amber" className="mt-4 ml-auto">
            Zapłać
          </Button>
        </div>
      </div>
    </div>
  );
}
