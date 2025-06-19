package lt.ca.javau12.ring_store.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.Dto.RingUpdateAdminDto;
import lt.ca.javau12.ring_store.Dto.RingUpdateDto;
import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.RingImage;
import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.mappers.RingMapper;
import lt.ca.javau12.ring_store.repositories.RingImageRepository;
import lt.ca.javau12.ring_store.repositories.RingRepository;
import lt.ca.javau12.ring_store.repositories.UserRepository;
import lt.ca.javau12.ring_store.services.helpers.RingUpdateData;

@Service
public class RingService {
	private static final Logger log = LoggerFactory.getLogger(RingService.class);
	private final RingRepository ringRepository;
	private final RingMapper ringMapper;
	private final UserRepository userRepository;
	private final UserService userService;
	private final RingImageRepository ringImageRepository;
	
	public RingService(RingRepository ringRepository,
			RingMapper ringMapper,
			UserRepository userRepository,
			UserService userService,
			RingImageRepository ringImageRepository
			) {
		this.ringRepository = ringRepository;
		this.ringMapper = ringMapper;
		this.userRepository = userRepository;
		this.userService = userService;
		this.ringImageRepository = ringImageRepository;
	}

	public List<RingDto> getAll() {
		return ringRepository
				.findAll()
				.stream().map(ringMapper::toDto)
				.toList();
	}

	   public RingDto findById(Long id) {
	        return ringRepository.findById(id)
	            .map(ringMapper::toDto)
	            .orElseThrow(() -> new RuntimeException("No ring found with this id: " + id));
	    }

	    public RingDto createRing(RingCreateDto dto, Long id) {
	        User user = userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("No user found with this id: " + id));
	        
	        Ring ring = ringMapper.toEntity(dto, user);
	        return ringMapper.toDto(ringRepository.save(ring));
	    }

	public RingDto update(Long id, RingDto dto) {
		Ring existing = ringRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ring not found by id: " + id));
		
		existing.setName(dto.getName());
		existing.setDescription(dto.getDescription());
		existing.setMetalType(dto.getMetalType());
		existing.setSize(dto.getSize());
		
		// fetch user by id and set it
		if(dto.getUserId() != null) {
			User user = userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new RuntimeException("No user found"));
			existing.setUser(user);
		} else {
			existing.setUser(null);
		}
		
		return ringMapper.toDto(ringRepository.save(existing));
	}

	public void delete(Long id) {
		Ring ring = ringRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ring not found with id: " + id));
		
		ringRepository.delete(ring);
	}

    public List<RingDto> getRingsByUser(Long userId) {
        List<Ring> userRings = ringRepository.findByUserId(userId);
        return userRings.stream()
                .map(ringMapper::toDto)
                .toList();
    }

	public Long getUserIdFromUsername(String username) {
		return userService.extractUserIdFromUsername(username);
	}
	

	// general method for both admin and user updates
	private Ring applyRingUpdates(Ring ring, RingUpdateData data, List<MultipartFile> newImages) throws IOException {
	    ring.setName(data.getName());
	    ring.setDescription(data.getDescription());
	    ring.setMetalType(data.getMetalType());
	    ring.setSize(data.getSize());

	    if (data.getUserId() != null) {
	        User user = userRepository
	        		.findById(data.getUserId())
	                .orElseThrow(() -> new RuntimeException("User not found with id: " + data.getUserId()));
	        ring.setUser(user);
	    }

	    // Extract remaining image IDs
	    List<Long> remainingIds = data.getRemainingImages() != null ? data.getRemainingImages()
	    		.stream()
	            .map(this::extractIdFromUrl)
	            .toList() : List.of();

	    // Delete removed images
	    List<RingImage> toRemove = ring
	    		.getImages()
	    		.stream()
	            .filter(img -> !remainingIds.contains(img.getId()))
	            .toList();

	    toRemove.forEach(image -> {
	        ring
	        .getImages()
	        .remove(image);
	        ringImageRepository.deleteById(image.getId());
	        log.debug("Removed image with ID {}", image.getId());
	    });

	    // Add new images
	    if (newImages != null) {
	        for (MultipartFile file : newImages) {
	            RingImage image = new RingImage(file.getBytes(), ring, file.getOriginalFilename());
	            ringImageRepository.save(image);
	            ring.getImages().add(image);
	            log.debug("Added new image: {}", file.getOriginalFilename());
	        }
	    }

	    log.info("Updating ring {} with data: {}", ring.getId(), data);
	    return ring;
	}
	
	public RingDto updateRingUser(Long id, @Valid RingUpdateDto dto, List<MultipartFile> newImages) throws IOException {
	    Ring ring = ringRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ring not found by id: " + id));

	    RingUpdateData data = new RingUpdateData();
	    data.setName(dto.getName());
	    data.setDescription(dto.getDescription());
	    data.setMetalType(dto.getMetalType());
	    data.setSize(dto.getSize());
	    data.setRemainingImages(dto.getRemainingImages());

	    Ring updated = applyRingUpdates(ring, data, newImages);
	    log.info("User update requested for ring {} by user (no userId in DTO)", id);
	    return ringMapper.toDto(ringRepository.save(updated));
	}
	
	public RingDto updateRingAdmin(Long id, @Valid RingUpdateAdminDto dto, List<MultipartFile> newImages) throws IOException {
	    Ring ring = ringRepository
	    		.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ring not found by id: " + id));

	    RingUpdateData data = new RingUpdateData();
	    data.setName(dto.getName());
	    data.setDescription(dto.getDescription());
	    data.setMetalType(dto.getMetalType());
	    data.setSize(dto.getSize());
	    data.setRemainingImages(dto.getRemainingImages());
	    data.setUserId(dto.getUserId());

	    
	    Ring updated = applyRingUpdates(ring, data, newImages);
	    log.info("Admin update requested for ring {}. Assigning to userId: {}", id, dto.getUserId());
	    return ringMapper.toDto(ringRepository.save(updated));
	}
	
	
	private Long extractIdFromUrl(String url) {
		try {
			String[] parts = url.split("/");
			return Long.parseLong(parts[parts.length - 1]);
		} catch(Exception e) {
			throw new RuntimeException("Invalid image Url format: " + url);
		}
	}
}
	