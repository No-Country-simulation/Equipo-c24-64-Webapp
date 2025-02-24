import React from "react";
import { FaFacebook, FaInstagram, FaTwitter } from "react-icons/fa";
import american from "@/assets/american.svg";
import mastercard from "@/assets/mastercard.svg";
import paypal from "@/assets/paypal.svg";
import visa from "@/assets/visa.svg";
const Footer = () => {
  return (
    <footer className="bg-[#222] text-white pt-10 pb-2">
      <div className="container mx-auto px-4">
        <div className="grid md:grid-cols-2 gap-8 items-center">
          <div className="space-y-8 max-w-full">
            <div>
              <h3 className="text-xl font-bold mb-4 sm:text-center text-center">
                Pagos y Seguridad
              </h3>
              <div className="flex space-x-4 sm:justify-center justify-center">
                <img src={visa} alt="logo visa" className="w-10 h-10" />
                <img src={mastercard} alt="logo visa" className="w-10 h-10" />
                <img src={paypal} alt="logo visa" className="w-10 h-10" />
                <img src={american} alt="logo visa" className="w-10 h-10" />
              </div>
            </div>
            <div>
              <h3 className="text-xl font-bold mb-4 text-center">
                Newsletter | Promos especiales
              </h3>
              <div className="flex w-full">
                <input
                  type="email"
                  placeholder="Ingresa tu email"
                  className="w-full px-4 py-2 rounded-l-md text-black bg-gray-100"
                />
                <button className="px-6 py-2 bg-blue-500 text-white rounded-r-md hover:bg-blue-600 transition">
                  Subscribe
                </button>
              </div>
            </div>

            {/* Payment Methods */}
          </div>

          {/* Right Column */}
          <div>
            {/* Hotel Name */}
            <div className="text-center mb-8">
              <h2 className="text-3xl font-bold mb-2 text-blue-500">
                Luxe Haven
              </h2>
            </div>

            {/* Three Column Links */}
            <div className="grid grid-cols-2 gap-1 text-center sm:text-center md:text-center lg:text-center">
              <div>
                <h4 className="font-semibold mb-3 ">Acerca de Luxe Haven</h4>
                <ul className="space-y-2">
                  <li>
                    <a href="#" className="hover:text-blue-300">
                      Empresa
                    </a>
                  </li>
                  <li>
                    <a href="#" className="hover:text-blue-300">
                      Contacto
                    </a>
                  </li>
                  <li>
                    <a href="#" className="hover:text-blue-300">
                      Ubicación
                    </a>
                  </li>
                </ul>
              </div>

              <div>
                <h4 className="font-semibold mb-3">Servicios</h4>
                <ul className="space-y-2">
                  <li>
                    <a href="#faq" className="hover:text-blue-300">
                      Preguntas Frecuentes
                    </a>
                  </li>
                  <li>
                    <a href="#" className="hover:text-blue-300">
                      Como Reservar
                    </a>
                  </li>
                  <li>
                    <a href="#ofertas" className="hover:text-blue-300">
                      Promociones{" "}
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div className="pt-5">
          {/* Social Media Links */}
          <div className="flex justify-center items-center space-x-6 mt-4 pt-4 flex-col gap-2">
            <div className="block">
              <h4 className="font-bold"> Nuestras redes sociales</h4>
            </div>
            <div className="flex gap-2">
              <a
                href="https://www.facebook.com"
                target="_blank"
                rel="noopener noreferrer"
                className="hover:text-blue-300"
              >
                <FaFacebook size={24} />
              </a>
              <a
                href="https://www.instagram.com"
                target="_blank"
                rel="noopener noreferrer"
                className="hover:text-blue-300"
              >
                <FaInstagram size={24} />
              </a>
              <a
                href="https://www.twitter.com"
                target="_blank"
                rel="noopener noreferrer"
                className="hover:text-blue-300"
              >
                <FaTwitter size={24} />
              </a>
            </div>
          </div>
        </div>
        {/* Copyright */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-2 pt-5">
          <div className="pt-1 text-sm text-gray-400 flex gap-4 justify-center md:justify-end">
            <a href="#" className="hover:text-blue-300">
              Términos y Condiciones
            </a>
            <a href="#" className="hover:text-blue-300">
              Privacidad
            </a>
          </div>
          <div className="text-sm text-gray-400 text-center md:text-end">
            <p>&copy; 2025 Hotel Luxe Haven.</p>
            <p className="md:pe-6">by No-Country</p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
