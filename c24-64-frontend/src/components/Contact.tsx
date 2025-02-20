import { motion } from "framer-motion";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const schema = yup.object().shape({
  name: yup
    .string()
    .min(4, "El nombre debe tener al menos 4 caracteres")
    .max(100, "Máximo 100 caracteres")
    .required("El nombre es requerido"),
  email: yup
    .string()
    .email("Correo inválido")
    .max(256, "Máximo 256 caracteres")
    .required("El correo es requerido"),
  message: yup
    .string()
    .min(5, "El mensaje debe tener al menos 5 caracteres")
    .max(500, "Máximo 500 caracteres")
    .required("El mensaje es requerido"),
});

const Contact: React.FC = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = (data: any) => {
    console.log("Mensaje enviado", data);
  };

  return (
    <section className="max-w-4xl mx-auto p-6" id="contact">
      <h2 className="text-3xl font-bold text-center mb-6 inter">Contacto</h2>
      <motion.form
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        onSubmit={handleSubmit(onSubmit)}
        className="bg-white shadow-md rounded-lg p-6"
      >
        <div className="mb-4">
          <label className="block text-gray-700 font-medium" htmlFor="name">
            Nombre
          </label>
          <input
            type="text"
            id="name"
            {...register("name")}
            className="border rounded p-2 w-full"
            aria-label="Nombre"
            aria-describedby="name-error"
          />
          <p className="text-red-500 text-sm" id="name-error">
            {errors.name?.message}
          </p>
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 font-medium" htmlFor="email">
            Email
          </label>
          <input
            id="email"
            type="email"
            {...register("email")}
            aria-label="Correo electrónico"
            aria-describedby="email-error"
            className="border rounded p-2 w-full"
          />
          <p className="text-red-500 text-sm" id="email-error">
            {errors.email?.message}
          </p>
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 font-medium" htmlFor="message">
            Mensaje
          </label>
          <textarea
            {...register("message")}
            className="border rounded p-2 w-full field-sizing-content min-h-20 max-h-40 resize-none"
            rows={4}
            id="message"
            name="message"
            aria-label="Mensaje"
            aria-describedby="message-error"
          ></textarea>
          <p className="text-red-500 text-sm" id="message-error">
            {errors.message?.message}
          </p>
        </div>
        <motion.button
          whileHover={{ scale: 1.05 }}
          transition={{ duration: 0.3 }}
          type="submit"
          className="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 rounded-lg"
        >
          Enviar Mensaje
        </motion.button>
      </motion.form>
    </section>
  );
};

export default Contact;
