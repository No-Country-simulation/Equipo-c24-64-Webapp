package gestionDeReservas.factory;

import java.util.ArrayList;
import java.util.List;


import gestionDeReservas.services.implementation.ImageService;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.model.entity.Image;
import gestionDeReservas.model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.model.entity.Room;
import gestionDeReservas.model.entity.RoomType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class TypeRoomFactory {

    @Autowired
    ImageService imageService;
    
    public RoomType buildRoomType(CreateTypeRoomDTO type, @RequestPart @Nullable List<MultipartFile> imagesRequest) throws Exception{

        List<Image> images = imageService.addImages(imagesRequest);

        return RoomType
                .builder()
                .name(type.name())
                .capacity(type.capacity())
                .description(type.description())
                .price(type.price())
                .rooms(new ArrayList<Room>())
                .images(images)
                .build();
    }
}