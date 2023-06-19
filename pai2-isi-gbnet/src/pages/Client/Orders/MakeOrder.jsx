import axios from "axios";
import React, { useEffect, useState,useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useAuthHeader,useAuthUser } from "react-auth-kit";
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
} from "@material-tailwind/react";
import {
  AiFillInfoCircle,
  AiOutlineArrowLeft,
  AiOutlineArrowRight,
} from "react-icons/ai";
import { BiPlusMedical } from "react-icons/bi";
import { ImMinus } from "react-icons/im";

export default function MakeOrder() {
  const [offer, setOffer] = useState();
  const [withBonus, isWithBonus] = useState(false);
  const [products, setProducts] = useState();
  const [open, setOpen] = useState();
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [selectedPages, setSelectedPages] = useState();
  const [enums, setEnums] = useState();
  const [chosen, setChosen] = useState(null);
  const { offerId } = useParams();
  const token = useAuthHeader();
  const navigate = useNavigate();
  const contractRef = useRef();
  const handleOpen = (value) => {
    setOpen(open === value ? -1 : value);
  };
  const calculatePrice = () => {
    let price = 0;
    selectedProducts.forEach((p) => {
      price += p.price;
    });
    return price;
  };
  const toggleOpen = () => {
    isWithBonus((cur) => !cur);
  };
  useEffect(() => {
    const itemCount = selectedProducts.length;
    const totalPages = Math.ceil(itemCount / itemsPerPage);
    setSelectedPages(totalPages);
  }, [selectedProducts]);

  useEffect(() => {
    async function fetchData() {
      const response = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-offer-by-uuid`,
        {
          params: {
            uuid: offerId,
          },
        }
      );
      setOffer(response.data);
    }
    async function fetchEnums() {
      // axios.defaults.headers.common["Authorization"] = token();
      const response = await axios.get(
        `http://localhost:8080/upc/unsecured/v1/get-contract-lengths`
      );
      setEnums(response.data);
    }
    const items = JSON.parse(localStorage.getItem("selectedProducts"));
    const contractLength = JSON.parse(localStorage.getItem("contract"));
    if (items) {
      setSelectedProducts(items);
    }
    if (contractLength) {
      setChosen(contractLength);
    }
    fetchData().catch(console.error);
    fetchEnums().catch(console.error);
  }, []);
  useEffect(() => {
    async function fetchProducts() {
      await axios
        .get("http://localhost:8080/upc/unsecured/v1/get-all-products")
        .then((data) => {
          const tab = data.data.content.filter(
            (product) => product.uuid !== offer?.productDto.uuid
          );
          setProducts(tab);
        })
        .catch((err) => console.log(err));
    }
    fetchProducts();
  }, [offer]);
  const [active, setActive] = React.useState(1);
  const [activeSelected, setActiveSelected] = React.useState(1);
  const itemsPerPage = 5;

  const totalItems = products ? products.length : 0;
  const totalPages = Math.ceil(totalItems / itemsPerPage);

  const getItemProps = (index) => ({
    variant: active === index ? "filled" : "text",
    color: active === index ? "amber" : "blue-gray",
    onClick: () => setActive(index),
  });

  const next = () => {
    if (active === totalPages) return;

    setActive(active + 1);
    setOpen(-1);
  };

  const prev = () => {
    if (active === 1) return;
    setActive(active - 1);
    setOpen(-1);
  };
  const nextSelected = () => {
    if (activeSelected === selectedPages) return;

    setActiveSelected(activeSelected + 1);
  };

  const prevSelected = () => {
    if (activeSelected === 1) return;

    setActiveSelected(activeSelected - 1);
  };

  const getPaginatedProducts = () => {
    const startIndex = (active - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    return products ? products.slice(startIndex, endIndex) : [];
  };
  const getPaginatedSelectedProducts = () => {
    const startIndex = (activeSelected - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    return selectedProducts ? selectedProducts.slice(startIndex, endIndex) : [];
  };

  const handleSubmit = () => {
    if (selectedProducts.length > 0) {
      localStorage.setItem(
        "selectedProducts",
        JSON.stringify(selectedProducts)
      );
    }
    localStorage.setItem("offer", JSON.stringify(offer));
    localStorage.setItem("contract", JSON.stringify(contractRef.current.value));
    navigate("/orderSummary");
  };

  return (
    <div className="flex flex-col basis-4/5 h-full bg-blue-gray-800 items-center">
      <div className="flex flex-col m-4 w-11/12 h-hull bg-blue-gray-700 p-4">
        <Button
          onClick={() => {
            navigate(`/offers/${offerId}`);
          }}
          color="amber"
          className="w-1/6"
        >
          Powrót
        </Button>
        <div className="hidden lg:flex flex-row self-center space-x-4 my-4 p-4 w-11/12 bg-blue-gray-500">
          <AiFillInfoCircle className="basis-1/12 text-amber-500 h-full w-full"></AiFillInfoCircle>
          <p className="text-xl text-white basis-11/12">
            Poniżej znajdują się szczegóły dotyczące Twojego zamówienia.
            Sprawdź, czy wszystko się zgadza i zapoznaj się z produktami
            dodatkowymi przygotowanymi z myślą o Tobie.
          </p>
        </div>
        <Typography variant="h1" color="amber">
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
        <Typography variant="h2" color="amber">
          Wybierz okres zoobowiązania:
        </Typography>
        <select className="w-1/4 p-2 bg-blue-gray-500 text-white font-bold" ref={contractRef}>
          {enums?.map((value)=> chosen == value? <option value={value} selected>{value === "TWENTY_FOUR" ? "24 miesiące" : "12 miesiący"}</option> : <option value={value}>{value === "TWENTY_FOUR" ? "24 miesiące" : "12 miesiący"}</option>)}
          
        </select>
        <Accordion open={withBonus} className="z-30">
          <AccordionHeader
            onClick={toggleOpen}
            className="w-3/4 self-center text-amber-500 hover:text-amber-600 "
          >
            Wybierz produkt(y) dodatkowy/e
          </AccordionHeader>
          <AccordionBody >
            <Card color="blue-gray" className=" z-30 w-11/12 mx-auto">
              <CardBody>
                <List>
                  {getPaginatedProducts().map((product, index) => {
                    return (
                      <Accordion open={open === index}>
                        <ListItem
                          ripple={false}
                          className="font-bold text-white hover:bg-blue-gray-800 hover:text-white active:bg-blue-gray-800 active:text-white"
                          onClick={() => handleOpen(index)}
                        >
                          {product.name}
                          <span className="ml-auto mr-10">
                            {product.price.toFixed(2)} zł
                          </span>
                          <ListItemSuffix className="ml-0">
                            {selectedProducts.includes(product) ? (
                              <IconButton
                                color="red"
                                onClick={(e) => {
                                  e.stopPropagation();
                                  const updatedSelectedProducts =
                                    selectedProducts.filter(
                                      (item) => item !== product
                                    );
                                  setSelectedProducts(updatedSelectedProducts);
                                }}
                              >
                                <ImMinus className="w-5 h-5"></ImMinus>
                              </IconButton>
                            ) : (
                              <IconButton
                                color="amber"
                                onClick={(e) => {
                                  e.stopPropagation();
                                  setSelectedProducts([
                                    ...selectedProducts,
                                    product,
                                  ]);
                                }}
                              >
                                <BiPlusMedical className="w-5 h-5"></BiPlusMedical>
                              </IconButton>
                            )}
                          </ListItemSuffix>
                        </ListItem>
                        <AccordionBody className="text-white">
                          {product.description}
                        </AccordionBody>
                      </Accordion>
                    );
                  })}
                </List>
                <div className="flex items-center gap-8 justify-center">
                  <IconButton
                    size="sm"
                    color="amber"
                    onClick={prev}
                    disabled={active === 1}
                  >
                    <AiOutlineArrowLeft strokeWidth={2} className="h-4 w-4" />
                  </IconButton>
                  <Typography color="white" className="font-normal">
                    Page <strong className="text-amber-500">{active}</strong> of{" "}
                    <strong className="text-amber-500">{totalPages}</strong>
                  </Typography>
                  <IconButton
                    size="sm"
                    color="amber"
                    onClick={next}
                    disabled={active === totalPages}
                  >
                    <AiOutlineArrowRight strokeWidth={2} className="h-4 w-4" />
                  </IconButton>
                </div>
              </CardBody>
            </Card>
          </AccordionBody>
        </Accordion>
        <div className="flex flex-col w-full mt-4 bg-blue-gray-600 p-4">
          <Typography
            variant="h2"
            color="amber"
            className="border-b border-gray-300"
          >
            Podsumowanie
          </Typography>
          {selectedProducts.length > 0 ? (
            <Card color="blue-gray" className="w-full ">
              <Typography variant="h3" color="amber" className="mx-auto">
                KOSZYK
              </Typography>
              <CardBody className="p-1 ">
                <List>
                  {getPaginatedSelectedProducts().map((product, index) => {
                    return (
                      <ListItem className="text-white">
                        <span className="flex-grow">{product.name}</span>
                        <ListItemSuffix className="ml-0">
                          <IconButton
                            color="red"
                            onClick={(e) => {
                              e.stopPropagation();
                              const updatedSelectedProducts =
                                selectedProducts.filter(
                                  (item) => item !== product
                                );
                              setSelectedProducts(updatedSelectedProducts);
                            }}
                          >
                            <ImMinus className="w-5 h-5"></ImMinus>
                          </IconButton>
                        </ListItemSuffix>
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
                      <strong className="text-amber-500">
                        {activeSelected}
                      </strong>{" "}
                      of{" "}
                      <strong className="text-amber-500">
                        {selectedPages}
                      </strong>
                    </Typography>
                    <IconButton
                      size="sm"
                      color="amber"
                      onClick={nextSelected}
                      disabled={activeSelected === selectedPages}
                    >
                      <AiOutlineArrowRight
                        strokeWidth={2}
                        className="h-4 w-4"
                      />
                    </IconButton>
                  </div>
                </List>
              </CardBody>
            </Card>
          ) : null}
          <Typography variant="h4" className="ml-auto text-white mt-2">
            Opłata miesięczna: {offer?.price.toFixed(2)} zł
          </Typography>
          {selectedProducts.length > 0 ? (
            <Typography variant="h4" className="ml-auto text-white">
              Opłata jednorazowa w pierwszym miesiącu:{" "}
              {calculatePrice().toFixed(2)} zł
            </Typography>
          ) : null}
        </div>
        <Button color="amber" className="mt-4 ml-auto" onClick={handleSubmit}>
          Podsumowanie
        </Button>
      </div>
    </div>
  );
}
