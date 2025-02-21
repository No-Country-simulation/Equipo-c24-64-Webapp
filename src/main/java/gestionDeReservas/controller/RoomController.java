package gestionDeReservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.services.Interface.RoomServiceUI;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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
    public ResponseEntity<?>  createRoom(@RequestPart("room") RoomCreateRequestDTO roomRequestDTO, @RequestPart List<MultipartFile> files) {
        RoomGetDTO roomCreated = roomService.addRoom(roomRequestDTO, files);
        return ResponseEntity.ok(roomCreated);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?>  uploadRoomImages(@PathVariable int id, @RequestPart List<MultipartFile> files) {
        RoomGetDTO roomCreated = roomService.uploadRoomImages(id, files);
        return ResponseEntity.ok(roomCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }
}

