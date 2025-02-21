package gestionDeReservas.services.implementation;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import gestionDeReservas.Model.entity.Image;
import gestionDeReservas.Model.entity.Room;
import gestionDeReservas.factory.ImageFactory;
import gestionDeReservas.repository.ImageRepository;
import gestionDeReservas.services.Interface.ImageServiceUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService implements ImageServiceUI {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    private ImageFactory imageFactory;

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public void removeImageByPublicId(int id) {
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        cloudinaryService.deleteFile(image.getPublicId());
        imageRepository.delete(image);
    }

    @Override
    public List<Image> addImages(List<MultipartFile> files, Room room) {
        List<byte[]> images = convertMultipartFilesToByteArrays(files);
        return cloudinaryService.uploadFiles(images).stream().map(response ->createAndSaveImage(response,room)).toList();
    }

    private List<byte[]> convertMultipartFilesToByteArrays(List<MultipartFile> files) {
        return files.stream()
                .map(file -> {
                    try {
                        return file.getBytes();
                    } catch (IOException e) {
                        throw new RuntimeException("Error: " + file.getOriginalFilename(), e);
                    }
                })
                .toList();
    }

    private Image createAndSaveImage(CloudinaryResponseDTO response, Room room) {
        Image image = imageFactory.buildImage(response);
        image.setRoom(room);
        return imageRepository.save(image);
    }

}
