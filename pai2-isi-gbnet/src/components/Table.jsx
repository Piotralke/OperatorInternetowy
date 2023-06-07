import React, { useState } from "react";
import { Card, Typography } from "@material-tailwind/react";
import { BiShowAlt } from "react-icons/bi"
import { LuEdit } from "react-icons/lu"

export default function Table(props) {
  const headers = props.headers;
  const rows = props.rows;
  const rowsPerPage = 2; // liczba wierszy na stronę

  const [currentPage, setCurrentPage] = useState(1);

  // Oblicz indeksy początkowy i końcowy dla wierszy na aktualną stronę
  const startIndex = (currentPage - 1) * rowsPerPage;
  const endIndex = startIndex + rowsPerPage;

  // Utwórz tablicę wierszy, które mają być wyświetlone na aktualnej stronie
  const visibleRows = rows.slice(startIndex, endIndex);

  // Funkcja do zmiany aktualnej strony
  const changePage = (page) => {
    setCurrentPage(page);
  };

  // Oblicz liczbę stron na podstawie liczby wierszy i wierszy na stronę
  const totalPages = Math.ceil(rows.length / rowsPerPage);

  return (
    <Card className="h-full w-full">
      <table className="w-full min-w-max table-auto text-left">
        <thead>
          <tr>
            {headers.map((head) => (
              <th
                key={head}
                className="border-b border-gray-200 bg-gray-200 p-4"
              >
                <Typography
                  variant="small"
                  color="gray"
                  className="font-normal leading-none opacity-70 flex flex-col items-center"
                >
                  {head}
                </Typography>
              </th>
            ))}
            
          </tr>
        </thead>
        <tbody>
          {visibleRows.map((row, index) => {
            const attributes = Object.keys(row);

            return (
              <tr key={index} className="even:bg-gray-200/50">
                {attributes.map((attribute, attrIndex) => {
                  const value = row[attribute];
                  console.log(value);
                  if(attribute!=="uuid")
                  return (
                    <td key={attrIndex} className="p-4 ">
                      <Typography
                        variant="small"
                        color="gray-300"
                        className="font-normal flex flex-col items-center"
                      >
                        {value}
                      </Typography>
                    </td>
                  );
                })}
                <td className="p-4">
                  <Typography
                    as="a"
                    href={`edit/${row["uuid"]}`}
                    variant="small"
                    color="blue-300"
                    className="font-medium flex flex-col items-center"
                  >
                    <BiShowAlt className="w-8 h-8 "/>
                  </Typography>
                </td>
                <td className="p-4">
                  <Typography
                    as="a"
                    href={`edit/${row["uuid"]}`}
                    variant="small"
                    color="blue-300"
                    className="font-medium flex flex-col items-center"
                  >
                    <LuEdit className="w-8 h-8 "/>
                  </Typography>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>

      {/* Paginacja */}
      <div className="flex space-x-8 mt-auto justify-center items-center">
        <button
          className="p-4 w-[10%] bg-gray-700 text-white text-xl font-bold rounded hover:bg-gray-800 disabled:bg-gray-500"
          onClick={() => changePage(currentPage - 1)}
          disabled={currentPage === 1}
        >
          Previous
        </button>
        <div className="font-bold text-lg"> {currentPage} / {totalPages} </div>
        <button
          className="p-4  w-[10%] bg-gray-700 text-white text-xl font-bold rounded hover:bg-gray-800 disabled:bg-gray-500"
          onClick={() => changePage(currentPage + 1)}
          disabled={currentPage === totalPages}
        >
          Next
        </button>
      </div>
    </Card>
  );
}
