import React, { useEffect } from "react";
import { Users, DollarSign } from "lucide-react";
import SearchBar from "../homepage/SearchBar";
import useSearchStore from "@/hooks/useSearchStore";
import fetchRooms from "../../utils/fetchRooms";

const RoomListing: React.FC = () => {
  const { rooms, setRooms } = useSearchStore();

  useEffect(() => {
    fetchRooms().then(setRooms);
  }, []);

  return (
    <div className="p-4">
      <SearchBar />

      <h2 className="text-2xl pt-6 pb-2 font-bold text-center text-black mb-8">
        {rooms.length > 0 ? "Habitaciones disponibles" : " "}
      </h2>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {rooms.map((room) => (
          <div key={room.id} className="bg-white rounded-lg shadow p-6 hover:shadow-lg transition-shadow">
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
    </div>
  );
};

export default RoomListing;
