package gestionDeReservas.factory;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import gestionDeReservas.Model.entity.Image;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class ImageFactory {

    public Image buildImage(CloudinaryResponseDTO respDTO){
        return Image.builder()
                .publicId(respDTO.publicId())
                .url(respDTO.url())
                .build();
    }

}
