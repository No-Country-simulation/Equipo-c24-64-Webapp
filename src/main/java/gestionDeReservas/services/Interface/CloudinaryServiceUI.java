package gestionDeReservas.services.Interface;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryServiceUI {
    public List<String> uploadFiles(List<MultipartFile> files);
    public List<String> uploadFiles(List<MultipartFile> files, String folder);
    public boolean deleteFile(String fileName);
}
