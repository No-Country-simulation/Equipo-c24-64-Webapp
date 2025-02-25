import React, { useEffect, useState } from "react";
import { Menu, X, Heart } from "lucide-react";
import { FaRegUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";

const Header = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [usuario, setUsuario] = useState(true);
  const usuarioLogeado = JSON.parse(localStorage.getItem("usuarioLogeado"));
  useEffect(() => {
    setUsuario(usuarioLogeado);
  }, [setUsuario]);

  // const { username } = usuario;
  return (
    <header
      className="fixed top-0 left-0 right-0 bg-white border-b border-gray-200 z-50"
      id="#top"
    >
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex justify-between items-center h-16">
          <div className="flex-shrink-0">
            <a href="/" className="flex items-center">
              <span className="text-2xl font-bold text-blue-600">
                Luxe Haven
              </span>
            </a>
          </div>
          <div className="hidden md:flex items-center space-x-6">
            <a
              href="/favorites"
              className="flex items-center space-x-2 text-gray-700 hover:text-blue-600 transition-colors"
            >
              <Heart size={20} />
              <span>Favoritos</span>
            </a>
            {/* Login */}
            {usuario ? (
              <>
                <FaRegUserCircle size={24} />
                <a href="/usuario" className="font-semibold ">
                  {usuario.username}
                </a>
              </>
            ) : (
              <a
                href="/login"
                className="text-gray-700 hover:text-blue-600 transition-colors"
              >
                Iniciá sesión
              </a>
            )}
            <div className="relative">
              <button
                onClick={() => setIsOpen(!isOpen)}
                className="flex items-center space-x-2 text-gray-700 hover:text-blue-600 transition-colors"
              >
                <Menu size={20} />
                <span>Menú</span>
              </button>
              {isOpen && (
                <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg py-2 border border-gray-200">
                  <Link
                    to="/"
                    className="block px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                  >
                    Inicio
                  </Link>
                  <a
                    href="#galeria"
                    className="block px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                  >
                    Galería
                  </a>
                  <Link
                    to="/contacto"
                    className="block px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                  >
                    Contacto
                  </Link>
                  <div className="border-t border-gray-200 my-2"></div>
                  <a
                    href="/register"
                    className="block px-4 py-2 text-blue-600 hover:bg-gray-100 transition-colors"
                  >
                    Registrate
                  </a>
                </div>
              )}
            </div>
          </div>

          {/* Mobile Menu Button */}
          <button
            onClick={() => setIsOpen(!isOpen)}
            className="md:hidden p-2 rounded-lg hover:bg-gray-100 transition-colors"
          >
            {isOpen ? <X size={24} /> : <Menu size={24} />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      {isOpen && (
        <div className="md:hidden border-t border-gray-200">
          <div className="px-4 py-3 space-y-3">
            <a
              href="/favorites"
              className="flex items-center space-x-2 text-gray-700 hover:text-blue-600 transition-colors"
            >
              <Heart size={20} />
              <span>Favoritos</span>
            </a>
            <a
              href="#inicio"
              className="block text-gray-700 hover:text-blue-600 transition-colors"
            >
              Inicio
            </a>
            <a
              href="#galeria"
              className="block text-gray-700 hover:text-blue-600 transition-colors"
            >
              Galería
            </a>
            <a
              href="#contacto"
              className="block text-gray-700 hover:text-blue-600 transition-colors"
            >
              Contacto
            </a>
            <div className="border-t border-gray-200 pt-3">
              <a
                href="/login"
                className="block text-gray-700 hover:text-blue-600 transition-colors"
              >
                Iniciá sesión
              </a>
              <a
                href="/register"
                className="block text-blue-600 hover:text-blue-700 transition-colors mt-3"
              >
                Registrate
              </a>
            </div>
          </div>
        </div>
      )}
    </header>
  );
};

export default Header;
