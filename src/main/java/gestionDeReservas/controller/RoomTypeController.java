package gestionDeReservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.services.Interface.RoomServiceUI;
import gestionDeReservas.services.Interface.TypeRoomServiceUI;

@RestController
@RequestMapping("/typeroom")
public class RoomTypeController {
     @Autowired
    private TypeRoomServiceUI roomService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomService.getAllTypesRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getTypeRoomById(@PathVariable Integer id) {
        return  ResponseEntity.ok(roomService.getTypeById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createRoom(@RequestBody CreateTypeRoomDTO roomRequestDTO) {
        RoomTypeGetDTO roomTypeCreated = roomService.createTypeRoom(roomRequestDTO);
        return ResponseEntity.ok(roomTypeCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteTypeRoom(id);
        return ResponseEntity.ok("Room deleted");
    }   
    
}
