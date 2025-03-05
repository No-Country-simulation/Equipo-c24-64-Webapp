import { motion } from "framer-motion";

interface HotelCardProps {
  image: string;
  title: string;
  location: string;
  price: string;
  link: string;
}

const HotelCard: React.FC<HotelCardProps> = ({
  image,
  title,
  location,
  price,
}) => {
  return (
    <div className="w-80 bg-white shadow-lg rounded-2xl overflow-hidden">
      <motion.div whileHover={{ scale: 1.08 }} transition={{ duration: 0.3 }}>
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
      </motion.div>

      <div className="p-4">
        <h3 className="text-lg font-bold">{title}</h3>
        <p className="text-gray-600">{location}</p>
        <p className="text-xl font-semibold mt-2">${price} por noche</p>
        <button
          className="block mt-4 bg-blue-600 px-2 text-white text-center w-full py-2 rounded-lg hover:bg-blue-700 transition"
          aria-label="Ir a reservar"
          title="Ir a reservar"
        >
          Reservar ahora
        </button>
      </div>
    </div>
  );
};

export default HotelCard;
