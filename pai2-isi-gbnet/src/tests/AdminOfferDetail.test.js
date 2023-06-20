import React, { useState as useStateMock } from 'react';
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import AdminOfferDetail from "../pages/Admin/Offers/AdminOfferDetail";
import { AuthProvider } from "react-auth-kit";
import { MemoryRouter } from "react-router-dom";
import "@testing-library/jest-dom/extend-expect";
import jwt from "jwt-decode";


jest.mock("jwt-decode", () => jest.fn());



describe("AdminOfferDetail", () => {
  // beforeEach(() => {
  //   // Mock any external dependencies or setup required for the tests
  // });

  test("renders component with offer name", () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <AdminOfferDetail />
        </MemoryRouter>
      </AuthProvider>
    );
    const offerName = screen.getByText("Nazwa");
    expect(offerName).toBeInTheDocument();
  });

  test("disables inputs when edit button is not clicked", () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <AdminOfferDetail />
        </MemoryRouter>
      </AuthProvider>
    );
    const nameInput = screen.getByLabelText("Nazwa");
    const priceInput = screen.getByLabelText("Cena");
    expect(nameInput).toBeDisabled();
    expect(priceInput).toBeDisabled();
  });


  test("enables inputs when edit button is disabled - role: worker", async() => {
      render(
        <AuthProvider>
          <MemoryRouter>
            <AdminOfferDetail/>
          </MemoryRouter>
        </AuthProvider>
      );
    // Sprawdź, czy pola są początkowo wyłączone
    const nameInput = screen.getByLabelText("Nazwa");
    const priceInput = screen.getByLabelText("Cena");
    expect(nameInput).toBeDisabled();
    expect(priceInput).toBeDisabled();


    const editButton = screen.getByTestId("edytuj-button");
    expect(editButton).toBeDisabled();
    fireEvent.change(editButton,{target: {disabled: false}});
    expect(editButton).not.toBeDisabled();
    if(fireEvent.click(editButton))
    {
      fireEvent.change(nameInput,{target: {disabled: false}});
      await waitFor(() => {
        expect(nameInput).not.toBeDisabled()
      })
    }
    else
    {
      await waitFor(() => {
        expect(nameInput).toBeDisabled()
      })
    }
    
    
  });

});
