package gestionDeReservas.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gestionDeReservas.Model.dto.CloudinaryDTO.CloudinaryResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import gestionDeReservas.services.Interface.CloudinaryServiceUI;

@Service
public class CloudinaryService implements CloudinaryServiceUI{

    private Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes) {
        return uploadFiles(bytes, null);
    }

    @Override
    public List<CloudinaryResponseDTO> uploadFiles(List<byte[]> bytes, String folder) {
        return bytes.stream().map(array ->{
           return uploadImage(array, folder);
        }).toList();
    }

    @Override
    public boolean deleteFile(String pulicId) {
        try {
            var result = cloudinary.uploader().destroy(pulicId, ObjectUtils.emptyMap());
            String response = result.get("result").toString();
            return response.equals("ok") ;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CloudinaryResponseDTO uploadImage(byte[] bytes, String folder){
        var param = ObjectUtils.asMap(
            "overwrite", true
        );

        if (folder != null && !folder.isEmpty()){
            param.put("folder", folder);
        }

        return uploadToCloudinary(bytes, param);
    }

    public CloudinaryResponseDTO uploadToCloudinary(byte[] bytes, Map<String,Object> param){
        try {
            var result = cloudinary.uploader().upload(bytes, param);
            String url =  result.get("url").toString();
            String publicId = result.get("public_id").toString();

            return new CloudinaryResponseDTO(url, publicId);
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }    
}
