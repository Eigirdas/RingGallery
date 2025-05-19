package lt.ca.javau12.ring_store.Dto;

import java.time.LocalDateTime;
import java.util.List;


public record RingDto(
	    String name,
	    String description,
	    String metalType,
	    double size,
	    String userName,
	    LocalDateTime createdAt,
	    List<String> imageUrls
	) {}
