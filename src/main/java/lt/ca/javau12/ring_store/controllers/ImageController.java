package lt.ca.javau12.ring_store.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lt.ca.javau12.ring_store.services.ImageService;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //
    @PostMapping("/{ringId}")
    public ResponseEntity<Void> uploadImages(
            @PathVariable("ringId") Long ringId,
            @RequestParam List<MultipartFile> file) throws IOException {

        imageService.uploadPhotos(ringId, file);
        return ResponseEntity.ok().build();
    }

    // single photo by its image ID
    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("imageId") Long imageId) {
        byte[] image = imageService.getPhotos(imageId);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    // Get all photos for a specific ring
    @GetMapping("/ring/{ringId}")
    public ResponseEntity<List<byte[]>> getAllPhotosForRing(@PathVariable("ringId") Long ringId) {
        List<byte[]> images = imageService.getAllPhotosForRing(ringId);
        return ResponseEntity.ok(images);
    }

    //Delete an image by its image ID
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> delete(@PathVariable("imageId") Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}