import React, { useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Pagination, Autoplay } from 'swiper/modules';
import { hotelSlides } from '../../data/HotelBanner';
import { FaWifi, FaSwimmingPool, FaUtensils } from 'react-icons/fa';
import { MdElectricalServices, MdFitnessCenter, MdSpa, MdMoreHoriz } from 'react-icons/md';
import { BsHouseDoor } from 'react-icons/bs';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

const HotelBanner: React.FC = () => {
  const [activeIndex, setActiveIndex] = useState(0);

  const facilities = [
    { icon: <FaWifi className="text-indigo-400 text-xl" />, name: "Libre Wifi" },
    { icon: <FaSwimmingPool className="text-indigo-400 text-xl" />, name: "Pileta" },
    { icon: <BsHouseDoor className="text-indigo-400 text-xl" />, name: "Zona de trabajo" },
    { icon: <FaUtensils className="text-indigo-400 text-xl" />, name: "Comedor" },
    { icon: <MdElectricalServices className="text-purple-400 text-xl" />, name: "Energía libre" },
    { icon: <MdFitnessCenter className="text-purple-400 text-xl" />, name: "Gym" },
    { icon: <MdSpa className="text-purple-400 text-xl" />, name: "Spa" },
    { icon: <MdMoreHoriz className="text-purple-400 text-xl" />, name: "Otros servicios" },
  ];

  return (
    <div className="max-w-6xl mx-auto flex flex-col lg:flex-row w-full bg-gray-900 text-white py-12 px-4 md:px-8 lg:px-12">
      {/* Left side content */}
      <div className="w-full lg:w-1/2 lg:pr-8 mb-8 lg:mb-0">
        <h1 className="text-4xl md:text-5xl font-bold mb-4">
          Nuestras mejores comodidades
        </h1>
        <p className="text-gray-400 mb-8">
          Reserva hoy, disfrutá mañana
        </p>
        
        <button className="bg-gray-600 hover:bg-gray-700 transition-colors duration-300 text-white font-medium py-3 px-6 rounded-md mb-10">
          <a href="/contacto">Contáctenos</a>
        </button>
        
        <div className="grid grid-cols-2 gap-x-4 gap-y-6">
          {facilities.map((facility, index) => (
            <div key={index} className="flex items-center">
              {facility.icon}
              <span className="ml-2 text-gray-300">{facility.name}</span>
            </div>
          ))}
        </div>
      </div>
      
      {/* Right side carousel */}
      <div className="w-full lg:w-1/2 relative">
        <Swiper
          modules={[Pagination, Autoplay]}
          spaceBetween={0}
          slidesPerView={1}
          pagination={{ clickable: true }}
          autoplay={{ delay: 5000 }}
          onSlideChange={(swiper) => setActiveIndex(swiper.activeIndex)}
          className="rounded-lg overflow-hidden h-96 md:h-[32rem]"
        >
          {hotelSlides.map((slide, index) => (
            <SwiperSlide key={index}>
              <div className="relative w-full h-full">
                <img 
                  src={slide.image} 
                  alt={slide.title} 
                  className="w-full h-full object-cover"
                />
                <div className="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 to-transparent p-6">
                  <div className="flex justify-between items-center">
                    <div>
                      <p className="text-sm text-gray-300">{slide.subtitle}</p>
                      <h3 className="text-2xl font-bold">{slide.title}</h3>
                    </div>
                    <div>
                      <button className="flex items-center text-white hover:text-gray-300 transition-colors duration-300">
                        <span className="mr-2"><a href="/rooms">Reservar ahora</a></span>
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                          <path fillRule="evenodd" d="M10.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L12.586 11H5a1 1 0 110-2h7.586l-2.293-2.293a1 1 0 010-1.414z" clipRule="evenodd" />
                        </svg>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </SwiperSlide>
          ))}
        </Swiper>
      </div>
    </div>
  );
};

export default HotelBanner;
