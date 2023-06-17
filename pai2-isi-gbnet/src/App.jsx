import { BrowserRouter, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/Client/Home/HomePage";
import ClientLayout from "./pages/Client/ClientLayout";
import Offers from "./pages/Client/Offers/Offers";
import Invoices from "./pages/Client/Invoices/Invoices";
import Profile from "./pages/Client/Profile/Profile";
import { AuthProvider, RequireAuth } from "react-auth-kit";
import OfferDetail from "./pages/Client/Offers/OfferDetail";
import Products from "./pages/Client/Products/Products";
import AdminLayout from "./pages/Admin/AdminLayout";
import RequireRole from "./RequireRole";
import Clients from "./pages/Admin/Clients/Clients";
import ClientDetail from "./pages/Admin/Clients/ClientDetail"
import Workers from "./pages/Admin/Workers/Workers";
import AdminProducts from "./pages/Admin/Products/AdminProducts";
import AdminProductDetail from "./pages/Admin/Products/AdminProductDetail"
import AdminOffers from "./pages/Admin/Offers/AdminOffers"
import AdminOfferDetail from "./pages/Admin/Offers/AdminOfferDetail";
import Reports from "./pages/Client/Reports/Reports";
import ReportDetail from "./pages/Client/Reports/ReportDetail";
import AdminWorkerDetail from "./pages/Admin/Workers/AdminWorkerDetail";
import WorkerAdd from "./pages/Admin/Workers/WorkerAdd";
import ClientAdd from "./pages/Admin/Clients/ClientAdd";
import AdminProductAdd from "./pages/Admin/Products/AdminProductAdd";
import AdminOfferAdd from "./pages/Admin/Offers/AdminOfferAdd";
import AdminReports from "./pages/Admin/Reports/AdminReports";
import AdminReportDetail from "./pages/Admin/Reports/AdminReportDetail";
import MakeOrder from "./pages/Client/Orders/MakeOrder";
import OrderSummary from "./pages/Client/Orders/OrderSummary";
import OrderSuccess from "./pages/Client/Orders/OrderSuccess";
import OrderCancel from "./pages/Client/Orders/OrderCancel";
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
                <ClientLayout></ClientLayout>
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
            </Route>
             <Route path="order/:offerId" element={<MakeOrder/>}></Route> 
             <Route path="order/:offerId/success" element={<OrderSuccess/>} ></Route> 
             <Route path="order/:offerId/cancel"element={<OrderCancel/>} ></Route> 
             <Route path="orderSummary" element={<OrderSummary/>}></Route>
             
          </Route>
          <Route
            path="/admin"
            element={
              <RequireRole allowedRoles={["ADMIN", "WORKER"]}>
                <AdminLayout></AdminLayout>
              </RequireRole>
            }
          >
            <Route path="clients" element={<Clients></Clients>}>
              <Route path=":clientId" element={<ClientDetail/>}></Route>
            </Route>
            <Route path="clientAdd" element={<ClientAdd/>}></Route>

            <Route path="employees" element={<Workers/>}>
              <Route path=":employeeId" element={<AdminWorkerDetail/>} ></Route> 
            </Route>
            <Route path="employeeAdd" element={<WorkerAdd/>}></Route>
            
            
            <Route path="products" element={<AdminProducts/>}>
              <Route path=":productId" element={<AdminProductDetail/>}></Route>
            </Route>
            <Route path="productAdd" element={<AdminProductAdd/>}></Route>
            
            
            <Route path="offers" element={<AdminOffers/>}>
              <Route path=":offerId" element={<AdminOfferDetail/>}></Route>
            </Route>
            <Route path="offerAdd" element={<AdminOfferAdd/>}></Route>
            
            <Route path="reports" element={<AdminReports/>}>
              <Route path=":reportId" element={<AdminReportDetail/>}></Route>
            </Route>
            <Route path="notification"></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
