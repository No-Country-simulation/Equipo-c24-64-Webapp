package gestionDeReservas.factory;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import gestionDeReservas.Model.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageFactory {

    public Image buildImage(CloudinaryResponseDTO respDTO){
        return Image.builder()
                .publicId(respDTO.publicId())
                .url(respDTO.url())
                .build();
    }

}
