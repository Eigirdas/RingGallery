package lt.ca.javau12.ring_store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ca.javau12.ring_store.entities.RingImage;

public interface RingImageRepository extends JpaRepository<RingImage,Long> {
	List<RingImage> findByRingId(Long ringId);

}