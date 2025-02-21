package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryServiceUI {
    public List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes);
    public List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes, String folder);
    public boolean deleteFile(String fileName);
}
