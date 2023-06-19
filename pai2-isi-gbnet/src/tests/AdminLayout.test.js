
import { render, screen, fireEvent } from "@testing-library/react";
import React from "react";
import { MemoryRouter } from "react-router-dom";
import AdminLayout from "../pages/Admin/AdminLayout";
import { AuthProvider } from 'react-auth-kit';
import '@testing-library/jest-dom/extend-expect';
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
