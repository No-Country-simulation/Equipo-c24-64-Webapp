import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import * as Yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import "./form.css";
import fetchRooms from "@/services/fetchRooms";

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
    .max(15, "El teléfono debe tener máximo 15 números."),
  habitacion: Yup.string().required("Debes seleccionar una habitación"),
  dni: Yup.string()
    .required("El dni es requerido")
    .matches(/^[0-9]+$/, "Solo se permiten números")
    .min(5, "El DNI debe tener al menos 5 caracteres")
    .max(10, "El DNI no puede tener más de 10 caracteres"),
});

const Form = () => {
  const [rooms, setRooms] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const {
    register,
    handleSubmit,
    // reset,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  useEffect(() => {
    console.log(isLoading);
    const fetchHabitaciones = async () => {
      setIsLoading(true);
      const habitaciones = await fetchRooms("rooms");
      setRooms(habitaciones);
      setIsLoading(false);
    };
    fetchHabitaciones();
    console.log(isLoading);
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-2 gap-8">
        {/* Formulario de Registro */}
        <div className="bg-white p-6 rounded-lg shadow-md">
          <h2 className="my-6 text-center text-3xl font-extrabold text-gray-900">
            Registrar Huésped
          </h2>
          <form className="space-y-6" onSubmit={handleSubmit()}>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                Nombre
              </label>
              <input
                type="text"
                {...register("nombre")}
                className="block w-full px-3 py-2 border rounded-md"
              />
              {errors.nombre && (
                <p className="text-red-600 text-sm">{errors.nombre.message}</p>
              )}
            </div>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                Apellido
              </label>
              <input
                type="text"
                {...register("apellido")}
                className="block w-full px-3 py-2 border rounded-md"
              />
              {errors.apellido && (
                <p className="text-red-600 text-sm">
                  {errors.apellido.message}
                </p>
              )}
            </div>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                DNI
              </label>
              <input
                type="text"
                {...register("dni")}
                className="block w-full px-3 py-2 border rounded-md"
              />
              {errors.dni && (
                <p className="text-red-600 text-sm">{errors.dni.message}</p>
              )}
            </div>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                Email
              </label>
              <input
                type="email"
                {...register("email")}
                className="block w-full px-3 py-2 border rounded-md"
              />
              {errors.email && (
                <p className="text-red-600 text-sm">{errors.email.message}</p>
              )}
            </div>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                Teléfono
              </label>
              <input
                type="tel"
                {...register("telefono")}
                className="block w-full px-3 py-2 border rounded-md"
              />
              {errors.telefono && (
                <p className="text-red-600 text-sm">
                  {errors.telefono.message}
                </p>
              )}
            </div>
            <div className="mt-1">
              <label className="block text-sm font-medium text-gray-700">
                Habitación
              </label>
              <select
                {...register("habitacion")}
                className="block w-full px-3 py-2 border rounded-md"
              >
                <option value="">Seleccionar habitación</option>
                {rooms.map((room) => (
                  <option key={room.id} value={room.id}>
                    Habitación {room.roomNumber} - {room.typeRoom.name}
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
              className="w-full py-2 px-4 cursor-pointer bg-indigo-600 text-white rounded-md"
            >
              Registrar Huésped
            </button>
          </form>
        </div>

        {/* Lista de Habitaciones */}
        <div className="bg-white p-6 rounded-lg shadow-md scrollable-container">
          <h2 className="text-xl font-semibold text-black mb-4 flex items-center gap-2">
            Lista de Habitaciones
          </h2>
          {isLoading && (
            <span className=" font-medium">Cargando habitaciones ...</span>
          )}
          <div className="space-y-5">
            {rooms
              ? rooms.map((room) => (
                  <div
                    className={`p-4 flex justify-between rounded-lg space-y-1 ${
                      room.roomStatus === null ? "bg-green-100" : "bg-red-100"
                    }`}
                  >
                    <div key={room.id}>
                      <h3 className="font-semibold text-black">
                        Habitación {room.roomNumber}
                      </h3>
                      <p className="text-sm text-gray-600">
                        Tipo: {room.typeRoom.name}
                      </p>
                      <p className="text-sm font-medium text-black">
                        Estado:{" "}
                        {room.roomStatus === null
                          ? "Disponible"
                          : room.roomStatus === true
                          ? "Ocupada"
                          : room.roomStatus === false
                          ? "En mantenimiento"
                          : ""}
                      </p>
                    </div>
                    <div className=" flex items-center justify-center">
                      <button class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded cursor-pointer">
                        {room.roomStatus === true ? "Check Out" : "Check In"}
                      </button>{" "}
                    </div>
                  </div>
                ))
              : "No hay habitaciones cargadas"}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Form;
