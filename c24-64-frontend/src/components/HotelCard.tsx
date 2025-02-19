import React from "react";

interface HotelCardProps {
  image: string;
  title: string;
  location: string;
  price: string;
  link: string;
}

const HotelCard: React.FC<HotelCardProps> = ({ image, title, location, price, link }) => {
  return (
    <div className="w-80 bg-white shadow-lg rounded-2xl overflow-hidden">
      <img 
        src={image} 
        alt={title} 
        className="w-full h-48 object-cover" 
        onError={(e) => {
          const target = e.target as HTMLImageElement;
          target.src = "/placeholder-image.jpg"; // Imagen de respaldo si la URL original falla
        }}
      />
      <div className="p-4">
        <h3 className="text-lg font-bold">{title}</h3>
        <p className="text-gray-600">{location}</p>
        <p className="text-xl font-semibold mt-2">${price} por noche</p>
        <a 
          href={link} 
          className="block mt-4 bg-blue-600 text-white text-center py-2 rounded-lg hover:bg-blue-700 transition"
        >
          Reservar ahora
        </a>
      </div>
    </div>
  );
};

export default HotelCard;