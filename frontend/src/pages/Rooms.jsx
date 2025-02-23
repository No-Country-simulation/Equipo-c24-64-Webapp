import React from "react";
import Header from "../layouts/Header";
import Footer from "../layouts/Footer"; 
import RoomListing from "@/components/rooms/rooms";

function Rooms() {
  return (
    <>
      <Header />
      <div className="mt-16">
        <RoomListing />
        <Footer />
      </div>
    </>
  );
}

export default Rooms;
