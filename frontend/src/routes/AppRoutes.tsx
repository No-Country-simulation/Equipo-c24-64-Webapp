import { Routes, Route } from "react-router-dom";
import Register from "../pages/Register";
import Login from "../pages/Login";
import HomePage from "../pages/Homepage";
import Dashboard from "../pages/Dashboard.jsx";
import Rooms from "../pages/Rooms.jsx";
import Contacto from "../pages/Contacto.jsx";
import { useLocation } from "react-router-dom";
import { useEffect } from "react";

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
    </Routes>
  );
};

export default AppRoutes;
