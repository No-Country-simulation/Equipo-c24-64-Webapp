import React, { useEffect } from "react";
import { Users, DollarSign } from "lucide-react";
import SearchBar from "../homepage/SearchBar";
import useSearchStore from "@/hooks/useSearchStore";
import fetchRooms from "../../utils/fetchRooms";
import { motion } from "framer-motion";
import useScrollAnimation from "@/hooks/useInView.ts";

const RoomListing: React.FC = () => {
  const { rooms, setRooms, roomType } = useSearchStore();
  const { ref, inView } = useScrollAnimation();
  useEffect(() => {
    // Cargar habitaciones al montar el componente
    console.log("toy pidiendo a la db");

    fetchRooms().then(setRooms);
  }, []);

  // Función para obtener el nombre legible del tipo de habitación
  const getRoomTypeName = (type) => {
    const typeMap = {
      single: "Habitación Single",
      double: "Habitación Doble",
      triple: "Habitación Triple",
      cuadruple: "Habitación Cuádruple",
      quintuple: "Habitación Quíntuple",
      suite: "Suite",
    };
    return typeMap[type] || "";
  };

  return (
    <div className="p-4">
      <motion.div
        ref={ref}
        initial={{ opacity: 0, y: 50 }}
        animate={inView ? { opacity: 1, y: 0 } : {}}
        transition={{ duration: 1 }}
      >
        <SearchBar />
      </motion.div>

      {rooms.length > 0 ? (
        <>
          <h2 className="text-2xl pt-6 pb-2 font-bold text-center text-black mb-8">
            Habitaciones disponibles
            {roomType && ` - ${getRoomTypeName(roomType)}`}
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {rooms.map((room) => (
              <div
                key={room.id}
                className="bg-white rounded-lg shadow p-6 hover:shadow-lg transition-shadow"
              >
                <h3 className="text-xl font-semibold mb-2">{room.name}</h3>
                <p className="text-gray-600">{room.description}</p>
                <div className="mt-4 space-y-2">
                  <div className="flex items-center text-gray-600">
                    <DollarSign className="w-4 h-4 mr-2" />
                    <span>${room.typeRoom.price} / noche</span>
                  </div>
                  <div className="flex items-center text-gray-600">
                    <Users className="w-4 h-4 mr-2" />
                    <span>Capacidad: {room.capacity} personas</span>
                  </div>
                  <div className="flex items-center text-gray-600">
                    <span className="inline-block bg-indigo-100 text-indigo-800 px-3 py-1 rounded-full text-sm">
                      {room.typeRoom.name}
                    </span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </>
      ) : (
        <div className="text-center py-12">
          <h2 className="text-2xl font-bold text-gray-700 mb-4">
            No hay habitaciones disponibles
          </h2>
          <p className="text-gray-500 max-w-md mx-auto">
            {roomType
              ? `No se encontraron habitaciones de tipo ${getRoomTypeName(
                  roomType
                )}.`
              : "No se encontraron habitaciones disponibles en este momento."}
            <br />
            Por favor, intenta con diferentes criterios de búsqueda.
          </p>
        </div>
      )}
    </div>
  );
};

export default RoomListing;
