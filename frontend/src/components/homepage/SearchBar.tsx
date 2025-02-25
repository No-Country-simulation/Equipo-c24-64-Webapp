import React, { useState } from "react";
import { Calendar, Users, ChevronDown, Search } from "lucide-react";
import useSearchStore from "@/hooks/useSearchStore.tsx";

const SearchBar = () => {
  const {
    isGuestsOpen,
    checkIn,
    checkOut,
    roomType,
    guests,
    setIsGuestsOpen,
    setCheckIn,
    setCheckOut,
    setRoomType,
    setGuests,
    handleGuestsChange,
  } = useSearchStore();

  return (
    <div className="max-w-6xl mx-auto px-4 mt-8" id="searchbar">
      {/* Heading Section */}
      <div className="text-center mb-6">
        <h2 className="text-2xl font-bold mb-2">
          Ahorrá hasta un 45% en tu próxima estadía de hotel
        </h2>
      </div>

      {/* Search Bar */}
      <div className="bg-white rounded-lg shadow-lg p-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-2">
          {/* Room Type */}
          <div className="relative">
            <select
              value={roomType}
              onChange={(e) => setRoomType(e.target.value)}
              className="w-full p-3 border border-gray-300 rounded-lg focus:border-blue-500 focus:ring-1 focus:ring-blue-500 appearance-none cursor-pointer"
            >
              <option value="">Tipo de habitación</option>
              <option value="standard">Habitación Estándar</option>
              <option value="deluxe">Habitación Deluxe</option>
              <option value="suite">Suite</option>
            </select>
            <ChevronDown
              className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400"
              size={20}
            />
          </div>

          {/* Check-in Date */}
          <div className="relative">
            <input
              type="date"
              value={checkIn}
              onChange={(e) => setCheckIn(e.target.value)}
              className="w-full p-3 border border-gray-300 rounded-lg focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
              placeholder="Fecha de llegada"
            />
            <Calendar
              className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 pointer-events-none"
              size={20}
            />
          </div>

          {/* Check-out Date */}
          <div className="relative">
            <input
              type="date"
              value={checkOut}
              onChange={(e) => setCheckOut(e.target.value)}
              className="w-full p-3 border border-gray-300 rounded-lg focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
              placeholder="Fecha de salida"
            />
            <Calendar
              className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 pointer-events-none"
              size={20}
            />
          </div>

          {/* Guests Selector */}
          <div className="relative">
            <button
              onClick={() => setIsGuestsOpen(!isGuestsOpen)}
              className="w-full p-3 border border-gray-300 rounded-lg text-left focus:border-blue-500 focus:ring-1 focus:ring-blue-500 bg-white"
            >
              <div className="flex items-center justify-between">
                <div className="flex items-center gap-2">
                  <Users size={20} className="text-gray-400" />
                  <span>{`${guests.adults} adultos · ${guests.children} niños · ${guests.rooms} hab.`}</span>
                </div>
                <ChevronDown size={20} className="text-gray-400" />
              </div>
            </button>

            {/* Guests Dropdown */}
            {isGuestsOpen && (
              <div className="absolute top-full left-0 mt-2 w-72 bg-white rounded-lg shadow-lg border border-gray-200 p-4 z-50">
                <div className="space-y-4">
                  {/* Adults */}
                  <div className="flex items-center justify-between">
                    <span>Adultos</span>
                    <div className="flex items-center gap-3">
                      <button
                        onClick={() => handleGuestsChange("adults", "subtract")}
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                        disabled={guests.adults <= 1}
                      >
                        -
                      </button>
                      <span>{guests.adults}</span>
                      <button
                        onClick={() => handleGuestsChange("adults", "add")}
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                      >
                        +
                      </button>
                    </div>
                  </div>

                  {/* Children */}
                  <div className="flex items-center justify-between">
                    <span>Niños</span>
                    <div className="flex items-center gap-3">
                      <button
                        onClick={() =>
                          handleGuestsChange("children", "subtract")
                        }
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                        disabled={guests.children <= 0}
                      >
                        -
                      </button>
                      <span>{guests.children}</span>
                      <button
                        onClick={() => handleGuestsChange("children", "add")}
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                      >
                        +
                      </button>
                    </div>
                  </div>

                  {/* Rooms */}
                  <div className="flex items-center justify-between">
                    <span>Habitaciones</span>
                    <div className="flex items-center gap-3">
                      <button
                        onClick={() => handleGuestsChange("rooms", "subtract")}
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                        disabled={guests.rooms <= 1}
                      >
                        -
                      </button>
                      <span>{guests.rooms}</span>
                      <button
                        onClick={() => handleGuestsChange("rooms", "add")}
                        className="w-8 h-8 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
                      >
                        +
                      </button>
                    </div>
                  </div>
                </div>

                <button
                  onClick={() => setIsGuestsOpen(false)}
                  className="mt-4 w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition-colors"
                >
                  Listo
                </button>
              </div>
            )}
          </div>
        </div>
        {/* Search Button */}
        <button className="w-full mt-4 bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition-colors flex items-center justify-center gap-2">
          <Search size={20} />
          <span>Buscar</span>
        </button>
      </div>
    </div>
  );
};

export default SearchBar;
