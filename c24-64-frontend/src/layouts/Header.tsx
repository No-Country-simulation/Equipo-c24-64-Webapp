import { motion } from "framer-motion";

const Header: React.FC = () => {
  return (
    <motion.header 
      initial={{ y: -50, opacity: 0 }}
      animate={{ y: 0, opacity: 1 }}
      transition={{ duration: 0.5 }}
      className="fixed top-0 left-0 w-full bg-white shadow-md py-4 px-6 flex justify-between items-center z-50"
    >
      <div className="text-2xl font-bold text-indigo-600"><a href="/homepage">Luxe Haven</a></div>
      <nav>
        <ul className="flex space-x-6">
          <li><a href="#hero" className="text-gray-700 hover:text-indigo-600">Inicio</a></li>
          <li><a href="#galerry" className="text-gray-700 hover:text-indigo-600">Galería</a></li>
          <li><a href="#contact" className="text-gray-700 hover:text-indigo-600">Contacto</a></li>
        </ul>
      </nav>
    </motion.header>
  );
};

export default Header;
