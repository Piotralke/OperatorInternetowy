import React from 'react';
import { render, screen, fireEvent } from "@testing-library/react";
import AdminLayout from "../pages/Admin/AdminLayout";
import { AuthProvider } from 'react-auth-kit';
import { MemoryRouter } from 'react-router-dom';
import '@testing-library/jest-dom/extend-expect';
import jwt from "jwt-decode";
jest.mock("jwt-decode", () => jest.fn());
describe("AdminLayout", () => {
  it("renders without error", () => {
    render(
        <AuthProvider>
        <MemoryRouter>
        <AdminLayout />
        </MemoryRouter>
      </AuthProvider>
    );
    
    expect(screen.getByText("Gb net")).toBeInTheDocument();
  });

  
  it("renders correctly on small screens", () => {
    // Mock window.innerWidth to simulate a small screen
    window.innerWidth = 600;
    render(
     <AuthProvider>
        <MemoryRouter>
        <AdminLayout />
        </MemoryRouter>
      </AuthProvider>
    );
    expect(screen.getByText("Gb net")).toBeInTheDocument();
    expect(screen.queryByTestId("tree-node")).not.toBeInTheDocument();
  });
});
