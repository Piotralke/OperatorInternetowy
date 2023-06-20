import OfferDetail from "../pages/Client/Offers/OfferDetail";
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from "react-auth-kit";
import { MemoryRouter } from "react-router-dom";
import "@testing-library/jest-dom/extend-expect";
import axios from "axios";
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());

jest.mock("axios");

afterEach(() => {
  axios.get.mockClear();
});
describe("ClientOfferDetail", () => {
  test("renders loading spinner before data is fetched", async () => {
    const response = {
      data: {
        uuid: "uuid",
        name: "Oferta",
        description: "Opis",
        price: 20,
        withDevice: false,
        productDto: null,
        offerType: "INTERNET",
      },
    };
    axios.get.mockResolvedValueOnce(response);
    const { getByText } = render(
      <AuthProvider>
        <MemoryRouter>
          <OfferDetail />
        </MemoryRouter>
      </AuthProvider>
    );
    expect(screen.getByText("Powrót")).toBeInTheDocument();
    expect(screen.getByText("Szczegóły oferty")).toBeInTheDocument();

    await waitFor(() => {
      expect(screen.getByText("OPŁATA")).toBeInTheDocument();
      expect(screen.getByText("MIESIĘCZNIE")).toBeInTheDocument();
      expect(screen.getByText("ZAMÓW")).toBeInTheDocument();
    });
  });
});
