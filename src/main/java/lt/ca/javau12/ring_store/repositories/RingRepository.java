package lt.ca.javau12.ring_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import lt.ca.javau12.ring_store.entities.Ring;

@Repository
public interface RingRepository extends JpaRepository<Ring,Long> {

}
