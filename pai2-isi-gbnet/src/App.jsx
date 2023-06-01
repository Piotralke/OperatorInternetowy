import { BrowserRouter, Route, Routes } from "react-router-dom"
import LoginPage from "./pages/loginPage"
import HomePage from "./pages/HomePage"
import Layout from "./pages/Layout"
import Offers from "./pages/Offers"
export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<LoginPage></LoginPage>}></Route>
        <Route path="/" element={<Layout></Layout>}>
         <Route path="/home" element={<HomePage></HomePage>}></Route>
         <Route path="/offers" element={<Offers></Offers>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
    
  )
}