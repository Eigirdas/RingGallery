package lt.ca.javau12.ring_store.services;

import java.util.List;


import org.springframework.stereotype.Service;

import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.mappers.UserMapper;
import lt.ca.javau12.ring_store.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserService(UserRepository userRepository,UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	public List<UserDto> getAllUsers(){
		return userRepository
				.findAll()
				.stream()
				.map(e -> userMapper.toDto(e))
				.toList();
	}
	
	public UserDto getUserById(Long id){
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
		return userMapper.toDto(user);
	}
	
	public UserDto createUser(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		User savedUser = userRepository.save(user);
		return userMapper.toDto(savedUser);
	}
	
	public UserDto editUser(Long id,UserDto dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		
		User updatedUser = userRepository.save(user);
		return userMapper.toDto(updatedUser);
	}
	
	
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
		
		// if user is deleted, all the rings will be deleted as well because of Cascade (CascadeType.all)
		userRepository.delete(user);
	}
	

}
