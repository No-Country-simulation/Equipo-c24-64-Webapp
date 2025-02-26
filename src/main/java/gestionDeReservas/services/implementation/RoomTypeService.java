package gestionDeReservas.services.implementation;

import java.util.List;

import gestionDeReservas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.Image;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.exception.RoomTypeNotFoundException;
import gestionDeReservas.factory.TypeRoomFactory;
import gestionDeReservas.mapper.RoomTypeMapper;
import gestionDeReservas.repository.IRoomTypeRepository;
import gestionDeReservas.services.Interface.TypeRoomServiceUI;
import jakarta.transaction.Transactional;

@Service
public class RoomTypeService implements TypeRoomServiceUI {

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

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
    public RoomType getTypeById(Integer id) throws Exception {
        return findById(id);
    }

    @Override
    public RoomTypeGetDTO createTypeRoom(CreateTypeRoomDTO roomTOCreate, List<MultipartFile> files) throws Exception{
        RoomType room = typeRoomFactory.buildRoomType(roomTOCreate, files);
        roomTypeRepository.save(room);
        return roomTypeMapper.toGetDTO(room);
    }

    @Override
    public void deleteTypeRoom(Integer id) throws Exception {
        RoomType room = roomTypeRepository
        .findById(id)
        .orElseThrow(() -> new RoomTypeNotFoundException("Room type not found"));
        roomTypeRepository.deleteById(id);
    }

    @Override
    public RoomTypeGetDTO editTypeRoom(EditRoomTypeDTO roomType) throws Exception{

        Integer id = roomType.id();
        RoomType room = roomTypeRepository
        .findById(id)
        .orElseThrow(() -> new RoomTypeNotFoundException("Room type not found"));

        room.setName(roomType.name());
        room.setDescription(roomType.description());
        room.setPrice(roomType.price());
        room.setCapacity(roomType.capacity());

        roomTypeRepository.save(room);
        return roomTypeMapper.toGetDTO(room);
    }


    public RoomType findById(Integer typeid){
        return roomTypeRepository
        .findById(typeid)
        .orElseThrow(() -> new RoomTypeNotFoundException("room type not found in database"));
    }

    @Override
    public RoomTypeGetDTO uploadRoomTypeImages(int id, List<MultipartFile> files) throws Exception {
        RoomType roomType = findById(id);
        if (files == null || files.isEmpty()) {
            throw new NotFoundException("files not found");
        }
        roomType.getImages().addAll(
                typeRoomFactory.getImageService().addImages(files)
        );
        return roomTypeMapper.toGetDTO(roomTypeRepository.save(roomType));
    }

    @Override
    @Transactional
    public void removeRoomImage(int roomId, int imageId) throws Exception{
        RoomType room = findById(roomId);
        Image image = typeRoomFactory.getImageService().getImageByPublicId(imageId);
        room.getImages().remove(image);
        typeRoomFactory.getImageService().removeImageFromCloundinary(image);
        roomTypeRepository.save(room);
    }

}