import React, { useState } from "react";
import { Facebook, Instagram, Twitter } from "lucide-react";
import { useForm } from "react-hook-form";

interface INewsletterForm {
  email: string;
}
function Footer() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting },
  } = useForm<INewsletterForm>();
  const onSubmit = async (data: INewsletterForm) => {
    try {
      const response = await fetch("#", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify(data),
      });
      console.log(response);

      if (response.ok) {
        alert("¡Te has suscrito con éxito!");
        reset();
      }
    } catch (error) {
      if (error instanceof Error) {
        console.log("Error", error.message);
      }
    }
  };
  return (
    <div className=" bg-gray-100">
      <footer className="bg-gray-900 text-gray-300">
        <div className="bg-blue-600 py-8">
          <div className="container mx-auto px-4">
            <div className="max-w-4xl mx-auto text-white">
              <div className="flex flex-col md:flex-row items-center justify-between gap-6">
                <div className="flex items-center gap-2">
                  <div>
                    <h3 className="text-sm font-medium">
                      RECIBE TODAS LAS OFERTAS EN TU EMAIL
                    </h3>
                    <p className="text-lg font-semibold">
                      Suscríbete a nuestra newsletter
                    </p>
                  </div>
                </div>
                <form
                  onSubmit={handleSubmit(onSubmit)}
                  className="flex w-full md:w-auto gap-2"
                >
                  <input
                    type="email"
                    placeholder="Ingresa tu email"
                    className={`px-4 py-2 rounded-lg bg-gray-100 w-full md:w-80 text-black ${
                      errors.email ? "border-red-500 border-2" : ""
                    }`}
                    {...register("email", {
                      required: "El email es requerido",
                      pattern: {
                        value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                        message: "Email inválido",
                      },
                    })}
                    disabled={isSubmitting}
                    aria-invalid={errors.email ? "true" : "false"}
                  />
                  <button
                    type="submit"
                    title="Suscribirse a la newsletter"
                    role="button"
                    className="bg-white cursor-pointer text-blue-600 px-6 py-2 rounded-lg font-semibold hover:bg-gray-200 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                    disabled={isSubmitting}
                  >
                    {isSubmitting ? "Enviando..." : "Enviar"}
                  </button>
                </form>
              </div>
              {errors.email && (
                <p className="text-red-500 text-sm md:ps-5 md:text-center lg:text-end lg:pe-26" aria-live="polite">
                  {errors.email.message}
                </p>
              )}
            </div>
          </div>
        </div>
        <div className="container mx-auto px-4 pt-12 pb-2">
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            <div className="flex flex-col items-center md:items-start">
              <div className="flex items-center text-2xl font-bold text-white ">
                <span className="text-blue-500">Luxe Haven</span>
              </div>
              <p className="text-sm text-white font-medium text-center md:text-left">
                HOTELS & RESORTS
              </p>
              <div className="mt-6">
                <h4 className="font-medium mb-4 text-center md:text-left">
                  Nuestras redes sociales
                </h4>
                <div className="flex gap-4">
                  <a href="#" className="hover:text-blue-400 transition-colors">
                    <Facebook className="w-6 h-6" />
                  </a>
                  <a href="#" className="hover:text-blue-400 transition-colors">
                    <Instagram className="w-6 h-6" />
                  </a>
                  <a href="#" className="hover:text-blue-400 transition-colors">
                    <Twitter className="w-6 h-6" />
                  </a>
                </div>
              </div>
            </div>
            <div>
              <h3 className="text-lg font-semibold text-white mb-4">
                Acerca de Luxe Haven
              </h3>
              <ul className="space-y-3">
                <li>
                  <a href="#" className="hover:text-white transition-colors">
                    Empresa
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white transition-colors">
                    Contacto
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white transition-colors">
                    Ubicación
                  </a>
                </li>
              </ul>
            </div>
            <div>
              <h3 className="text-lg font-semibold text-white mb-4">
                Servicios
              </h3>
              <ul className="space-y-3">
                <li>
                  <a href="#faq" className="hover:text-white transition-colors">
                    Preguntas Frecuentes
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-white transition-colors">
                    Cómo Reservar
                  </a>
                </li>
                <li>
                  <a
                    href="#ofertas"
                    className="hover:text-white transition-colors"
                  >
                    Promociones
                  </a>
                </li>
              </ul>
            </div>
            <div>
              <h3 className="text-lg font-semibold text-white mb-4">
                Pagos y Seguridad
              </h3>
              <div className="grid grid-cols-2 gap-4">
                <a href="">
                  <img
                    src="https://upload.wikimedia.org/wikipedia/commons/5/5e/Visa_Inc._logo.svg"
                    alt="Visa"
                    className="h-8 bg-white p-1 rounded"
                  />
                </a>
                <a href="">
                  <img
                    src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Mastercard-logo.svg"
                    alt="Mastercard"
                    className="h-8 bg-white p-1 rounded"
                  />
                </a>
                <a href="">
                  <img
                    src="https://upload.wikimedia.org/wikipedia/commons/b/b5/PayPal.svg"
                    alt="PayPal"
                    className="h-8 bg-white p-1 rounded"
                  />
                </a>
                <a href="#">
                  <img
                    src="https://upload.wikimedia.org/wikipedia/commons/f/fa/American_Express_logo_%282018%29.svg"
                    alt="American Express"
                    className="h-8 bg-white p-1 rounded"
                  />
                </a>
              </div>
            </div>
          </div>
          <div className="mt-10 pt-4 border-t border-gray-700 flex flex-col md:flex-row justify-between items-center gap-4 text-sm">
            <div className="flex gap-6">
              <a href="#" className="hover:text-white transition-colors">
                Términos y Condiciones
              </a>
              <a href="#" className="hover:text-white transition-colors">
                Privacidad
              </a>
            </div>
            <p className="text-gray-400">
              © 2025 Hotel Luxe Haven. <br />
              by No-Country
            </p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
