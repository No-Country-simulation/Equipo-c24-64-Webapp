import React, { useState, useEffect } from "react";
import { Users, DollarSign } from "lucide-react";
import SearchBar from "../homepage/SearchBar";
import useSearchStore from "@/hooks/useSearchStore";
import fetchRooms from "@/utils/fetchRooms";

const RoomListing: React.FC = () => {
  const { guests, roomType, checkIn, checkOut, rooms } = useSearchStore();

console.log(rooms)
  return (
    <div className="p-4">
      <SearchBar />
      {rooms && rooms.length > 0 && (
        <h2 className="text-2xl pt-6 pb-2 font-bold text-center text-black mb-8">
          Habitaciones recomendadas
        </h2>
      )}

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {rooms.length > 0 ? (
          rooms.map((room) => (
            <div
              key={room.id}
              className="bg-white rounded-lg shadow p-6 hover:shadow-lg transition-shadow"
            >
              <h3 className="text-xl font-semibold mb-2">{room.name}</h3>
              <div className="mb-4">
                <span className="inline-block bg-purple-100 text-purple-800 px-3 py-1 rounded-full text-sm">
                  {room.type}
                </span>
              </div>
              <div className="space-y-2">
                <div className="flex items-center text-gray-600">
                  <DollarSign className="w-4 h-4 mr-2" />
                  <span>${room.price} / noche</span>
                </div>
                <div className="flex items-center text-gray-600">
                  <Users className="w-4 h-4 mr-2" />
                  <span>
                    {room.people} {room.people === 1 ? "persona" : "personas"}
                  </span>
                </div>
              </div>
            </div>
          ))
        ) : (
          <p></p>
        )}
      </div>
    </div>
  );
};

export default RoomListing;
