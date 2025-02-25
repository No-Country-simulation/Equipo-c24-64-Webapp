package gestionDeReservas.model.dto.ImageDTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ImageCreateRequestDTO(
        List<MultipartFile> images
){

}