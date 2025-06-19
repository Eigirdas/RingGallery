package lt.ca.javau12.ring_store.controllers;

import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.Dto.RingUpdateDto;
import lt.ca.javau12.ring_store.services.RingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user/rings")
@CrossOrigin(origins = "http://localhost:3000")
public class UserRingController {

    private final RingService ringService;
   

    public UserRingController(RingService ringService) {
        this.ringService = ringService;
    }
    @PostMapping
    public ResponseEntity<RingDto> createRing(@Valid @RequestBody RingCreateDto dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = ringService.getUserIdFromUsername(userDetails.getUsername());
        RingDto created = ringService.createRing(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/my")
    public ResponseEntity<List<RingDto>> getMyRings(@AuthenticationPrincipal UserDetails userDetails) {
    	Long userId = ringService.getUserIdFromUsername(userDetails.getUsername());
        List<RingDto> userRings = ringService.getRingsByUser(userId);
        return ResponseEntity.ok(userRings);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RingDto> updateRingUser(
            @PathVariable Long id,
            @RequestPart("ring") RingUpdateDto dto,
            @RequestPart(value = "images", required = false) List<MultipartFile> newImages
    ) throws IOException {
        RingDto updated = ringService.updateRingUser(id, dto, newImages);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRing(@PathVariable Long id) {
        ringService.delete(id);
        return ResponseEntity.noContent().build();
    }


    
}
