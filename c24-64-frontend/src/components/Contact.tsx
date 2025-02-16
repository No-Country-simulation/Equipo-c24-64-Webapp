import { motion } from "framer-motion";
import { useState } from "react";

const Contact: React.FC = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    message: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Mensaje enviado", formData);
  };

  return (
    <section className="max-w-4xl mx-auto p-6" id="contact">
      <h2 className="text-3xl font-bold text-center mb-6 inter">Contacto</h2>
      <motion.form 
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        onSubmit={handleSubmit} 
        className="bg-white shadow-md rounded-lg p-6"
      >
        <div className="mb-4">
          <label className="block text-gray-700 font-medium">Nombre</label>
          <input 
            type="text" 
            name="name" 
            value={formData.name} 
            onChange={handleChange} 
            className="border rounded p-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 font-medium">Email</label>
          <input 
            type="email" 
            name="email" 
            value={formData.email} 
            onChange={handleChange} 
            className="border rounded p-2 w-full"
            required
          />
        </div>
        <div className="mb-4">
          <label className="block text-gray-700 font-medium">Mensaje</label>
          <textarea 
            name="message" 
            value={formData.message} 
            onChange={handleChange} 
            className="border rounded p-2 w-full"
            rows={4}
            required
          ></textarea>
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