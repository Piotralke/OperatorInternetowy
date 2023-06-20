import React from 'react';
import { render, screen, fireEvent } from "@testing-library/react";
import ClientLayout from "../pages/Client/ClientLayout";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());;
describe('ClientLayout', () => {
test("renders client layout with correct navigation", () => {
  render(<AuthProvider>
    <MemoryRouter>
      <ClientLayout />
    </MemoryRouter>
  </AuthProvider>);
  const homeButton = screen.getAllByText("STRONA GŁÓWNA");
  const offersButton = screen.getAllByText("OFERTY");
  const productsButton = screen.getAllByText("PRODUKTY");
  const invoicesButton = screen.getAllByText("FAKTURY");
  const profileButton = screen.getAllByText("PROFIL");
  const reportsButton = screen.getAllByText("ZGŁOSZENIA");
  expect(homeButton).toHaveLength(2)
  expect(offersButton).toHaveLength(2)
  expect(productsButton).toHaveLength(2)
  expect(invoicesButton).toHaveLength(2)
  expect(profileButton).toHaveLength(2)
  expect(reportsButton).toHaveLength(2)
});

});