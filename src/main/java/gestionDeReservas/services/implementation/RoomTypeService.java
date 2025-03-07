package gestionDeReservas.services.implementation;

import java.util.List;

import gestionDeReservas.exception.NotFoundException;
import gestionDeReservas.services.Interface.RoomTypeServiceUI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.EditRoomTypeDTO;
import gestionDeReservas.model.dto.TypeRoomDTO.RoomTypeGetDTO;
import gestionDeReservas.model.entity.Image;
import gestionDeReservas.model.entity.RoomType;
import gestionDeReservas.factory.TypeRoomFactory;
import gestionDeReservas.mapper.RoomTypeMapper;
import gestionDeReservas.repository.IRoomTypeRepository;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoomTypeService implements RoomTypeServiceUI {
    private IRoomTypeRepository roomTypeRepository;
    private RoomTypeMapper roomTypeMapper;
    private TypeRoomFactory typeRoomFactory;

    @Override
    public List<RoomTypeGetDTO> getAllTypesRooms() {
        return roomTypeRepository.findAll().stream().map(roomTypeMapper::toGetDTO).toList();
    }

    @Override
    public RoomType getTypeById(Integer id) {
        return findRoomTypeById(id);
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
        .orElseThrow(() -> new NotFoundException("Room type not found"));
        roomTypeRepository.deleteById(id);
    }

    @Override
    public RoomTypeGetDTO editTypeRoom(EditRoomTypeDTO roomType) throws Exception{

        Integer id = roomType.id();
        RoomType room = roomTypeRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Room type not found"));

        room.setName(roomType.name());
        room.setDescription(roomType.description());
        room.setPrice(roomType.price());
        room.setCapacity(roomType.capacity());

        roomTypeRepository.save(room);
        return roomTypeMapper.toGetDTO(room);
    }

    public RoomType findRoomTypeById(Integer typeid){
        return roomTypeRepository
        .findById(typeid)
        .orElseThrow(() -> new NotFoundException("room type not found in database"));
    }

    @Override
    public RoomTypeGetDTO uploadRoomTypeImages(int id, List<MultipartFile> files) throws Exception {
        RoomType roomType = findRoomTypeById(id);
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
        RoomType room = findRoomTypeById(roomId);
        Image image = typeRoomFactory.getImageService().getImageByPublicId(imageId);
        room.getImages().remove(image);
        typeRoomFactory.getImageService().removeImageFromCloundinary(image);
        roomTypeRepository.save(room);
    }
}