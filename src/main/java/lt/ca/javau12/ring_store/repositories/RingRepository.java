package lt.ca.javau12.ring_store.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau12.ring_store.entities.Ring;

@Repository
public interface RingRepository extends JpaRepository<Ring,Long> {
	List<Ring> findByUserId(Long userId);

}