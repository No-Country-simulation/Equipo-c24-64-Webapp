package gestionDeReservas.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.Model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.Model.entity.RoomType;
import gestionDeReservas.exception.RoomTypeNotFoundException;
import gestionDeReservas.factory.TypeRoomFactory;
import gestionDeReservas.mapper.RoomTypeMapper;
import gestionDeReservas.repository.TypeRoomRepository;
import gestionDeReservas.services.Interface.TypeRoomServiceUI;

@Service
public class RoomTypeService implements TypeRoomServiceUI {

    @Autowired
    private TypeRoomRepository roomTypeRepository;

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private TypeRoomFactory typeRoomFactory;

    @Override
    public List<RoomTypeGetDTO> getAllTypesRooms() {
        return roomTypeRepository.findAll().stream().map((e) -> {
            return roomTypeMapper.toGetDTO(e);
        }).toList();
    }

    @Override
    public RoomType getTypeById(Integer id) {
        RoomType type = roomTypeRepository.findById(id)
        .orElseThrow(() -> new RoomTypeNotFoundException("Room type not found"));
        return type;
    }

    @Override
    public RoomTypeGetDTO createTypeRoom(CreateTypeRoomDTO roomTOCreate) {
        RoomType room = typeRoomFactory.buildRoomType(roomTOCreate);
        roomTypeRepository.save(room);
        return roomTypeMapper.toGetDTO(room);
    }

    @Override
    public void deleteTypeRoom(Integer id) {
        roomTypeRepository.deleteById(id);
    }

    @Override
    public RoomTypeGetDTO editTypeRoom(EditRoomTypeDTO roomType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editTypeRoom'");
    }

   

  

    
    
}
