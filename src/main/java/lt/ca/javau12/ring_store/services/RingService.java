package lt.ca.javau12.ring_store.services;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lt.ca.javau12.ring_store.Dto.RingDto;
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

	public ProblemDetail findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public RingDto create(RingDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public RingDto update(Long id, RingDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void uploadPhotos(Long id, List<MultipartFile> files) {
		// TODO Auto-generated method stub
		
	}

	public byte[] getImage(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
