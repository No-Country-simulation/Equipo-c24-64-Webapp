import { motion } from "framer-motion";
import Header from "../layouts/Header";
import Footer from "../layouts/Footer";
import Hero from "../components/Hero";
import SearchBar from "../components/SearchBar";
import Gallery from "../components/Gallery";
import FAQ from "../components/FAQ";
//import Map from "../components/Map";
import Weather from "../components/Weather";
import Contact from "../components/Contact";

const Home: React.FC = () => {
  return (
    <div className="bg-white text-black">
      <Header />
      <Hero />
      <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} transition={{ duration: 1 }}>
        <SearchBar />
      </motion.div>
      <Gallery />
      <Weather />
      <FAQ />
      <Contact/>
      <Footer />
    </div>
  );
};

export default Home;
