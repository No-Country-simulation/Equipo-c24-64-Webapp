package gestionDeReservas.facade;

import gestionDeReservas.model.dto.ImageDTO.ImageGetDTO;
import gestionDeReservas.model.entity.Image;
import gestionDeReservas.mapper.ImageMapper;
import gestionDeReservas.services.Interface.ImageServiceUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class ImageFacade {

    @Autowired
    ImageServiceUI imageService;

    @Autowired
    ImageMapper imageMapper;


    public List<ImageGetDTO> getAllImages() throws  Exception{
       return convertImagesToDTO(imageService.getAllImages());
    }

    public List<ImageGetDTO> addImages(List<MultipartFile> files) throws  Exception{
        return convertImagesToDTO(imageService.addImages(files));
    }

    public ImageGetDTO getImageById(int id) throws  Exception{
        return convertImageToDTO(imageService.getImageByPublicId(id));
    }

    public void deleteImage(int id) throws  Exception{
        imageService.removeImage(id);
    }

    private ImageGetDTO convertImageToDTO(Image image) {
        return imageMapper.toImageGetDTO(image);
    }

    private List<ImageGetDTO> convertImagesToDTO(List<Image> images) {
        return images.stream().map(this::convertImageToDTO).toList();
    }
}
