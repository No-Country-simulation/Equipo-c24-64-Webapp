// import { motion } from "framer-motion";
import Header from "../layouts/Header";
import Footer from "../layouts/Footer";
import Hero from "../components/homepage/Hero";
import ServiceCard from "../components/homepage/ServiceCard";
import RoomListing from "@/components/rooms/RoomsListing";
import FAQ from "../components/homepage/FAQ";
import Weather from "../components/Weather";
import HotelCard from "../components/homepage/HotelCard";
import hotels from "../data/hotel";
import HotelBanner from "../components/homepage/HotelBanner";
import { motion } from "framer-motion";
import useScrollAnimation from "@/hooks/useInView";
import SpaSection from "@/components/spaSection/SpaSection";

const Home: React.FC = () => {
  const { ref, inView } = useScrollAnimation();
  const hasHotels = Array.isArray(hotels) && hotels.length > 0;
  return (
    <div className="bg-white text-black min-h-screen ">
      <Header />
      <Hero />
      {/* <SearchBar/> */}
      <section className="pt-12 pb-4 px-4 sm:px-8">
        <RoomListing />
      </section>
      <section className="pb-12 px-4 sm:px-8">
        <ServiceCard />
      </section>
      <section className="pb-12 px-4 sm:px-8">
        <div className="p-12 bg-gradient-to-br from-indigo-50 to-purple-100 rounded-lg">
          <SpaSection />
        </div>
      </section>
      <section className="py-12">
        <HotelBanner />
      </section>
      <section className="pb-12 px-4 sm:px-8">
        <Weather />
      </section>
      <FAQ />
      {/* Sección de Ofertas de Hoteles */}
      <section className="py-12" id="ofertas">
        <h2 className="text-2xl font-bold text-center mb-6">
          Ofertas de hoteles imperdibles
        </h2>
        <div className="flex flex-wrap gap-6 justify-center">
          {hasHotels ? (
            hotels.map((hotel) => (
              <motion.a
                ref={ref}
                initial={{ opacity: 0, y: 100 }}
                animate={inView ? { opacity: 1, y: 0 } : {}}
                transition={{ duration: 1 }}
                href={hotel.link}
                key={hotel.id}
              >
                <HotelCard
                  image={hotel.image}
                  title={hotel.title}
                  location={hotel.location}
                  price={hotel.price}
                />
              </motion.a>
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
