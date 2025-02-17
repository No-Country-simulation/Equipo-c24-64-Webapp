import { Routes, Route } from "react-router-dom";
import Register from "../pages/Register";
import Login from "../pages/Login";
import HomePage from "../pages/Homepage";
import Dashboard from "@/pages/Dashboard.jsx";
const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/homepage" element={<HomePage />} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />
    </Routes>
  );
};

export default AppRoutes;
