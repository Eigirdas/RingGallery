package lt.ca.javau12.ring_store.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.User;
import lt.ca.javau12.ring_store.repositories.RingRepository;
import lt.ca.javau12.ring_store.repositories.UserRepository;

@Service
public class UserManagementService {
	private final UserService userService;
	private final RingRepository ringRepository;
	private final UserRepository userRepository;
	private static final long DUMMY_USER_ID = 1L;
	
	public UserManagementService(UserService userService,RingRepository ringRepository,UserRepository userRepository) {
		this.userService = userService;
		this.ringRepository = ringRepository;
		this.userRepository = userRepository;
	}
	
	// Saving the rings to DUMMY if Proper User was deleted
	
	@Transactional
	public void deleteUserLeaveRings(Long userId) {
	    // Only load rings belonging to this user
	    List<Ring> userRings = ringRepository.findAll().stream()
	        .filter(r -> r.getUser() != null && r.getUser().getId().equals(userId))
	        .toList();

	    if (!userRings.isEmpty()) {
	        User dummyUser = userRepository.findById(DUMMY_USER_ID)
	            .orElseThrow(() -> new IllegalStateException("Dummy user not found"));

	        userRings.forEach(r -> r.setUser(dummyUser));
	        ringRepository.flush();
	    }

	    userService.deleteUser(userId);
	}

}
