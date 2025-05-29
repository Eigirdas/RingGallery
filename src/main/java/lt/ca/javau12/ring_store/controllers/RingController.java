package lt.ca.javau12.ring_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.services.RingService;

@RestController
@RequestMapping("/rings")
@CrossOrigin(origins = "http://localhost:3000")
public class RingController {
	private final RingService ringService;
	
	public RingController(RingService ringService) {
		this.ringService = ringService;
	}
	
	@GetMapping
	public List<RingDto> getAll(){
		return ringService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RingDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(ringService.findById(id));
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<RingDto> createRing(@PathVariable Long userId, @Valid @RequestBody RingCreateDto dto){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ringService.createRing(dto, userId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RingDto> update(@PathVariable Long id,@RequestBody RingDto dto){
		RingDto updated = ringService.update(id,dto);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		ringService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	

	
	
			
	
	
	

}
