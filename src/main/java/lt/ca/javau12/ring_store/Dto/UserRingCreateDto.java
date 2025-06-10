package lt.ca.javau12.ring_store.Dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
// tinka
public record UserRingCreateDto(
		@NotEmpty String name,
		@Email String email,
		@NotEmpty List<@Valid RingCreateDto> rings
	) {}