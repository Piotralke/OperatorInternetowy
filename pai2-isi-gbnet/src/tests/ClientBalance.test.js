import Balance from "../pages/Client/Home/Balance";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import axios from "axios"
import React from "react";
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());
jest.mock('axios');
const mail = { sub: 'wojnolo2137@wp.pl' };
describe('ClientBalance', () => {
  test('renders loading spinner when loading is true', () => {
    render(<AuthProvider>
        <MemoryRouter>
        <Balance data={mail}/>
        </MemoryRouter>
      </AuthProvider>);
    const spinnerElement = screen.getByTestId('loading-spinner');
    expect(spinnerElement).toBeInTheDocument();
  });

  test('renders balance amount when loading is false', async() => {
    render(
    <AuthProvider>
        <MemoryRouter>
        <Balance data={mail}/>
        </MemoryRouter>
    </AuthProvider>
    );

    await waitFor(() => {
        expect(screen.getByTestId('balance-amount')).toBeInTheDocument();
    });

  });

  test('renders "Wszystkie faktury" link when loading is false', async() => {
    render(<AuthProvider>
        <MemoryRouter>
          <Balance data={mail}/>
        </MemoryRouter>
      </AuthProvider>);
    await waitFor(() => {
        expect(screen.getByText('Wszystkie faktury')).toBeInTheDocument();
    }
  );
});



});