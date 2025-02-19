package gestionDeReservas.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import gestionDeReservas.services.Interface.CloudinaryServiceUI;

public class CloudinaryService implements CloudinaryServiceUI{

    private Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }


    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            urls.add(uploadImage(multipartFile));
        }
        return urls;
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files, String folder) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            urls.add(uploadImage(multipartFile,folder));
        }

        return urls;
    }

    public boolean deleteFile(String fileName) {
        try {
            var result = cloudinary.uploader().destroy(fileName, ObjectUtils.emptyMap());
            String response = result.get("result").toString();
            return response.equals("ok") ;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


     public String uploadImage(MultipartFile file, String folder){
        var param = ObjectUtils.asMap(
            "overwrite", true,
            "folder", folder
        );
        try {
            var result = cloudinary.uploader().upload(file.getBytes(), param);
            return result.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    
    public String uploadImage(MultipartFile file){
        var param = ObjectUtils.asMap(
            "overwrite", true
        );
        try {
            return uploadToCloudinary(file.getBytes(), param);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String uploadToCloudinary(byte[] bytes, Map<String,Object> param){
        try {
            var result = cloudinary.uploader().upload(bytes, param);
            return result.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }    
}
