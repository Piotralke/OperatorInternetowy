import { BrowserRouter, Route, Routes } from "react-router-dom"
import LoginPage from "./pages/LoginPage"
import HomePage from "./pages/HomePage"
import Layout from "./pages/Layout"
import Offers from "./pages/Offers"
import Invoices from "./pages/Invoices"
import Profile from "./pages/Profile"
import { RequireAuth } from "react-auth-kit"
export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage></LoginPage>}></Route>
        <Route path="/" element={<RequireAuth loginPath={'/login'}><Layout></Layout></RequireAuth> }>
         <Route path="/home" element={<HomePage></HomePage>}></Route>
         <Route path="/offers" element={<Offers></Offers>}></Route>
         <Route path="/invoices" element={<Invoices></Invoices>}></Route>
         <Route path="/profile" element={<Profile></Profile>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
    
  )
}