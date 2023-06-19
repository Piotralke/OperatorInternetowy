import React, { useEffect, useState } from "react";
import { Card, Typography } from "@material-tailwind/react";
import { BiShowAlt } from "react-icons/bi";
import { AiOutlineRight, AiOutlineLeft } from "react-icons/ai";
import { BsPaypal, BsFiletypePdf } from "react-icons/bs";
import { LuEdit } from "react-icons/lu";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import jwt from "jwt-decode";
import { useAuthHeader } from "react-auth-kit";
export default function Table(props) {
  const headers = props.headers;
  const rows = props.rows;
  const rowsPerPage = 5; // liczba wierszy na stronę
  const [currentPage, setCurrentPage] = useState(1);
  // Oblicz indeksy początkowy i końcowy dla wierszy na aktualną stronę
  const startIndex = (currentPage - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;
  const navigate = useNavigate();
  const token = useAuthHeader();
  // Funkcja do zmiany aktualnej strony
  const changePage = (page) => {
    setCurrentPage(page);
  };
  // Oblicz liczbę stron na podstawie liczby wierszy i wierszy na stronę
  const [totalPages, setTotalPages] = useState(
    Math.ceil(rows?.length / rowsPerPage)
  );
  const [searchTerm, setSearchTerm] = useState("");
  const [sortField, setSortField] = useState(headers[0].key);
  const [sortOrder, setSortOrder] = useState("asc");
  const [filteredRows, setFilteredRows] = useState(rows);
  // Utwórz tablicę wierszy, które mają być wyświetlone na aktualnej stronie

  useEffect(() => {
    const tab = rows.filter((row) => {
      if (searchTerm === "") {
        return true;
      } else {
        const returnValue = Object.values(row).some(
          (value) =>
            value &&
            value.toString().toLowerCase().includes(searchTerm.toLowerCase())
        );
        const count = rows.filter((row) =>
          Object.values(row).some(
            (value) =>
              value &&
              value.toString().toLowerCase().includes(searchTerm.toLowerCase())
          )
        ).length;
        if (count > 0) {
          setTotalPages(Math.ceil(count / rowsPerPage));
        } else {
          setTotalPages(1);
        }

        return returnValue;
      }
    });

    setFilteredRows(tab);
  }, [searchTerm]);

  useEffect(() => {
    const count = rows.filter((row) =>
      Object.values(row).some(
        (value) =>
          value &&
          value.toString().toLowerCase().includes(searchTerm.toLowerCase())
      )
    ).length;

    if (count > 0) {
      setTotalPages(Math.ceil(count / rowsPerPage));
    } else {
      setTotalPages(1);
    }
  });
  // Przykładowy kod obsługujący zmianę sortField i sortOrder
  const handleSortChange = (field, order) => {
    const sortedRows = [...filteredRows]?.sort((a, b) => {
      const fieldA = a[field];
      const fieldB = b[field];

      if (typeof fieldA === "number" && typeof fieldB === "number") {
        if (fieldA < fieldB) return order === "asc" ? -1 : 1;
        if (fieldA > fieldB) return order === "asc" ? 1 : -1;
        return 0;
      } else {
        const fieldAStr = fieldA?.toString().toLowerCase();
        const fieldBStr = fieldB?.toString().toLowerCase();

        if (fieldAStr < fieldBStr) return order === "asc" ? -1 : 1;
        if (fieldAStr > fieldBStr) return order === "asc" ? 1 : -1;
        return 0;
      }
    });

    setFilteredRows(sortedRows);
  };

  // Wykorzystanie useEffect do inicjalnego sortowania i reagowania na zmiany sortField i sortOrder
  useEffect(() => {
    handleSortChange(sortField, sortOrder);
  }, [sortField, sortOrder]);

  return (
    <Card className=" w-full">
      <div className="flex items-center p-2">
        <input
          type="text"
          placeholder="Wyszukaj..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="p-2 border border-gray-300 rounded mr-4"
        />

        <select
          value={sortField}
          onChange={(e) => {
            setSortField(e.target.value);
          }}
          className="p-2 border border-gray-300 rounded"
        >
          {headers
            .filter((header) => header.key !== null)
            .map((header) => (
              <option key={header.key} value={header.key}>
                {header.name}
              </option>
            ))}
        </select>

        <select
          value={sortOrder}
          onChange={(e) => {
            setSortOrder(e.target.value);
          }}
          className="p-2 border border-gray-300 rounded ml-4"
        >
          <option value="asc">Rosnąco</option>
          <option value="desc">Malejąco</option>
        </select>
      </div>
      <table className="w-full min-w-max table-auto text-left">
        <thead>
          <tr>
            {headers.map((head, index) => (
              <th
                key={index}
                className="border-b border-gray-200 bg-gray-200 p-1"
              >
                <Typography
                  variant="small"
                  className="font-normal leading-none opacity-70 flex flex-col items-center "
                >
                  {head.name}
                </Typography>
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {filteredRows &&
            filteredRows.slice(startIndex, endIndex).map((row, index) => {
              const attributes = Object.keys(row);
              return (
                <tr key={index} className="even:bg-gray-200/50">
                  {attributes.map((attribute, attrIndex) => {
                    const value = row[attribute];
                    if (attribute !== "uuid")
                      return (
                        <td key={attrIndex} className="p-1 ">
                          <Typography
                            variant="small"
                            className="font-normal flex flex-col items-center "
                          >
                            {value}
                          </Typography>
                        </td>
                      );
                  })}
                  {!props.invoices ? (
                    <td className="p-1">
                      <button
                        className="flex flex-col w-full items-center"
                        onClick={() => {
                          navigate(`${row["uuid"]}`);
                        }}
                      >
                        <BiShowAlt className="w-8 h-8 " />
                      </button>
                    </td>
                  ) : (
                    <>
                      <td className="p-1">
                        <button
                          className="flex flex-col w-full items-center"
                          onClick={() => {
                            navigate(`${row["uuid"]}`);
                          }}
                        >
                          <BsPaypal className="w-8 h-8 text-blue-500" />
                        </button>
                      </td>
                      <td className="p-1">
                        <button
                          className="flex flex-col w-full items-center"
                          onClick={async () => {
                            axios.defaults.headers.common["Authorization"] =
                              token();
                            const response = await axios.get(
                              "http://localhost:8080/upc/v1/user-role/get-invoice",
                              {
                                headers: {
                                  "Content-Type": "application/json",
                                },
                                data: {},
                                params: {
                                  clientUuid: props.userUuid,
                                  paymentUuid: row["uuid"],
                                },
                                responseType: "blob",
                              }
                            );
                            // Tworzenie URL obiektu blob
                            const url = window.URL.createObjectURL(
                              new Blob([response.data])
                            );

                            // Tworzenie elementu <a> do pobrania pliku
                            const link = document.createElement("a");
                            link.href = url;
                            link.setAttribute("download", "invoice.pdf"); // Ustawienie nazwy pliku do pobrania

                            // Dodanie elementu <a> do dokumentu i kliknięcie go
                            document.body.appendChild(link);
                            link.click();

                            // Usunięcie elementu <a> z dokumentu
                            document.body.removeChild(link);
                          }}
                        >
                          <BsFiletypePdf className="w-8 h-8 text-red-500" />
                        </button>
                      </td>
                    </>
                  )}
                </tr>
              );
            })}
        </tbody>
      </table>

      {/* Paginacja */}
      <div className="flex space-x-4 my-2 justify-center items-center">
        <button
          className="flex justify-center p-1  bg-gray-700 text-white text-[0.7vw] font-bold rounded hover:bg-gray-800 disabled:bg-gray-500"
          onClick={() => changePage(currentPage - 1)}
          disabled={currentPage === 1}
        >
          <AiOutlineLeft className="" />
        </button>
        <div className="font-bold text-sm">
          {" "}
          {currentPage} / {totalPages}{" "}
        </div>
        <button
          className="flex justify-center p-1   bg-gray-700 text-white text-[0.7vw] font-bold rounded hover:bg-gray-800 disabled:bg-gray-500"
          onClick={() => changePage(currentPage + 1)}
          disabled={currentPage === totalPages}
        >
          <AiOutlineRight />
        </button>
      </div>
    </Card>
  );
}
