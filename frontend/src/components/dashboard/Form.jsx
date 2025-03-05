import { useState } from "react";
import Swal from "sweetalert2";
import { useForm } from "react-hook-form";
import * as Yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { ROOM_STATUS } from "@/types.js";
import roomsData from "@/data/rooms.js";
import "./form.css";

const schema = Yup.object().shape({
  nombre: Yup.string().required("El nombre es requerido"),
  apellido: Yup.string().required("El apellido es requerido"),
  email: Yup.string()
    .email("Ingrese un email válido")
    .required("El email es requerido"),
  telefono: Yup.string()
    .required("El teléfono es requerido")
    .matches(/^[0-9]+$/, "Solo se permiten números")
    .min(6, "El teléfono debe tener mínimo 6 números.")
    .max(15, "El teléfono debe tener mínimo 6 números."),
  habitacion: Yup.string().required("Debes seleccionar una habitación"),
  dni: Yup.string()
    .required("El dni es requerido")
    .matches(/^[0-9]+$/, "Solo se permiten números")
    .min(5, "El DNI debe tener al menos 5 caracteres")
    .max(10, "El DNI no puede tener más de 10 caracteres"),
});

const Form = () => {
  const [guests, setGuests] = useState([]);
  const [rooms, setRooms] = useState(roomsData);
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const registerGuest = (formData) => {
    const newGuest = new User(
      formData.nombre,
      formData.apellido,
      formData.dni,
      formData.email,
      formData.telefono,
      formData.habitacion
    );
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
      setRooms(
        rooms.map((room) =>
          room.id === roomId
            ? { ...room, estado: ROOM_STATUS.DISPONIBLE }
            : room
        )
      );
      Swal.fire({
        title: "Huesped eliminado con exito!",
        icon: "success",
      });
    }

    reset();
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
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
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
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.apellido && (
                  <p className="text-red-600 text-sm">
                    {errors.apellido.message}
                  </p>
                )}
              </div>
              <div className="mt-1">
                <label
                  htmlFor="apellido"
                  className="block text-sm font-medium text-gray-700"
                >
                  DNI
                </label>
                <input
                  type="number"
                  id="dni"
                  name="dni"
                  {...register("dni")}
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                />
                {errors.dni && (
                  <p className="text-red-600 text-sm">{errors.dni.message}</p>
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
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
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
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
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
          <div className="bg-white p-6 rounded-lg shadow-md scrollable-container">
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
                        {/* {room.estado === ROOM_STATUS.OCUPADA && (
                          <button
                            onClick={() => handleCheckout(room.id)}
                            className="bg-red-500 text-white px-3 py-1 rounded-md hover:bg-red-600"
                          >
                            Checkout
                          </button>
                        )} */}
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
                            className={`${
                              room.estado === ROOM_STATUS.MANTENIMIENTO
                                ? "bg-green-600 hover:bg-green-700"
                                : "bg-yellow-600 hover:bg-yellow-700"
                            } text-white py-1 px-3 rounded-md cursor-pointer`}
                          >
                            {room.estado === ROOM_STATUS.MANTENIMIENTO
                              ? "Ir a Disponible"
                              : "Ir a Mantenimiento"}
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

export default Form;
