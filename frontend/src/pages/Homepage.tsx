import { motion } from "framer-motion";
import Header from "../layouts/Header";
import Footer from "../layouts/Footer";
import Hero from "../components/homepage/Hero";
// import SearchBar from "../components/homepage/SearchBar";
import ServiceCard from "../components/homepage/ServiceCard";
import RoomListing from "@/components/rooms/RoomsListing";
import FAQ from "../components/homepage/FAQ";
import Weather from "../components/Weather";
import HotelCard from "../components/homepage/HotelCard";
import hotels from "../data/hotel";
import HotelBanner from"../components/homepage/HotelBanner";
const Home: React.FC = () => {
  // Verifica si los componentes existen antes de renderizarlos
  const hasHotels = Array.isArray(hotels) && hotels.length > 0;

  return (
    <div className="bg-white text-black min-h-screen ">
      <Header />
      <Hero />
      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ duration: 1 }}
      >
      </motion.div>
      {/* <SearchBar/> */}
      <RoomListing />
       <ServiceCard/> 
       <HotelBanner/>
      <Weather />
      <FAQ />
      {/* Sección de Ofertas de Hoteles */}
      <section className="py-12" id="ofertas">
        <h2 className="text-2xl font-bold text-center mb-6">
          Ofertas de hoteles imperdibles
        </h2>
        <div className="flex flex-wrap gap-6 justify-center">
          {hasHotels ? (
            hotels.map((hotel) => (
              <a href={hotel.link} key={hotel.id}>
                <HotelCard
                  image={hotel.image}
                  title={hotel.title}
                  location={hotel.location}
                  price={hotel.price}
                />
              </a>
            ))
          ) : (
            <p>No hay ofertas disponibles en este momento.</p>
          )}
        </div>
      </section>
      <Footer />
    </div>
  );
};

export default Home;
