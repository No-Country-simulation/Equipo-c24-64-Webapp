package gestionDeReservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionDeReservas.Model.dto.RoomDTO.RoomCreateRequestDTO;
import gestionDeReservas.Model.dto.RoomDTO.RoomGetDTO;
import gestionDeReservas.services.Interface.RoomServiceUI;




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

    @PostMapping("/{id}")
    public ResponseEntity<?>  uploadRoomImages(@PathVariable int id, @RequestPart List<MultipartFile> files) {
        RoomGetDTO roomCreated = roomService.uploadRoomImages(id, files);
        return ResponseEntity.ok(roomCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteRoom(@PathVariable("id") Integer id) throws Exception {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted");
    }

}

