import React, { useState } from "react";
import { AiOutlineDown } from "react-icons/ai";
import { AiOutlineUp } from "react-icons/ai";
interface Question {
  question: string;
  answer: string;
}

const FAQ = () => {
  const [activeIndex, setActiveIndex] = useState<number | null>(null); // Tipar el estado como número o null

  const questions: Question[] = [
    // Tipar el array de preguntas
    {
      question: "¿Cómo puedo hacer una reserva?",
      answer:
        "Puedes hacer una reserva usando el formulario de búsqueda en la página de inicio.",
    },
    {
      question: "¿Qué tipos de habitaciones ofrecen?",
      answer:
        "Ofrecemos habitaciones individuales, dobles y suites con vista al mar.",
    },
    {
      question: "¿Cuál es la política de cancelación?",
      answer: "La cancelación es gratuita hasta 24 horas antes del check-in.",
    },
    {
      question: "¿El hotel ofrece servicio de transporte?",
      answer:
        "Sí, ofrecemos transporte desde y hacia el aeropuerto bajo solicitud.",
    },
  ];

  const toggleAnswer = (index: number) => {
    // Tipar el parámetro de la función
    setActiveIndex(activeIndex === index ? null : index);
  };

  return (
    <section className="max-w-6xl mx-auto faq-section px-4 py-8">
      <h2 className="text-2xl font-bold text-center mb-6 inter ">
        Preguntas Frecuentes
      </h2>
      <div className="faq-list space-y-4 cursor-pointer">
        {questions.map((item, index) => (
          <div
            key={index}
            className="faq-item bg-white p-4 rounded-lg shadow-lg "
          >
            <h3
              className="faq-question text-xl font-semibold w-full text-left flex justify-between "
              onClick={() => toggleAnswer(index)}
              role="button"
            >
              {item.question}
              <span className="text-lg">
                {activeIndex === index ? <AiOutlineUp /> : <AiOutlineDown />}
              </span>
            </h3>
            {activeIndex === index && (
              <p className="faq-answer mt-2 text-gray-600">{item.answer}</p>
            )}
          </div>
        ))}
      </div>
    </section>
  );
};

export default FAQ;
