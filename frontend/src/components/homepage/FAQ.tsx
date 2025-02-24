import React, { useState } from "react";
import { AiOutlineDown, AiOutlineUp } from "react-icons/ai";
import { questions, Question } from "../../data/FAQ";

const FAQ = () => {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

  const toggleAnswer = (index: number) => {
    setActiveIndex(activeIndex === index ? null : index);
  };

  return (
    <section className="max-w-6xl mx-auto px-4 py-8" id="faq">
      <h2 className="text-2xl font-bold text-center mb-6">Preguntas Frecuentes</h2>
      <div className="space-y-4 cursor-pointer">
        {questions.map((item: Question, index: number) => (
          <div key={index} className="bg-white p-4 rounded-lg shadow-lg">
            <h3
              className="text-xl font-semibold w-full text-left flex justify-between"
              onClick={() => toggleAnswer(index)}
              role="button"
            >
              {item.question}
              <span className="text-lg">
                {activeIndex === index ? <AiOutlineUp /> : <AiOutlineDown />}
              </span>
            </h3>
            {activeIndex === index && (
              <p className="mt-2 text-gray-600">{item.answer}</p>
            )}
          </div>
        ))}
      </div>
    </section>
  );
};

export default FAQ;
