package lt.ca.javau12.ring_store.security.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.repositories.UserRepository;
import lt.ca.javau12.ring_store.security.models.Role;


@Configuration
public class InitConfiguration {
	
	private final UserRepository userRepository;
	
	
	public InitConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	//TODO: REMOVE BEFORE PRODUCTION00
	@Bean
	CommandLineRunner init(PasswordEncoder encoder) {
	    return args -> {
	        if(userRepository.findByUsername("alice").isEmpty()) {
	            User user = new User(
	                "alice", 
	                encoder.encode("pass1234"),
	                "alice@mail.com", 
	                Role.ADMIN,
	                true
	            );
	            userRepository.save(user);
	        }
	    };
	}

}
