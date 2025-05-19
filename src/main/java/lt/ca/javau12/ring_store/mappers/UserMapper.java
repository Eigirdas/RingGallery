package lt.ca.javau12.ring_store.mappers;

import org.springframework.stereotype.Component;

import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.entities.User;

@Component
public class UserMapper {
	private final RingMapper ringMapper;
	
	public UserMapper(RingMapper ringMapper) {
		this.ringMapper = ringMapper;
	}
	
	public UserDto toDto(User user) {
		return new UserDto(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getEditToken(),
				user.getCreatedAt(),
				user.getRing() != null ? ringMapper.toDto(user.getRing()) : null
				);
	}
	
	public User toEntity(UserDto dto) {
		User user = new User();
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setCreatedAt(dto.submittedAt());
		return user;
	}

}
