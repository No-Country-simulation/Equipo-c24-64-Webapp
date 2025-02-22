package gestionDeReservas.factory;

import java.util.ArrayList;
import java.util.List;

import gestionDeReservas.Model.entity.Image;
import gestionDeReservas.services.implementation.ImageService;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gestionDeReservas.Model.dto.TypeRoomDTO.CreateTypeRoomDTO;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.Model.entity.RoomType;
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
