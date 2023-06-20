import AccountData from "../pages/Client/Home/AccountData";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import axios from "axios"
import React from "react";
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());
jest.mock('axios');
describe('ClientAccountData', () => {
  test('renders client number, firstname and lastname', async() => {
    render(
    <AuthProvider>
        <MemoryRouter>
            <AccountData/>
        </MemoryRouter>
    </AuthProvider>
    );

    await waitFor(() => {
        expect(screen.getByTestId('client-number')).toBeInTheDocument();
        expect(screen.getByTestId('client-firstnameandlastname')).toBeInTheDocument();
        expect(screen.getByTestId('client-address')).toBeInTheDocument();
    });

  });

  test('renders "PROFIL" link', async() => {
    render(<AuthProvider>
        <MemoryRouter>
          <AccountData/>
        </MemoryRouter>
      </AuthProvider>);
    await waitFor(() => {
        expect(screen.getByText('PROFIL')).toBeInTheDocument();
    }
  );
});
});