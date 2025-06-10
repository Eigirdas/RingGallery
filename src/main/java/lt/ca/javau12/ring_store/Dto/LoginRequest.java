package lt.ca.javau12.ring_store.Dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty String username,@NotEmpty String password) {
	

}
