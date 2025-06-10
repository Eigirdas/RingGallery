package lt.ca.javau12.ring_store.mappers;

import org.springframework.stereotype.Component;


import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.entities.User;

@Component
public class UserMapper {
	
	public UserDto toDto(User user) {
		return new UserDto(
				user.getId(),
				user.getUsername(),
				user.getEmail()
				);
	}
	
	public User toEntity(UserDto dto) {
		User user = new User();
		user.setUsername(dto.getName());
		user.setEmail(dto.getEmail());
		return user;
	}

}