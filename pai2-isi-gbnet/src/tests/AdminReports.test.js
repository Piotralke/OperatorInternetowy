import AdminReports from "../pages/Admin/Reports/AdminReports"
import React from 'react';
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect'
import axios from "axios"
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());
/*
jest.mock('react-router-dom', () => {
    const originalModule = jest.requireActual('react-router-dom');
    return {
      ...originalModule,
      useNavigate: jest.fn(),
    };
  });
  */

  jest.mock('axios');
describe('AdminReports', () => {
  test('renders loading spinner before data is fetched', () => {
    render(<AuthProvider>
      <MemoryRouter>
        <AdminReports />
      </MemoryRouter>
    </AuthProvider>);
    expect(screen.getByTestId('loading-spinner')).toBeInTheDocument();
  });

  test('renders table with fetched data', async () => {
    const mockData = [
      {
        uuid: '8c571a05-8f15-431f-a90f-eaff18843c91',
        userProblemId: '8c571a05-8f15-431f-a90f-eaff18843c91',
        userProblemStartDate: '2023-06-18 10:50:53.829958',
        userProblemStatus: 'IN_PROGRESS',
      },
    ];

    axios.get.mockResolvedValue({ data: { content: mockData } });

    render(<AuthProvider>
      <MemoryRouter>
        <AdminReports />
      </MemoryRouter>
    </AuthProvider>);

    await waitFor(() => {
      
      expect(screen.getAllByText('Nr zgłoszenia')).toHaveLength(2);
      expect(screen.getAllByText('Data wysłania')).toHaveLength(2);
      expect(screen.getAllByText('Status zgłoszenia')).toHaveLength(2);
      expect(screen.getByText('Szczegóły')).toBeInTheDocument();

      expect(screen.getByText('8c571a05-8f15-431f-a90f-eaff18843c91')).toBeInTheDocument();
      expect(screen.getByText('IN_PROGRESS')).toBeInTheDocument();

      
    });
  });
  });

