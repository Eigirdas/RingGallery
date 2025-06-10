package lt.ca.javau12.ring_store.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lt.ca.javau12.ring_store.Dto.LoginResponse;
import lt.ca.javau12.ring_store.Dto.UserCreateDto;
import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.mappers.UserMapper;
import lt.ca.javau12.ring_store.repositories.UserRepository;
import lt.ca.javau12.ring_store.security.JwtUtils;
import lt.ca.javau12.ring_store.security.models.Role;
import lt.ca.javau12.ring_store.security.services.MyUserDetailsService;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final AuthenticationManager authenticationManager;
	private final MyUserDetailsService userDetailsService;
	private final JwtUtils jwtUtils;
	
	public UserService(UserRepository userRepository,UserMapper userMapper,PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager,MyUserDetailsService userDetailsService,JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
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
		user.setUsername(dto.getName());
		user.setEmail(dto.getEmail());
		User savedUser = userRepository.save(user);
		return userMapper.toDto(savedUser);
	}
	
	public UserDto editUser(Long id,UserDto dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
		
		user.setUsername(dto.getName());
		user.setEmail(dto.getEmail());
		
		User updatedUser = userRepository.save(user);
		return userMapper.toDto(updatedUser);
	}
	
	
	public void deleteUser(Long id) {

		userRepository.deleteById(id);
	}

	public UserDto registerNewUser(@Valid UserCreateDto dto) {
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Email already in use");
			
		}
		
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new RuntimeException("Username already in use");
		}
		
		if(!dto.getFirstPassword().equals(dto.getSecondPassword())) {
			throw new RuntimeException("Passwords dont match");
		}
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getFirstPassword()));
		user.setRole(Role.USER);
		user.setEnabled(true);
		User savedUser = userRepository.save(user);
		return userMapper.toDto(savedUser);
	}

	public LoginResponse authenticateUser(@NotEmpty String username, @NotEmpty String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String jwt = jwtUtils.generateToken(userDetails.getUsername());
		return new LoginResponse(jwt);
	}




	

}