package lt.ca.javau12.ring_store.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.RingImage;
import lt.ca.javau12.ring_store.repositories.RingImageRepository;
import lt.ca.javau12.ring_store.repositories.RingRepository;

@Service
public class ImageService {

    private final RingRepository ringRepository;
    private final RingImageRepository ringImageRepository;
    private final UserService userService;

    public ImageService(RingRepository ringRepository, RingImageRepository ringImageRepository, UserService userService) {
        this.ringRepository = ringRepository;
        this.ringImageRepository = ringImageRepository;
        this.userService = userService;
    }

    public void uploadPhotos(UserDetails userDetails, Long ringId, List<MultipartFile> files) throws IOException {
        Long userId = userService.extractUserIdFromUsername(userDetails.getUsername());
        Ring ring = ringRepository.findById(ringId)
                .orElseThrow(() -> new IllegalArgumentException("Ring not found"));

        if (!ring.getUser().getId().equals(userId)) {
            throw new SecurityException("Unauthorized: You do not own this ring");
        }

        for (MultipartFile file : files) {
            RingImage image = new RingImage(file.getBytes(), ring, file.getOriginalFilename());
            ringImageRepository.save(image);
        }
    }

    public byte[] getPhotos(Long id) {
        RingImage image = ringImageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No image found"));
        return image.getImage();
    }

    public RingImage getImageEntity(Long id) {
        return ringImageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No image found"));
    }

    public MediaType getMediaTypeFromFilename(String filename) {
        if (filename == null) return MediaType.APPLICATION_OCTET_STREAM;

        String lower = filename.toLowerCase();
        if (lower.endsWith(".png")) return MediaType.IMAGE_PNG;
        if (lower.endsWith(".webp")) return MediaType.valueOf("image/webp");
        if (lower.endsWith(".jpeg") || lower.endsWith(".jpg")) return MediaType.IMAGE_JPEG;
        
        return MediaType.APPLICATION_OCTET_STREAM; 
    }
}
