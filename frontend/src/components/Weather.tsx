import React, { useEffect, useState } from "react";
import { Cloud, CloudRain, Sun, Loader2 } from "lucide-react";

interface WeatherData {
  main: {
    temp: number;
    humidity: number;
    feels_like: number;
  };
  weather: [{ description: string; icon: string; main: string }];
}

interface ForecastData {
  list: Array<{
    dt_txt: string;
    main: {
      temp: number;
      humidity: number;
      feels_like: number;
    };
    weather: [{ description: string; icon: string; main: string }];
  }>;
}

const Weather: React.FC = () => {
  const [weather, setWeather] = useState<WeatherData | null>(null);
  const [forecast, setForecast] = useState<ForecastData | null>(null);
  const [loading, setLoading] = useState(true);

  const API_KEY = "6d51cd4aa4deb04d9eaa166f1616a848";
  const CITY = "Buenos Aires";
  //el tiempo es en ms asi que si lo cambias usa ms. basicamente se guarda la response en localStorage para no realizar el pedido en cada montaje del componente.
  const CACHE_TIME = 5 * 60 * 1000;

  useEffect(() => {
    const fetchData = async () => {
      const cachedWeatherData = localStorage.getItem("weatherData");
      const cachedForecastData = localStorage.getItem("forecastData");
      const cachedTime = localStorage.getItem("cacheTime");

      const currentTime = new Date().getTime();

      if (
        cachedWeatherData &&
        cachedForecastData &&
        cachedTime &&
        currentTime - Number(cachedTime) < CACHE_TIME
      ) {
        setWeather(JSON.parse(cachedWeatherData));
        setForecast(JSON.parse(cachedForecastData));
        setLoading(false);
      } else {
        try {
          const [weatherResponse, forecastResponse] = await Promise.all([
            fetch(
              `https://api.openweathermap.org/data/2.5/weather?q=${CITY}&units=metric&appid=${API_KEY}&lang=es`
            ),
            fetch(
              `https://api.openweathermap.org/data/2.5/forecast?q=${CITY}&units=metric&appid=${API_KEY}&lang=es`
            ),
          ]);

          const weatherData = await weatherResponse.json();
          const forecastData = await forecastResponse.json();

          localStorage.setItem("weatherData", JSON.stringify(weatherData));
          localStorage.setItem("forecastData", JSON.stringify(forecastData));
          localStorage.setItem("cacheTime", currentTime.toString());

          setWeather(weatherData);
          setForecast(forecastData);
          setLoading(false);
        } catch (error) {
          console.error("Error fetching weather data:", error);
          setLoading(false);
        }
      }
    };

    fetchData();
  }, []);

  const getWeatherIcon = (main: string) => {
    switch (main.toLowerCase()) {
      case "clear":
        return <Sun className="w-12 h-12 text-yellow-400" />;
      case "rain":
        return <CloudRain className="w-12 h-12 text-blue-400" />;
      default:
        return <Cloud className="w-12 h-12 text-gray-400" />;
    }
  };

  const getDayName = (dateStr: string) => {
    const days = [
      "Domingo",
      "Lunes",
      "Martes",
      "Miércoles",
      "Jueves",
      "Viernes",
      "Sábado",
    ];
    const date = new Date(dateStr);
    return days[date.getDay()];
  };

  const getForecastDays = () => {
    if (!forecast) return [];

    const uniqueDays = new Map();

    forecast.list.forEach((item) => {
      const date = new Date(item.dt_txt).toLocaleDateString();
      if (!uniqueDays.has(date)) {
        uniqueDays.set(date, item);
      }
    });

    return Array.from(uniqueDays.values()).slice(0, 4);
  };

  if (loading) {
    return (
      <div className="w-full max-w-3xl mx-auto rounded-lg bg-white shadow-lg">
        <div className="flex items-center justify-center p-8">
          <Loader2 className="w-8 h-8 animate-spin text-blue-500" />
        </div>
      </div>
    );
  }

  return (
    <div className="w-full max-w-3xl mx-auto rounded-lg bg-gradient-to-br from-blue-50 to-blue-100 shadow-lg overflow-hidden">
      <div className="p-6">
        <h2 className="text-2xl font-bold text-center text-blue-900 mb-6">
          {CITY}
        </h2>

        {weather && (
          <div className="space-y-6">
            {/* Current Weather */}
            <div className="flex items-center justify-center p-6 bg-white rounded-lg shadow-sm">
              <div className="text-center">
                {getWeatherIcon(weather.weather[0].main)}
                <div className="mt-2">
                  <span className="text-4xl font-bold text-blue-900">
                    {Math.round(weather.main.temp)}°C
                  </span>
                  <p className="text-gray-600 capitalize mt-1">
                    {weather.weather[0].description}
                  </p>
                  <p className="text-sm text-gray-500 mt-1">
                    Sensación térmica: {Math.round(weather.main.feels_like)}°C
                  </p>
                  <p className="text-sm text-gray-500">
                    Humedad: {weather.main.humidity}%
                  </p>
                </div>
              </div>
            </div>

            {/* Forecast */}
            <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
              {getForecastDays().map((day, index) => (
                <div
                  key={index}
                  className="bg-white p-4 rounded-lg shadow-sm text-center transform transition-transform duration-200 hover:scale-105"
                >
                  <p className="font-semibold text-blue-900 mb-2">
                    {index === 0 ? "Hoy" : getDayName(day.dt_txt)}
                  </p>
                  {getWeatherIcon(day.weather[0].main)}
                  <p className="text-2xl font-bold text-blue-900 mt-2">
                    {Math.round(day.main.temp)}°C
                  </p>
                  <p className="text-sm text-gray-600 capitalize">
                    {day.weather[0].description}
                  </p>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default Weather;
