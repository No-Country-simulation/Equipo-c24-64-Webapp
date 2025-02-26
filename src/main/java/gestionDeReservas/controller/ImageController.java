package gestionDeReservas.controller;

import gestionDeReservas.facade.ImageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageFacade imageFacade;

    @GetMapping
    public ResponseEntity<?> getAll() throws Exception {
        return ResponseEntity.ok(imageFacade.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(imageFacade.getImageById(id));
    }

    @PostMapping
    public ResponseEntity<?> addImages(@RequestBody List<MultipartFile> files) throws Exception {
        return ResponseEntity.ok(imageFacade.addImages(files));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteImage(@RequestParam int id) throws Exception {
        imageFacade.deleteImage(id);
        return ResponseEntity.ok("Image deleted");
    }
}