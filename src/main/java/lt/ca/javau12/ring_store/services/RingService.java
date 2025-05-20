package lt.ca.javau12.ring_store.services;

import java.util.List;

import org.springframework.stereotype.Service;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.mappers.RingMapper;
import lt.ca.javau12.ring_store.repositories.RingRepository;

@Service
public class RingService {
	
	private final RingRepository ringRepository;
	private final RingMapper ringMapper;
	
	public RingService(RingRepository ringRepository,RingMapper ringMapper) {
		this.ringRepository = ringRepository;
		this.ringMapper = ringMapper;
	}

	public List<RingDto> getAll() {
		return ringRepository
				.findAll()
				.stream().map(ringMapper::toDto)
				.toList();
	}

	public RingDto findById(Long id) {
		Ring ring = ringRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No ring found with id: " + id));
		return ringMapper.toDto(ring);
	}

	public RingDto create(RingDto dto) {
		Ring ring = ringMapper.toEntity(dto);
		return ringMapper.toDto(ringRepository.save(ring));
	}

	public RingDto update(Long id, RingDto dto) {
		Ring existing = ringRepository.findById(id).orElseThrow(() -> new RuntimeException("Ring not found by id: " + id));
		existing.setName(dto.name());
		existing.setDescription(dto.description());
		existing.setMetalType(dto.metalType());
		existing.setSize(dto.size());
		
		return ringMapper.toDto(ringRepository.save(existing));
	}

	public void delete(Long id) {
		Ring ring = ringRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ring not found with id: " + id));
		
		ringRepository.delete(ring);
	}


	}
	