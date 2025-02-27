import { create } from "zustand";

// Definir el tipo de estado que vamos a manejar
interface SearchState {
  isGuestsOpen: boolean;
  checkIn: string;
  checkOut: string;
  roomType: string;
  rooms: string[];
  guests: {
    adults: number;
    children: number;
    rooms: number;
  };
  setIsGuestsOpen: (state: boolean) => void;
  setCheckIn: (date: string) => void;
  setCheckOut: (date: string) => void;
  setRooms: (rooms: string[]) => void;
  setRoomType: (roomType: string) => void;
  setGuests: (guests: {
    adults: number;
    children: number;
    rooms: number;
  }) => void;
  handleGuestsChange: (
    type: "adults" | "children" | "rooms",
    operation: "add" | "subtract"
  ) => void;
}

const useSearchStore = create<SearchState>((set) => ({
  isGuestsOpen: false,
  checkIn: "",
  checkOut: "",
  roomType: "",
  rooms: [],
  guests: {
    adults: 2,
    children: 0,
    rooms: 1,
  },
  setIsGuestsOpen: (state) => set({ isGuestsOpen: state }),
  setCheckIn: (date) => set({ checkIn: date }),
  setCheckOut: (date) => set({ checkOut: date }),
  setRoomType: (roomType) => set({ roomType }),
  setGuests: (guests) => set({ guests }),
  setRooms: (rooms) => set({ rooms }),
  handleGuestsChange: (type, operation) => {
    set((state) => ({
      guests: {
        ...state.guests,
        [type]:
          operation === "add"
            ? state.guests[type] + 1
            : Math.max(type === "adults" ? 1 : 0, state.guests[type] - 1),
      },
    }));
  },
}));

export default useSearchStore;