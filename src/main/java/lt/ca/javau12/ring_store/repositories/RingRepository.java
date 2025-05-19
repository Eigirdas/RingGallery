package lt.ca.javau12.ring_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau12.ring_store.entities.Ring;

@Repository
public interface RingRepository extends JpaRepository<Ring,Long> {

}
