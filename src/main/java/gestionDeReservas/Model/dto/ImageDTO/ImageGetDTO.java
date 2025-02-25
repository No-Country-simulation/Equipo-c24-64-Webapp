package gestionDeReservas.model.dto.ImageDTO;

import lombok.Builder;

@Builder
public record ImageGetDTO(
        long id,
        String url,
        String publicId
){

}
