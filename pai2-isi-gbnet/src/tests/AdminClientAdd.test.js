import ClientAdd from "../pages/Admin/Clients/ClientAdd"
import React from 'react';
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import axios from "axios"
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());
jest.mock('axios');

describe('ClientAdd', () => {
  test('renders form inputs', () => {
    render(<AuthProvider>
        <MemoryRouter>
          <ClientAdd />
        </MemoryRouter>
      </AuthProvider>);

    expect(screen.getByLabelText('Imię')).toBeInTheDocument();
    expect(screen.getByLabelText('Nazwisko')).toBeInTheDocument();
    expect(screen.getByLabelText('Adres e-mail')).toBeInTheDocument();
    expect(screen.getByLabelText('Numer Telefonu')).toBeInTheDocument();
    expect(screen.getByLabelText('Hasło')).toBeInTheDocument();
    expect(screen.getByLabelText('Powtórz hasło')).toBeInTheDocument();
    expect(screen.getByLabelText('PESEL')).toBeInTheDocument();
    expect(screen.getByLabelText('NIP')).toBeInTheDocument();
    expect(screen.getByLabelText('Adres')).toBeInTheDocument();
    expect(screen.getByText('Klient biznesowy')).toBeInTheDocument();
    expect(screen.getByText('Dodaj')).toBeInTheDocument();
  });

  test('submits form with valid data', async () => {
    const mockResponse = { status: 200 };
    axios.post.mockResolvedValueOnce(mockResponse);

    render(<AuthProvider>
        <MemoryRouter>
          <ClientAdd />
        </MemoryRouter>
      </AuthProvider>);

    fireEvent.change(screen.getByLabelText('Imię'), { target: { value: 'John' } });
    fireEvent.change(screen.getByLabelText('Nazwisko'), { target: { value: 'Doe' } });
    fireEvent.change(screen.getByLabelText('Adres e-mail'), { target: { value: 'john.doe@example.com' } });
    fireEvent.change(screen.getByLabelText('Numer Telefonu'), { target: { value: '123456789' } });
    fireEvent.change(screen.getByLabelText('Hasło'), { target: { value: 'Password123' } });
    fireEvent.change(screen.getByLabelText('Powtórz hasło'), { target: { value: 'Password123' } });
    fireEvent.change(screen.getByLabelText('PESEL'), { target: { value: '12345678901' } });
    fireEvent.change(screen.getByLabelText('Adres'), { target: { value: '123 Main St' } });

    fireEvent.click(screen.getByText('Dodaj'));

    await waitFor(() => {
      expect(axios.post).toHaveBeenCalledWith('http://localhost:8080/upc/unsecured/v1/client-register', {
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.doe@example.com',
        password: 'Password123',
        address: '123 Main St',
        phoneNumber: '123456789',
        nip: null,
        pesel: '12345678901',
        isBusinessClient: false,
      });
      expect(screen.getByLabelText('Imię')).toBeEmpty();
    expect(screen.getByLabelText('Nazwisko')).toBeEmpty();
    expect(screen.getByLabelText('Adres e-mail')).toBeEmpty();
    expect(screen.getByLabelText('Numer Telefonu')).toBeEmpty();
    expect(screen.getByLabelText('Hasło')).toBeEmpty();
    expect(screen.getByLabelText('Powtórz hasło')).toBeEmpty();
    expect(screen.getByLabelText('PESEL')).toBeEmpty();
    expect(screen.getByLabelText('NIP')).toBeEmpty();
    expect(screen.getByLabelText('Adres')).toBeEmpty();
    
    });
  });
});