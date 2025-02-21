package gestionDeReservas.services.Interface;

import gestionDeReservas.Model.dto.ImageDTO.ImageCreateRequestDTO;
import gestionDeReservas.Model.dto.ImageDTO.ImageGetDTO;
import gestionDeReservas.Model.entity.Image;
import gestionDeReservas.Model.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageServiceUI {
    void removeImageByPublicId(int id);
    List<Image> addImages(List<MultipartFile> files, Room room);
}
