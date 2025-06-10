package lt.ca.javau12.ring_store.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.mappers.RingMapper;
import lt.ca.javau12.ring_store.repositories.RingRepository;
import lt.ca.javau12.ring_store.repositories.UserRepository;

@Service
public class RingService {
	
	private final RingRepository ringRepository;
	private final RingMapper ringMapper;
	private final UserRepository userRepository;
	
	public RingService(RingRepository ringRepository,RingMapper ringMapper,UserRepository userRepository) {
		this.ringRepository = ringRepository;
		this.ringMapper = ringMapper;
		this.userRepository = userRepository;
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
	

	}
	