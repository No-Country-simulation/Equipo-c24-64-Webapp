package gestionDeReservas.services.implementation;

import gestionDeReservas.model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import gestionDeReservas.model.entity.Image;
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
    public void removeImage(int id) throws Exception {
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        imageRepository.delete(image);
        removeImageFromCloundinary(image);
    }

    @Override
    public void removeImageFromCloundinary(Image image) throws Exception {
        cloudinaryService.deleteFile(image.getPublicId());
    }

    @Override
    public List<Image> addImages(List<MultipartFile> files) throws Exception {
        if (files == null || files.isEmpty()) return List.of();
        List<byte[]> images = convertMultipartFilesToByteArrays(files);
        return cloudinaryService.uploadFiles(images).stream().map(this::createAndSaveImage).toList();
    }

    @Override
    public List<Image> getAllImages() throws Exception{
        return imageRepository.findAll();
    }

    @Override
    public Image getImageByPublicId(int id) throws Exception {
        return imageRepository.findById(id).orElseThrow(RuntimeException::new);
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

    private Image createAndSaveImage(CloudinaryResponseDTO response) {
        Image image = imageFactory.buildImage(response);
        return imageRepository.save(image);
    }

}
