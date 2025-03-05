import useSearchStore from "@/hooks/useSearchStore";

const fetchRooms = async () => {
  try {
    const { roomType } = useSearchStore.getState();

    const response = await fetch(`https://hotels-1-0.onrender.com/api/rooms`);
    const data = await response.json();
    let filteredRooms = data;

    if (roomType) {
      const capacityMap = {
        single: 1,
        double: 2,
        triple: 3,
        cuadruple: 4,
        quintuple: 5,
        suite: 6,
      };
      const targetCapacity = capacityMap[roomType];

      if (targetCapacity) {
        filteredRooms = data.filter((room) => room.capacity === targetCapacity);
      }
    }
    useSearchStore.getState().setRooms(filteredRooms);

    return filteredRooms;
  } catch (error) {
    console.error("Error fetching rooms:", error);
    return [];
  }
};

export default fetchRooms;
