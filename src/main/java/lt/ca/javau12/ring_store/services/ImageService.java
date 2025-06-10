package lt.ca.javau12.ring_store.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public ImageService(RingRepository ringRepository,RingImageRepository ringImageRepository) {
		this.ringRepository = ringRepository;
		this.ringImageRepository = ringImageRepository;
	}

    public void uploadPhotos(Long ringId, List<MultipartFile> files) throws IOException {
        Ring ring = ringRepository.findById(ringId)
                .orElseThrow(() -> new RuntimeException("Ring not found"));

        for (MultipartFile file : files) {
            RingImage image = new RingImage(file.getBytes(), ring, file.getOriginalFilename());
            ringImageRepository.save(image);
        }
    }

	public byte[] getPhotos(Long id) {
		RingImage image = ringImageRepository.findById(id).orElseThrow(() -> new RuntimeException ("No image found"));
		return image.getImage();
	}
	
	
	public void deleteImage(Long id) {
		ringImageRepository.deleteById(id);
	}

	public List<byte[]> getAllPhotosForRing(Long ringId) {
		List<RingImage> images = ringImageRepository.findByRingId(ringId);
		return images.stream()
				.map(RingImage::getImage)
				.collect(Collectors.toList());
	}

}