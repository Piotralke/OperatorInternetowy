import Product from "../components/Product";
import axios from "axios";
import { useEffect, useState, useRef } from "react";

export default function Products() {
  const [products, setProducts] = useState([]);
  const internetRef = useRef(null);
  const telewizjaRef = useRef(null);
  const mobileRef = useRef(null);
  const deviceRef = useRef(null);

  useEffect(() => {
    async function fetchData() {
      await axios
        .get("http://localhost:8080/upc/unsecured/v1/get-all-products")
        .then((data) => {
          console.log(data.data.content)
          setProducts(data.data.content);
        })
        .catch((err) => console.log(err));
    }
    fetchData().catch(console.error);
  }, []);

  const scrollToRef = (ref) => {
    window.scrollTo({
      top: ref.current.offsetTop,
      behavior: "smooth",
    });
  };

  return (
    <div className="flex flex-col w-full min-h-full bg-blue-gray-300 basis-4/5">
      <div className="w-full" ref={internetRef} >
        <div className="flex flex-row fixed space-x-5 bg-blue-gray-600 w-full bg-opacity-70">
            <button className="rounded-xl text-2xl hover:font-bold italic "onClick={() => scrollToRef(internetRef)}>Internet</button>
            <button className="rounded-xl text-2xl hover:font-bold italic"onClick={() => scrollToRef(telewizjaRef)}>Telewizja</button>
            <button className="rounded-xl text-2xl hover:font-bold italic"onClick={() => scrollToRef(mobileRef)}>Telefon</button>
            <button className="rounded-xl text-2xl hover:font-bold italic"onClick={() => scrollToRef(deviceRef)}>Inne urządzenia</button>
        </div>
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r mt-8 from-blue-500 text-white p-3 font-bold"
          
        >
          Internet
        </div>
        <div className="grid grid-cols-3 gap-4" >
          {products
            ?.filter((product) => product.productType === "INTERNET")
            .map((product) => (
              <Product
                id={product.uuid}
                title={product.name}
                price={product.price}
                key={product.uuid}
                type={product.productType}
              />
            ))}
        </div>
      </div>
      <div className="w-full"ref={telewizjaRef} >
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r from-blue-500 text-white p-3 font-bold"
          
        >
          Telewizja
        </div>
        <div className="grid grid-cols-3 gap-4" >
          {products
            ?.filter((product) => product.productType === "TV")
            .map((product) => (
              <Product
              id={product.uuid}
              title={product.name}
              price={product.price}
              key={product.uuid}
              type={product.productType}
              />
            ))}
        </div>
      </div>
      <div className="w-full" ref={mobileRef} >
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r from-blue-500 text-white p-3 font-bold"
          
        >
          Telefony
        </div>
        <div className="grid grid-cols-3 gap-4">
          {products
            ?.filter((product) => product.productType === "MOBILE")
            .map((product) => (
              <Product
              id={product.uuid}
              title={product.name}
              price={product.price}
              key={product.uuid}
              type={product.productType}
              />
            ))}
        </div>
      </div>
      <div className="w-full" ref={deviceRef} >
        <div
          className="w-1/2 text-lg rounded-full bg-gradient-to-r from-blue-500 text-white p-3 font-bold"
          
        >
          Pozostałe urządzenia
        </div>
        <div className="grid grid-cols-3 gap-4">
          {products
            ?.filter((product) => product.productType === "DEVICE")
            .map((product) => (
              <Product
              id={product.uuid}
              title={product.name}
              price={product.price}
              key={product.uuid}
              type={product.productType}
              />
            ))}
        </div>
      </div>
    </div>
  );
}
