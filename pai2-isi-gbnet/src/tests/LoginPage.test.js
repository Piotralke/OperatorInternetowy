import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import LoginPage from '../pages/LoginPage';
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect';
describe('LoginPage', () => {
  test('renders login form', () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <LoginPage />
        </MemoryRouter>
      </AuthProvider>
    );
    
    const loginInput = screen.getByPlaceholderText('login');
    const passwordInput = screen.getByPlaceholderText('haslo');
    const registerButton = screen.getByText('Rejestracja');
    const loginButton = screen.getByText('Zaloguj');
    
    expect(loginInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();
    expect(registerButton).toBeInTheDocument();
    expect(loginButton).toBeInTheDocument();
  });

   test('switches to registration form when "Rejestracja" button is clicked', () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <LoginPage />
        </MemoryRouter>
      </AuthProvider>
    );
    
    const registerButton = screen.getByText('Rejestracja');
    fireEvent.click(registerButton);
    
    const firstNameInput = screen.getByLabelText('Imię');
    const lastNameInput = screen.getByLabelText('Nazwisko');
    const emailInput = screen.getByLabelText('Adres e-mail');
    const phoneNumberInput = screen.getByLabelText('Numer Telefonu');
    const passwordInput = screen.getByLabelText('Hasło');
    
    expect(firstNameInput).toBeInTheDocument();
    expect(lastNameInput).toBeInTheDocument();
    expect(emailInput).toBeInTheDocument();
    expect(phoneNumberInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();
  });

  test('switches to login form when "Logowanie" button is clicked', () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <LoginPage />
        </MemoryRouter>
      </AuthProvider>
    );
    
    const registerButton = screen.getByText('Rejestracja');
    fireEvent.click(registerButton);
    
    const loginButton = screen.getByText('Logowanie');
    fireEvent.click(loginButton);
    
    const loginInput = screen.getByPlaceholderText('login');
    const passwordInput = screen.getByPlaceholderText('haslo');
    
    expect(loginInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();
  });

  test('performs login when "Zaloguj" button is clicked', async () => {
    render(
      <AuthProvider>
        <MemoryRouter>
          <LoginPage />
        </MemoryRouter>
      </AuthProvider>
    );
    
    const loginInput = screen.getByPlaceholderText('login');
    const passwordInput = screen.getByPlaceholderText('haslo');
    const loginButton = screen.getByText('Zaloguj');
    
    fireEvent.change(loginInput, { target: { value: 'wojnolo2137@wp.pl' } });
    fireEvent.change(passwordInput, { target: { value: 'cipka123' } });
    fireEvent.click(loginButton);
    const stronaGlownaText = screen.getByText('Zalogowano');
    expect(stronaGlownaText).toBeInTheDocument();
  });
});
