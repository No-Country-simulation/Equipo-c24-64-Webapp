import { Navigate, Outlet } from "react-router-dom";

const ProtectedRoute: React.FC = () => {
  const userRole: string | null = sessionStorage.getItem("role") || "";

  return userRole === "admin" ? <Outlet /> : <Navigate to="/" replace />;
};

export default ProtectedRoute;
