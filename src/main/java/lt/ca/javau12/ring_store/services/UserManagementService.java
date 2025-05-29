package lt.ca.javau12.ring_store.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.ca.javau12.ring_store.repositories.RingRepository;

@Service
public class UserManagementService {
	private final UserService userService;
	private final RingRepository ringRepository;
	
	public UserManagementService(UserService userService,RingRepository ringRepository) {
		this.userService = userService;
		this.ringRepository = ringRepository;
	}
	
	@Transactional
	public void deleteUserLeaveRings(Long userId) {
		if(userService.getUserById(userId) != null) {
			throw new RuntimeException("User not found");
		}
		
		// set nulls to each ring that belongs to the user
		var rings = ringRepository
				.findAll()
				.stream()
				.filter(r -> r.getUser() != null && r.getUser()
				.getId()
				.equals(userId))
				.toList();
		rings.forEach(r -> r.setUser(null));
		ringRepository.saveAll(rings);
		
		userService.deleteUser(userId);
		
	}

}
