package lt.ca.javau12.ring_store.controllers;

import jakarta.validation.Valid;
import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.services.RingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rings")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminRingController {

    private final RingService ringService;

    public AdminRingController(RingService ringService) {
        this.ringService = ringService;
    }
	@GetMapping
	public List<RingDto> getAll(){
		return ringService.getAll();
	}

    @GetMapping("/{id}")
    public ResponseEntity<RingDto> getRingById(@PathVariable Long id) {
        return ResponseEntity.ok(ringService.findById(id));
    }
    
	@PostMapping("/{userId}")
	public ResponseEntity<RingDto> createRing(@PathVariable Long userId, @Valid @RequestBody RingCreateDto dto){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ringService.createRing(dto, userId));
	}

    @PutMapping("/{id}")
    public ResponseEntity<RingDto> updateRing(@PathVariable Long id,
                                              @Valid @RequestBody RingDto dto) {
        RingDto updated = ringService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRing(@PathVariable Long id) {
        ringService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
