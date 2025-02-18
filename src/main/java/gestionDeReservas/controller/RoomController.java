package gestionDeReservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionDeReservas.Model.dto.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomGetDTO;
import gestionDeReservas.services.RoomServiceUI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomServiceUI roomService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getMethodName(@PathVariable int id) {
        return  ResponseEntity.ok(roomService.getRoomById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createRoom(@RequestBody RoomCreateRequestDTO roomRequestDTO) {
        RoomGetDTO roomCreated = roomService.addRoom(roomRequestDTO);
        return ResponseEntity.ok(roomCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }   
    
}

