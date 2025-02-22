package gestionDeReservas.controller;

import gestionDeReservas.Model.dto.RoomDTO.RoomEditRequestDTO;
import jakarta.annotation.Nullable;
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
    public ResponseEntity<?>  getRoomById(@PathVariable int id) throws Exception {
        return  ResponseEntity.ok(roomService.getRoomById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createRoom(@RequestPart("room") RoomCreateRequestDTO roomRequestDTO, @RequestPart @Nullable List<MultipartFile> files) throws Exception {
        RoomGetDTO roomCreated = roomService.addRoom(roomRequestDTO, files);
        return ResponseEntity.ok(roomCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  uploadRoomImages(@PathVariable int id, @RequestPart List<MultipartFile> files) throws Exception {
        return ResponseEntity.ok(roomService.uploadRoomImages(id,files));
    }

    @PutMapping("")
    public ResponseEntity<?>  editRoom(@RequestBody RoomEditRequestDTO roomEdit) throws Exception {
        return ResponseEntity.ok(roomService.editRoom(roomEdit));
    }

    @PutMapping("/{roomId}/{imageId}")
    public ResponseEntity<?>  deleteRoomImage(@PathVariable int roomId, @PathVariable int imageId) throws Exception {
       roomService.removeRoomImage(roomId, imageId);
       return ResponseEntity.ok("Image deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) throws Exception {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }

}

