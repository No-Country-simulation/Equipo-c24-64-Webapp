package gestionDeReservas.controller;

import gestionDeReservas.model.dto.RoomDTO.RoomEditRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestionDeReservas.model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.services.Interface.RoomServiceUI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




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
    public ResponseEntity<?>  getRoomById(@PathVariable int id) throws Exception {
        return  ResponseEntity.ok(roomService.getRoomById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createRoom(@RequestBody RoomCreateRequestDTO roomRequestDTO) throws Exception {
        RoomGetDTO roomCreated = roomService.addRoom(roomRequestDTO);
        return ResponseEntity.ok(roomCreated);
    }

    @PutMapping("")
    public ResponseEntity<?>  editRoom(@RequestBody RoomEditRequestDTO roomEdit) throws Exception {
        return ResponseEntity.ok(roomService.editRoom(roomEdit));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) throws Exception {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }

}

