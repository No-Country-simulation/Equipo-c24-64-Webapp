import React, { useState, useEffect, useRef } from 'react';
import { motion, useInView } from "framer-motion";
import { Clock } from 'lucide-react';
import { Link } from "react-router-dom";
import hotels from '../../data/hotel'; // Import from your external file

// Countdown Timer Component
const CountdownTimer = () => {
  const [timeLeft, setTimeLeft] = useState(3600); // 1 hour in seconds

  useEffect(() => {
    // Check if we have a stored end time
    const storedEndTime = localStorage.getItem('dealsEndTime');
    let endTime;
    
    if (storedEndTime) {
      endTime = parseInt(storedEndTime, 10);
      // Calculate remaining time
      const currentTime = Math.floor(Date.now() / 1000);
      const remaining = endTime - currentTime;
      
      // If timer expired, reset it
      if (remaining <= 0) {
        endTime = Math.floor(Date.now() / 1000) + 3600;
        localStorage.setItem('dealsEndTime', endTime.toString());
        setTimeLeft(3600);
      } else {
        setTimeLeft(remaining);
      }
    } else {
      // Set end time for 1 hour from now
      endTime = Math.floor(Date.now() / 1000) + 3600;
      localStorage.setItem('dealsEndTime', endTime.toString());
    }
    
    const interval = setInterval(() => {
      setTimeLeft(prev => {
        if (prev <= 1) {
          clearInterval(interval);
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
    
    return () => clearInterval(interval);
  }, []);
  
  const hours = Math.floor(timeLeft / 3600);
  const minutes = Math.floor((timeLeft % 3600) / 60);
  const seconds = timeLeft % 60;
  
  return (
    <div className="flex items-center justify-center gap-2 text-red-600 font-bold">
      <Clock className="w-5 h-5" />
      <span>
        {hours.toString().padStart(2, '0')}:{minutes.toString().padStart(2, '0')}:{seconds.toString().padStart(2, '0')}
      </span>
    </div>
  );
};

// Enhanced HotelCard component
interface HotelCardProps {
  image: string;
  title: string;
  location: string;
  price: string;
  deal?: string; // Optional deal price
}

const HotelCard: React.FC<HotelCardProps> = ({
  image,
  title,
  location,
  price,
  deal
}) => {
  return (
    <div className="w-80 bg-white shadow-lg rounded-2xl overflow-hidden">
      <motion.div whileHover={{ scale: 1.08 }} transition={{ duration: 0.3 }}>
        <div className="relative">
        
          <img
            src={image}
            alt={title}
            title={title}
            loading="lazy"
            className="w-full h-48 object-cover"
            onError={(e) => {
              const target = e.target as HTMLImageElement;
              target.src = "/placeholder-image.jpg"; // Imagen de respaldo si la URL original falla
            }}
          />
        </div>
      </motion.div>

      <div className="p-4">
        <h3 className="text-lg font-bold">{title}</h3>
        <p className="text-gray-600">Piso {location}</p>
        
        {deal ? (
          <div className="mt-2">
            <p className="text-gray-500 line-through">${price} por noche</p>
            <p className="text-black-600 text-xl font-bold">${deal} por noche</p>
          </div>
        ) : (
          <p className="text-xl font-semibold mt-2">${price} por noche</p>
        )}
        
        <Link
                      to="#"
                      title="Obtener oferta"
                      className="px-4 flex py-2 bg-gradient-to-r bg-blue-600 text-white font-medium transition-all rounded-lg"
                    >
          {deal ? "¡Aprovechar oferta!" : "Reservar ahora"}
        </Link>
      </div>
    </div>
  );
};

// Function to add deals to the hotel data
const addDealsToHotels = (hotelData) => {
  return hotelData.map(hotel => {
    // Calculate a deal price (approx. 30% off)
    const originalPrice = parseFloat(hotel.price.replace(/\./g, '').replace(',', '.'));
    const dealPrice = Math.floor(originalPrice * 0.7).toLocaleString('es-CL');
    
    return {
      ...hotel,
      location: Math.floor(Math.random() * 3 + 1).toString(), // Random floor 1-3
      originalTitle: hotel.title,
      deal: dealPrice,
      link: '#' // Update link to go to the /deal page
    };
  });
};

// Main section component
const HotelOffers = () => {
  // Use a ref for the whole section instead of individual items
  const sectionRef = useRef(null);
  const isInView = useInView(sectionRef, { once: true });
  
  // Transform the imported hotels data to add deals
  const hotelsWithDeals = addDealsToHotels(hotels);
  const hasHotels = hotelsWithDeals.length > 0;

  return (
    <section className="py-12" id="ofertas" ref={sectionRef}>
      <div className="max-w-6xl mx-auto px-4">
        <h2 className="text-2xl font-bold text-center mb-2">
          Ofertas de habitaciones imperdibles
        </h2>
        <div className="flex justify-center mb-6">
          <div className="bg-red-100 py-2 px-4 rounded-full flex items-center gap-3">
            <p className="font-semibold">¡Ofertas expiran en:</p>
            <CountdownTimer />
          </div>
        </div>
        
        <div className="flex flex-wrap gap-6 justify-center">
          {hasHotels ? (
            hotelsWithDeals.map((hotel, index ) => (
              <motion.a
                href={hotel.link}
                key={hotel.id}
                initial={{ opacity: 0, y: 50 }}
                animate={isInView ? { opacity: 1, y: 0 } : { opacity: 0, y: 50 }}
                transition={{ duration: 0.5, delay: index * 0.1 }}
              >
                <HotelCard
                  image={hotel.image}
                  title={hotel.title}
                  location={hotel.location}
                  price={hotel.price}
                  deal={hotel.deal}
                />
              </motion.a>
            ))
          ) : (
            <p>No hay ofertas disponibles en este momento.</p>
          )}
        </div>
      </div>
    </section>
  );
};

export default HotelOffers;