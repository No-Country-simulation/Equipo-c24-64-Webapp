package gestionDeReservas.controller;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.services.Interface.TypeRoomServiceUI;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/typeRoom")
public class RoomTypeController {
    @Autowired
    private TypeRoomServiceUI roomService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roomService.getAllTypesRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getTypeRoomById(@PathVariable Integer id) throws Exception{
        return  ResponseEntity.ok(roomService.getTypeById(id));
    }
    
    @PostMapping("")
    public ResponseEntity<?>  createTypeRoom(@RequestPart("roomType") CreateTypeRoomDTO roomRequestDTO, @RequestPart @Nullable List<MultipartFile> files) throws Exception{
        RoomTypeGetDTO roomTypeCreated = roomService.createTypeRoom(roomRequestDTO,files);
        return ResponseEntity.ok(roomTypeCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  uploadRoomTypeImages(@PathVariable int id, @RequestPart List<MultipartFile> files) throws Exception {
        return ResponseEntity.ok(roomService.uploadRoomTypeImages(id,files));
    }

    @PutMapping("/{roomId}/{imageId}")
    public ResponseEntity<?>  deleteRoomTypeImage(@PathVariable int roomId, @PathVariable int imageId) throws Exception {
        roomService.removeRoomImage(roomId, imageId);
        return ResponseEntity.ok("Image deleted");
    }


    @PutMapping("")
    public ResponseEntity<?> editTypeRoom(@RequestBody EditRoomTypeDTO entity)  throws Exception{
        return ResponseEntity.ok(roomService.editTypeRoom(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteTypeRoom(@PathVariable("id") Integer id) throws Exception {
        roomService.deleteTypeRoom(id);
        return ResponseEntity.ok("Room deleted");
    }   
    
}
