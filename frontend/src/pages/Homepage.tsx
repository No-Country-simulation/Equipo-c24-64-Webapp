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
      <section className="pt-12 pb-4 px-4 sm:px-8 bg-gray-50">
        <RoomListing />
      </section>
      <section className="pb-12 px-4 sm:px-8 bg-gray-100">
        <ServiceCard />
      </section>
      <section className="pb-12">
        <HotelBanner />
      </section>
      <section className="pb-12 px-4 sm:px-8 flex justify-center">
        <div className="p-4 sm:p-12 max-w-7xl bg-gradient-to-br from-blue-50 to-blue-100 rounded-lg ">
          <SpaSection />
        </div>
      </section>
      <section className="pb-12 lg:max-w-full">
        <Weather />
      </section>
      <FAQ />
      {/* Sección de Ofertas de Hoteles */}
      <section className="py-12" id="ofertas">
        <HotelCard />
        <div>
          {/*<audio controls>
            <source src="ruta-de-tu-musica.mp3" type="audio/mp3" />
            Tu navegador no soporta la etiqueta de audio.
          </audio>*/}
        </div>
      </section>
      <Footer />
    </div>
  );
};

export default Home;
