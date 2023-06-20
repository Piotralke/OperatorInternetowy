import UserData from "../pages/Client/Profile/UserData";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import axios from "axios"
import React from "react";
jest.mock('axios');

describe('ClientUserData', () => {
  test('renders user data form and changes user data', async () => {
    const mockResponse = { status: 200 };
    axios.put.mockResolvedValueOnce(mockResponse);

    const userData = {
      uuid: "user-uuid",
      phoneNumber: "123456789",
      email: "example@example.com",
      address: "Example Address",
      balance: 0,
      firstName: "John",
      lastName: "Doe",
      nip: "1234567890",
      isBusinessClient: true,
    };

    render(
      <AuthProvider>
        <MemoryRouter>
          <UserData userData={userData} />
        </MemoryRouter>
      </AuthProvider>
    );

    expect(screen.getByDisplayValue("123456789")).toBeInTheDocument();
    expect(screen.getByDisplayValue("example@example.com")).toBeInTheDocument();
    expect(screen.getByDisplayValue("Example Address")).toBeInTheDocument();

    const phoneNumberInput = screen.getByLabelText("Telefon komórkowy");
    fireEvent.change(phoneNumberInput, { target: { value: "987654321" } });
    

  });

  test('changes password in inputs', async () => {
    const mockResponse = { status: 200 };
    axios.put.mockResolvedValueOnce(mockResponse);

    const userData = {
      uuid: "user-uuid",
    };

    render(
      <AuthProvider>
        <MemoryRouter>
          <UserData userData={userData} />
        </MemoryRouter>
      </AuthProvider>
    );
    
    const newPasswordInput = screen.getByLabelText("Nowe hasło");
    const confirmNewPasswordInput = screen.getByLabelText("Powtórz hasło");
    fireEvent.change(newPasswordInput, { target: { value: "newPassword123" } });
    fireEvent.change(confirmNewPasswordInput, { target: { value: "newPassword123" } })

    expect(newPasswordInput).not.toBeNull();
    expect(confirmNewPasswordInput).not.toBeNull();
    });
  });

