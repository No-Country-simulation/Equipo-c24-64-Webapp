import { useState } from "react";
import { motion } from "framer-motion";

const SearchBar: React.FC = () => {
  const [checkIn, setCheckIn] = useState("");
  const [checkOut, setCheckOut] = useState("");
  const [guests, setGuests] = useState(1);
  const [roomType, setRoomType] = useState("standard");

  const handleSearch = () => {
    console.log({ checkIn, checkOut, guests, roomType });
  };

  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }} 
      animate={{ opacity: 1, y: 0 }} 
      transition={{ duration: 0.5 }}
      className="bg-white shadow-md rounded-lg p-6 max-w-4xl mx-auto mt-8" id="searchbar"
    >
      <div className="grid grid-cols-1 md:grid-cols-4 gap-4" >
        <input 
          type="date" 
          value={checkIn} 
          onChange={(e) => setCheckIn(e.target.value)} 
          className="border rounded p-2 w-full"
          placeholder="Check-in"
        />
        <input 
          type="date" 
          value={checkOut} 
          onChange={(e) => setCheckOut(e.target.value)} 
          className="border rounded p-2 w-full"
          placeholder="Check-out"
        />
        <input 
          type="number" 
          value={guests} 
          onChange={(e) => setGuests(Number(e.target.value))} 
          className="border rounded p-2 w-full" 
          min="1"
        />
        <select 
          value={roomType} 
          onChange={(e) => setRoomType(e.target.value)} 
          className="border rounded p-2 w-full"
        >
          <option value="standard">Estándar</option>
          <option value="deluxe">Deluxe</option>
          <option value="suite">Suite</option>
        </select>
      </div>
      <button 
        onClick={handleSearch} 
        className="mt-4 w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 rounded-lg"
      >
        Buscar Disponibilidad
      </button>
    </motion.div>
  );
};

export default SearchBar;
