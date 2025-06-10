package lt.ca.javau12.ring_store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau12.ring_store.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User>findByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);
	

}