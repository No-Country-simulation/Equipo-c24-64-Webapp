import { motion } from "framer-motion";

const Gallery: React.FC = () => {
  const images = [
    "/images/hotel-1.jpg",
    "/images/hotel-2.jpg",
    "/images/hotel-3.jpg",
    "/images/hotel-4.jpg",
    "/images/hotel-5.jpg",
    "/images/hotel-6.jpg",
  ];

  return (
    <section className="max-w-6xl mx-auto p-6" id="galerry">
      <h2 className="text-3xl font-bold text-center mb-6 inter">Galería</h2>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
        {images.map((src, index) => (
          <motion.div 
            key={index} 
            whileHover={{ scale: 1.05 }} 
            transition={{ duration: 0.3 }}
            className="overflow-hidden rounded-lg shadow-lg"
          >
            <img src={src} alt={`Hotel ${index + 1}`} className="w-full h-64 object-cover" />
          </motion.div>
        ))}
      </div>
    </section>
  );
};

export default Gallery;
