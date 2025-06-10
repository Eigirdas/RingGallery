package lt.ca.javau12.ring_store.controllers;

import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.services.RingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
        Long userId = extractUserId(userDetails);
        RingDto created = ringService.createRing(dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get all rings created by the authenticated user
     */
    @GetMapping("/my")
    public ResponseEntity<List<RingDto>> getMyRings(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = extractUserId(userDetails);
        List<RingDto> userRings = ringService.getRingsByUser(userId);
        return ResponseEntity.ok(userRings);
    }

    /**
     * Extracts the user ID from the JWT-authenticated user
     */
    private Long extractUserId(UserDetails userDetails) {
        // This assumes your JWT sets the username as the user ID
        return Long.parseLong(userDetails.getUsername());

        // If your JWT uses email instead, you'd need:
        // return userService.findByEmail(userDetails.getUsername()).getId();
    }
}
