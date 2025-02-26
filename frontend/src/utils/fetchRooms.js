const fetchRooms = async () => {
  try {
    const response = await fetch(`https://hotels-1-0.onrender.com/api/rooms`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching rooms:", error);
    return [];
  }
};

export default fetchRooms;
