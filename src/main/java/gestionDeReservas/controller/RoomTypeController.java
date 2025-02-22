package gestionDeReservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.services.Interface.TypeRoomServiceUI;
import org.springframework.web.bind.annotation.PutMapping;


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
    public ResponseEntity<?>  createTypeRoom(@RequestBody CreateTypeRoomDTO roomRequestDTO) throws Exception{
        RoomTypeGetDTO roomTypeCreated = roomService.createTypeRoom(roomRequestDTO);
        return ResponseEntity.ok(roomTypeCreated);
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
