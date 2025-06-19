package lt.ca.javau12.ring_store.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lt.ca.javau12.ring_store.entities.RingImage;
import lt.ca.javau12.ring_store.services.ImageService;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{ringId}")
    public ResponseEntity<Void> uploadImages(
            @PathVariable("ringId") Long ringId,
            @RequestParam List<MultipartFile> file,
            @AuthenticationPrincipal UserDetails userDetails) throws IOException { // only authorised users (injection)

        imageService.uploadPhotos(userDetails, ringId, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getPhotos(@PathVariable("imageId") Long imageId) {
        RingImage image = imageService.getImageEntity(imageId);
        MediaType mediaType = imageService.getMediaTypeFromFilename(image.getFilename());

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(image.getImage());
    }
}
