package gestionDeReservas.mapper;

import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import gestionDeReservas.Model.entity.Image;

public class ImageMapper {

    public ImageGetDTO toImageGetDTO(Image image) {
        return ImageGetDTO.builder()
                .id(image.getId())
                .url(image.getUrl())
                .publicId(image.getPublicId())

                .build();
    }
}
