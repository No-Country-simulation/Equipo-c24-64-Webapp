package gestionDeReservas.mapper;

import gestionDeReservas.model.dto.ImageDTO.ImageGetDTO;
import gestionDeReservas.model.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageGetDTO toImageGetDTO(Image image) {
        return ImageGetDTO.builder()
                .id(image.getId())
                .url(image.getUrl())
                .publicId(image.getPublicId())

                .build();
    }
}
