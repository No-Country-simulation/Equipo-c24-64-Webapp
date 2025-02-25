import React, { useMemo } from "react";
import { Users, DollarSign } from "lucide-react";
import SearchBar from "../homepage/SearchBar";
import useSearchStore from "@/hooks/useSearchStore";

interface Room {
  id: number;
  name: string;
  type: string;
  price: number;
  people: number;
}
const rooms = [
  {
    id: 1,
    name: "Habitación Single Económica",
    type: "single",
    price: 50,
    people: 1,
  },
  {
    id: 2,
    name: "Habitación Doble Estándar",
    type: "double",
    price: 100,
    people: 2,
  },
  {
    id: 3,
    name: "Suite Ejecutiva",
    type: "suite",
    price: 300,
    people: 3,
  },
  {
    id: 4,
    name: "Habitación Triple",
    type: "double",
    price: 200,
    people: 3,
  },
  {
    id: 5,
    name: "Suite Presidencial",
    type: "suite",
    price: 500,
    people: 4,
  },
  {
    id: 6,
    name: "Habitación Single Premium",
    type: "single",
    price: 70,
    people: 1,
  },
  {
    id: 7,
    name: "Habitación Doble Deluxe",
    type: "double",
    price: 180,
    people: 2,
  },
  { id: 8, name: "Suite Familiar", type: "suite", price: 350, people: 4 },
  {
    id: 9,
    name: "Habitación Cuádruple",
    type: "cuadruple",
    price: 220,
    people: 4,
  },
  { id: 10, name: "Suite Real", type: "suite", price: 600, people: 5 },
  {
    id: 11,
    name: "Habitación Single Compacta",
    type: "single",
    price: 45,
    people: 1,
  },
  {
    id: 12,
    name: "Habitación Doble Business",
    type: "double",
    price: 160,
    people: 2,
  },
  { id: 13, name: "Suite Junior", type: "suite", price: 280, people: 3 },
  {
    id: 14,
    name: "Habitación Triple Superior",
    type: "triple",
    price: 250,
    people: 3,
  },
  { id: 15, name: "Suite Imperial", type: "suite", price: 700, people: 5 },
  {
    id: 16,
    name: "Habitación Single Deluxe",
    type: "single",
    price: 85,
    people: 1,
  },
  {
    id: 17,
    name: "Habitación Doble Confort",
    type: "double",
    price: 140,
    people: 2,
  },
  { id: 18, name: "Suite Lujo", type: "suite", price: 450, people: 4 },
  {
    id: 19,
    name: "Habitación Cuádruple Confort",
    type: "cuadruple",
    price: 260,
    people: 4,
  },
  { id: 20, name: "Suite Royal", type: "suite", price: 750, people: 5 },
  {
    id: 21,
    name: "Habitación Single Vista al Mar",
    type: "single",
    price: 120,
    people: 1,
  },
  {
    id: 22,
    name: "Habitación Doble con Balcón",
    type: "double",
    price: 190,
    people: 2,
  },
  { id: 23, name: "Suite Panorámica", type: "suite", price: 500, people: 4 },
  {
    id: 24,
    name: "Habitación Cuádruple Familiar",
    type: "cuadruple",
    price: 280,
    people: 4,
  },
  { id: 25, name: "Suite Platino", type: "suite", price: 800, people: 6 },
  {
    id: 26,
    name: "Habitación Single Premium Plus",
    type: "single",
    price: 130,
    people: 1,
  },
  {
    id: 27,
    name: "Habitación Doble Elite",
    type: "double",
    price: 210,
    people: 2,
  },
  { id: 28, name: "Suite Business", type: "suite", price: 400, people: 3 },
  {
    id: 29,
    name: "Habitación Triple Deluxe",
    type: "triple",
    price: 270,
    people: 3,
  },
  { id: 30, name: "Suite Diamante", type: "suite", price: 900, people: 6 },
];
const RoomListing: React.FC = () => {
  const { guests, roomType, checkIn, checkOut } = useSearchStore();
  console.log(guests);

  const getFilteredRooms = useMemo(() => {
    return rooms.filter((room) => {
      if (
        roomType !== "all" &&
        room.type.toLowerCase() !== roomType.toLowerCase()
      ) {
        return false;
      }
      if (
        guests &&
        room.people < (guests.adults || 0) + (guests.children || 0)
      ) {
        return false;
      }
      return true;
    });
  }, [guests, roomType]);

  return (
    <div className="p-4">
      <SearchBar />
      {roomType && (
        <h2 className="text-2xl pt-6 pb-2 font-bold text-center text-black mb-8">
          Habitaciónes recomendadas
        </h2>
      )}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {getFilteredRooms.map((room) => (
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
        ))}
      </div>
    </div>
  );
};

export default RoomListing;
