package lt.ca.javau12.ring_store.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.User;

@Component
public class RingMapper {

    public RingDto toDto(Ring ring) {
        List<String> images = ring
                .getImages()
                .stream()
                .map(image -> "/images/" + image.getId()) 
                .collect(Collectors.toList());             
        
        return new RingDto(
        	    ring.getName(),
        	    ring.getDescription(),
        	    ring.getMetalType(),
        	    ring.getSize(),
        	    ring.getUser().getName(),
        	    ring.getCreatedAt(),
        	    images
        	);
    }
    
    public Ring toEntity(RingDto dto,User user) {
    	Ring ring = new Ring();
    	ring.setName(dto.name());
    	ring.setDescription(dto.description());
    	ring.setMetalType(dto.metalType());
    	ring.setSize(dto.size());
    	ring.setUser(user);
    	return ring;
    }
}
