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
import ClientDetail from "./components/ClientDetail"
import Workers from "./pages/Workers";
import AdminProducts from "./pages/AdminProducts";
import AdminProductDetail from "./components/AdminProductDetail"
import AdminOffers from "./pages/AdminOffers"
import AdminOfferDetail from "./components/AdminOfferDetail";
import Reports from "./pages/Reports";
import AddReport from "./components/AddReport";
import ReportDetail from "./components/ReportDetail";
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
            <Route path="/reports" element={<Reports></Reports>}>
              <Route path=":reportId" element={<ReportDetail></ReportDetail>}></Route>
              <Route path="add" element={<AddReport/>}></Route>
            </Route>
          </Route>
          <Route
            path="/admin"
            element={
              <RequireRole allowedRoles={["ADMIN", "WORKER"]}>
                <AdminPage></AdminPage>
              </RequireRole>
            }
          >
            <Route path="clients" element={<Clients></Clients>}>
              <Route path=":clientId" element={<ClientDetail/>}></Route>
            </Route>
            <Route path="clientAdd"></Route>

            <Route path="employees" element={<Workers/>}>
              <Route path=":employeeId" ></Route> 
            </Route>
            <Route path="employeeAdd"></Route>
            
            
            <Route path="products" element={<AdminProducts/>}>
              <Route path=":productId" element={<AdminProductDetail/>}></Route>
            </Route>
            <Route path="productAdd"></Route>
            
            
            <Route path="offers" element={<AdminOffers/>}>
              <Route path=":offerId" element={<AdminOfferDetail/>}></Route>
            </Route>
            <Route path="offerAdd"></Route>
            
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
