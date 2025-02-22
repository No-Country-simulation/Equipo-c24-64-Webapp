package gestionDeReservas.services.Interface;

import gestionDeReservas.Model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageServiceUI {
    void removeImage(int id) throws Exception;
    List<Image> addImages(List<MultipartFile> files) throws Exception;
    List<Image> getAllImages() throws Exception;
    Image getImageByPublicId(int id) throws Exception;
}
