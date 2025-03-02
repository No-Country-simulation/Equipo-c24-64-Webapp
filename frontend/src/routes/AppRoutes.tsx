import { Routes, Route } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";
import Register from "../pages/Register";
import Login from "../pages/Login";
import HomePage from "../pages/Homepage";
import Dashboard from "../pages/Dashboard.jsx";
import Rooms from "../pages/Rooms.jsx";
import Contacto from "../pages/Contacto.jsx";
import Descuento from "@/pages/Descuento.jsx";
import Spa from "@/pages/Spa";
import Confirmation from "@/pages/Confirmation";

const AppRoutes = () => {
  const location = useLocation();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, [location.pathname]);
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/rooms" element={<Rooms />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/contacto" element={<Contacto />} />
      <Route path="/spa" element={<Spa />} />
      <Route path="/confirmation" element={<Confirmation />} />
      <Route
        path="/descuento-masajes-30/:codigoDescuento"
        element={<Descuento />}
      />
    </Routes>
  );
};

export default AppRoutes;
