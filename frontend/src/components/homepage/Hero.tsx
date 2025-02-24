import { motion } from "framer-motion";

const Hero: React.FC = () => {
  return (
    <section
      className="relative w-full h-screen flex items-center justify-center bg-cover bg-center hero-bg"
      id="hero"
      style={{ backgroundImage: "url('/images/hero-bg.webp')" }}
    >
      <div className="absolute inset-0 bg-opacity-50"></div>
      <motion.div
        initial={{ opacity: 0, y: 50 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        className="relative text-center text-white px-6"
      >
        <h1 className="text-6xl font-bold mb-4 inter ">
          Bienvenido a Luxe Haven
        </h1>
        <p className="text-2xl mb-6">
          Encuentra las mejores habitaciones al mejor precio
        </p>
        <motion.a
          whileHover={{ scale: 1.1 }}
          transition={{ duration: 0.3 }}
          href="#searchbar"
          className="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-3 px-6 rounded-lg"
        >
          Reservar Ahora
        </motion.a>
      </motion.div>
    </section>
  );
};

export default Hero;
