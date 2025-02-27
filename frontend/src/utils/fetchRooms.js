import useSearchStore from "@/hooks/useSearchStore";

const fetchRooms = async () => {
  try {
    // Obtener el tipo de habitación seleccionado del store
    const { roomType } = useSearchStore.getState();
    
    // Obtener todas las habitaciones desde la API
    const response = await fetch(`https://hotels-1-0.onrender.com/api/rooms`);
    const data = await response.json();
    
    // Definir habitaciones filtradas, inicialmente todas
    let filteredRooms = data;
    
    // Filtrar por tipo de habitación si se ha seleccionado uno
    if (roomType) {
      // Mapeo de tipos de habitación a capacidades
      const capacityMap = {
        'single': 1,
        'double': 2,
        'triple': 3,
        'cuadruple': 4,
        'quintuple': 5,
        'suite': 6
      };
      
      // Aplicar filtro por capacidad según el tipo seleccionado
      const targetCapacity = capacityMap[roomType];
      if (targetCapacity) {
        filteredRooms = data.filter(room => room.capacity === targetCapacity);
      }
    }
    
    // Guardar las habitaciones filtradas en el estado global
    useSearchStore.getState().setRooms(filteredRooms);
    return filteredRooms;
  } catch (error) {
    console.error("Error fetching rooms:", error);
    return [];
  }
};

export default fetchRooms;