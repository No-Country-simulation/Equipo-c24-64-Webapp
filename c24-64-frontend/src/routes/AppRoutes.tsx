import { Routes, Route } from 'react-router-dom';
import Register from '../pages/Register';
import Login from '../pages/Login';
import HomePage from '../pages/Homepage';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/homepage" element={<HomePage/>} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  )
}

export default AppRoutes