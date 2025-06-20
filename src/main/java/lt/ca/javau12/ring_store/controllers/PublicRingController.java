package lt.ca.javau12.ring_store.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.services.RingService;

@RestController
@RequestMapping("/rings")
@CrossOrigin(origins = "http://localhost:3000")
public class PublicRingController {
	private final RingService ringService;
	
	public PublicRingController(RingService ringService) {
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
}