import { FaFacebook, FaInstagram, FaTwitter } from 'react-icons/fa';

import React from 'react';

const Footer = () => {
  return (
    <footer className="bg-indigo-900 text-white py-8">
      <div className="container mx-auto text-center">
        <div className="mb-4">
          <h3 className="text-2xl font-bold inter">Hotel Luxe Haven</h3>
          <p className="text-lg">Tu lugar de descanso ideal.</p>
        </div>
        <div className="flex justify-center space-x-6 mb-4">
        <FaFacebook size={20}/> <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer" className="text-xl hover:text-indigo-300">
            <i className="fab fa-facebook"></i> Facebook
          </a>
          <FaInstagram size={20}/> <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer" className="text-xl hover:text-indigo-300">
            <i className="fab fa-instagram"></i> Instagram
          </a>
          <FaTwitter size={20}/> <a href="https://www.twitter.com" target="_blank" rel="noopener noreferrer" className="text-xl hover:text-indigo-300">
            <i className="fab fa-twitter"></i> Twitter
          </a>
        </div>
        <div className="text-sm text-gray-400">
          <p>&copy; 2025 Hotel Luxe Haven. Todos los derechos reservados.</p>
          <p>Desarrollado por No-Country</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
