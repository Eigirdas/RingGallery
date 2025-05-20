package lt.ca.javau12.ring_store.Dto;

import java.time.LocalDateTime;

public record UserDto(
		Long id,
		String name,
		String email,
		String editToken,
		LocalDateTime submittedAt
		) {}
