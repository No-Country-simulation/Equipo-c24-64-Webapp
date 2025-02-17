import React, { useState } from "react";
import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup"; // Importamos yup
import { yupResolver } from "@hookform/resolvers/yup"; // Importamos el resolver

import { ROOM_TYPES, ROOM_STATUS } from "@/types.js";

// Creamos el esquema de validación con Yup
const schema = Yup.object().shape({
  nombre: Yup.string().required("El nombre es obligatorio"),
  apellido: Yup.string().required("El apellido es obligatorio"),
  email: Yup.string()
    .email("Ingrese un email válido")
    .required("El email es requerido"),
  telefono: Yup.string()
    .required("El teléfono es obligatorio")
    .matches(/^[0-9]+$/, "Solo se permiten números"),
  habitacion: Yup.string().required("Debes seleccionar una habitación"),
});

const ReceptionistForm = () => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
    setValue,
  } = useForm({
    resolver: yupResolver(schema),
  });
  const navigate = useNavigate();
  const [guests, setGuests] = useState([]);

  const [rooms, setRooms] = useState([
    {
      id: "101",
      numero: "101",
      tipo: ROOM_TYPES.INDIVIDUAL,
      estado: ROOM_STATUS.DISPONIBLE,
    },
    {
      id: "102",
      numero: "102",
      tipo: ROOM_TYPES.DOBLE,
      estado: ROOM_STATUS.DISPONIBLE,
    },
    {
      id: "103",
      numero: "103",
      tipo: ROOM_TYPES.SUITE,
      estado: ROOM_STATUS.DISPONIBLE,
    },
  ]);

  const [selectedRoom, setSelectedRoom] = useState("");

  const registerGuest = (formData) => {
    console.log(formData);
    const newGuest = {
      id: Date.now().toString(),
      ...formData,
      habitacionId: formData.habitacion,
    };
    console.log("nuevo huesped", newGuest);

    setGuests([...guests, newGuest]);

    setRooms(
      rooms.map((room) =>
        room.id === formData.habitacion
          ? { ...room, estado: ROOM_STATUS.OCUPADA }
          : room
      )
    );
    Swal.fire({
      title: "Huesped agregado con exito!",
      icon: "success",
    });
    reset();
  };

  const handleCheckout = async (roomId) => {
    const eliminarHuesped = await Swal.fire({
      icon: "question",
      title: "Esta huesped será eliminado.",
      text: "¿Deseas eliminar este huesped?",
      showCancelButton: true,
      confirmButtonText: "Sí, eliminar",
      cancelButtonText: "Cancelar",
    });

    if (eliminarHuesped.isConfirmed) {
      setGuests(guests.filter((guest) => guest.habitacionId !== roomId));
      Swal.fire({
        title: "Huesped eliminado con exito!",
        icon: "success",
      });
    }

    reset();

    setRooms(
      rooms.map((room) =>
        room.id === roomId ? { ...room, estado: ROOM_STATUS.DISPONIBLE } : room
      )
    );
  };

  const toggleRoomStatus = (roomId) => {
    setRooms(
      rooms.map((room) => {
        if (room.id === roomId) {
          const newStatus =
            room.estado === ROOM_STATUS.MANTENIMIENTO
              ? ROOM_STATUS.DISPONIBLE
              : ROOM_STATUS.MANTENIMIENTO;
          return { ...room, estado: newStatus };
        }
        return room;
      })
    );
  };

  const getRoomStatusColor = (estado) => {
    switch (estado) {
      case ROOM_STATUS.OCUPADA:
        return "bg-red-50 border-red-200";
      case ROOM_STATUS.MANTENIMIENTO:
        return "bg-orange-50 border-orange-200";
      default:
        return "bg-green-50 border-green-200";
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          {/* Formulario de Registro */}
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h2 className="my-6 text-center text-3xl font-extrabold text-gray-900">
              Registrar Huésped
            </h2>
            <form onSubmit={handleSubmit(registerGuest)} className="space-y-6">
              <div className="mt-1">
                <label
                  htmlFor="nombre"
                  className="block text-sm font-medium text-gray-700"
                >
                  Nombre
                </label>
                <input
                  type="text"
                  name="nombre"
                  id="nombre"
                  {...register("nombre")}
                  className="appearance-none block w-full px-3 py-1 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.nombre && (
                  <p className="text-red-600 text-sm">
                    {errors.nombre.message}
                  </p>
                )}
              </div>
              <div className="mt-1">
                <label
                  htmlFor="apellido"
                  className="block text-sm font-medium text-gray-700"
                >
                  Apellido
                </label>
                <input
                  type="text"
                  id="apellido"
                  name="apellido"
                  {...register("apellido")}
                  className="appearance-none block w-full px-3 py-1 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.apellido && (
                  <p className="text-red-600 text-sm">
                    {errors.apellido.message}
                  </p>
                )}
              </div>
              <div className="mt-1">
                <label
                  htmlFor="email"
                  className="block text-sm font-medium text-gray-700"
                >
                  Email
                </label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  {...register("email")}
                  className="appearance-none block w-full px-3 py-1 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.email && (
                  <p className="text-red-600 text-sm">{errors.email.message}</p>
                )}
              </div>
              <div className="mt-1">
                <label
                  htmlFor="telefono"
                  className="block text-sm font-medium text-gray-700"
                >
                  Teléfono
                </label>
                <input
                  type="tel"
                  id="telefono"
                  name="telefono"
                  {...register("telefono")}
                  className="appearance-none block w-full px-3 py-1 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.telefono && (
                  <p className="text-red-600 text-sm">
                    {errors.telefono.message}
                  </p>
                )}
              </div>
              <div className="mt-1">
                <label
                  htmlFor="habitacion"
                  className="block text-sm font-medium text-gray-700"
                >
                  Habitación
                </label>
                <select
                  name="habitacion"
                  {...register("habitacion")}
                  className="appearance-none block w-full px-3 py-1 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                >
                  <option value="">Seleccionar habitación</option>
                  {rooms
                    .filter((room) => room.estado === ROOM_STATUS.DISPONIBLE)
                    .map((room) => (
                      <option key={room.id} value={room.id}>
                        Habitación {room.numero} - {room.tipo}
                      </option>
                    ))}
                </select>
                {errors.habitacion && (
                  <p className="text-red-600 text-sm">
                    {errors.habitacion.message}
                  </p>
                )}
              </div>
              <button
                type="submit"
                className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 disabled:opacity-50"
              >
                Registrar Huésped
              </button>
            </form>
          </div>

          {/* Lista de Habitaciones */}
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h2 className="text-xl font-semibold text-black mb-4 flex items-center gap-2">
              Estado de Habitaciones
            </h2>
            <div className="space-y-4">
              {rooms.map((room) => {
                const guest = guests.find((g) => g.habitacionId === room.id);
                return (
                  <div
                    key={room.id}
                    className={`p-4 rounded-lg border ${getRoomStatusColor(
                      room.estado
                    )}`}
                  >
                    <div className="flex justify-between items-start">
                      <div>
                        <h3 className="font-semibold text-black">
                          Habitación {room.numero}
                        </h3>
                        <p className="text-sm text-gray-600">
                          Tipo: {room.tipo}
                        </p>
                        <p className="text-sm font-medium text-black">
                          Estado:{" "}
                          {room.estado === ROOM_STATUS.MANTENIMIENTO
                            ? "En Mantenimiento"
                            : room.estado === ROOM_STATUS.OCUPADA
                            ? "Ocupada"
                            : "Disponible"}
                        </p>
                        {guest && (
                          <div className="mt-2 text-sm text-gray-600">
                            <p>
                              Huésped: {guest.nombre} {guest.apellido}
                            </p>
                            <p>Contacto: {guest.telefono}</p>
                          </div>
                        )}
                      </div>
                      <div className="flex gap-2">
                        {guest && (
                          <button
                            onClick={() => handleCheckout(room.id)}
                            className="bg-red-600 cursor-pointer text-white py-1 px-3 rounded-md hover:bg-red-700"
                          >
                            Checkout
                          </button>
                        )}
                        {!guest && (
                          <button
                            onClick={() => toggleRoomStatus(room.id)}
                            className="bg-yellow-600 text-white py-1 px-3 rounded-md hover:bg-yellow-700 cursor-pointer"
                          >
                            {room.estado === ROOM_STATUS.MANTENIMIENTO
                              ? "Cambiar a Disponible"
                              : "Cambiar a Mantenimiento"}
                          </button>
                        )}
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ReceptionistForm;
