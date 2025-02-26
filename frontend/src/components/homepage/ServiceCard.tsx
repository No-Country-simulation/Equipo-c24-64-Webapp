import React from "react";
import { ServiceCard, defaultServices } from "../../data/Services";

interface ServiceCardsProps {
  services?: ServiceCard[];
}

const ServiceCards: React.FC<ServiceCardsProps> = ({ services = defaultServices }) => {
  return (
    <div className="max-w-6xl mx-auto p-4">
      <div className="grid grid-cols-2 md:grid-cols-3 gap-4 auto-rows-auto">
        {services.map((service, index) => (
          <div
            key={index}
            className={`relative group overflow-hidden rounded-lg cursor-pointer transition-transform duration-300 hover:scale-105
              ${index === 0 ? "row-span-2 col-span-1" : "col-span-1"}
            `}
          >
            {/* Imagen de fondo */}
            <div
              className={`w-full bg-cover bg-center ${index === 0 ? "h-full" : "aspect-[4/3]"}`}
              style={{ backgroundImage: `url(${service.image})` }}
            />
            
            {/* Capa de superposición oscura con degradado */}
            <div className="absolute inset-0 bg-black/50 bg-gradient-to-t from-black/10 to-transparent transition-opacity group-hover:bg-black/40" />
            
            {/* Título del servicio */}
            <div className="absolute inset-0 flex items-center justify-center">
              <h3 className="text-white text-2xl font-semibold text-center px-4">
                {service.title}
              </h3>
            </div>

            {/* Enlace clickeable */}
            <a 
              href={service.link}
              className="absolute inset-0"
              aria-label={`View ${service.title}`}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default ServiceCards;
