package gestionDeReservas.services.Interface;

import java.util.List;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;

public interface CloudinaryServiceUI {
    List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes);
    List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes, String folder);
    void deleteFile(String fileName);
}
