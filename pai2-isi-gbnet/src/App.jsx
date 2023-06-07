import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";
import Layout from "./pages/Layout";
import Offers from "./pages/Offers";
import Invoices from "./pages/Invoices";
import Profile from "./pages/Profile";
import { AuthProvider, RequireAuth } from "react-auth-kit";
import OfferDetail from "./components/OfferDetail";
import Products from "./pages/Products";
import AdminPage from "./pages/AdminPage";
import RequireRole from "./RequireRole";
import Clients from "./pages/Clients";

export default function App() {
  return (
    <AuthProvider
      authType="cookie"
      authName="_auth"
      cookieDomain={window.location.hostname}
      cookieSecure={false}
    >
      
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<LoginPage></LoginPage>}></Route>
          <Route
            path="/"
            element={
              <RequireRole allowedRoles={["USER"]}>
                <Layout></Layout>
              </RequireRole>
            }
          >
            <Route path="/home" element={<HomePage></HomePage>}></Route>
            <Route path="/offers" element={<Offers></Offers>}></Route>
            <Route
              path="/offers/:offerId"
              element={<OfferDetail></OfferDetail>}
            ></Route>
            <Route path="/products/" element={<Products></Products>}></Route>
            {/* <Route path="/products/:productId" element={<ProductDetail></ProductDetail>}></Route> */}
            <Route path="/invoices" element={<Invoices></Invoices>}></Route>
            <Route path="/profile" element={<Profile></Profile>}></Route>
          </Route>
          <Route
            path="/admin"
            element={
              <RequireRole allowedRoles={["ADMIN", "WORKER"]}>
                <AdminPage></AdminPage>
              </RequireRole>
            }
          >
            <Route path="clients" element={<Clients></Clients>}></Route>
            <Route path="clientAdd"></Route>
            <Route path="clients/:clientId"></Route>
            <Route path="clients/edit/:clientId"></Route>
            <Route path="employees "></Route>
            <Route path="employeeAdd "></Route>
            <Route path="employees/:employeeId"></Route>
            <Route path="employees/edit/:employeeId"></Route>
            <Route path="products "></Route>
            <Route path="productAdd"></Route>
            <Route path="products/:productId"></Route>
            <Route path="products/edit/:productId"></Route>
            <Route path="services "></Route>
            <Route path="serviceAdd"></Route>
            <Route path="services/:serviceId"></Route>
            <Route path="services/edit/:serviceId"></Route>
            <Route path="reports">
              <Route path=":reportId"></Route>
            </Route>
            <Route path="notification"></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
