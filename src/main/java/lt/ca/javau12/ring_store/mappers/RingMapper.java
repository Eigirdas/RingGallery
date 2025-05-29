
package lt.ca.javau12.ring_store.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import lt.ca.javau12.ring_store.Dto.RingCreateDto;
import lt.ca.javau12.ring_store.Dto.RingDto;
import lt.ca.javau12.ring_store.entities.Ring;
import lt.ca.javau12.ring_store.entities.User;

@Component
public class RingMapper {

    private final UserMapper userMapper;

    public RingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RingDto toDto(Ring ring) {
        List<String> images = ring
                .getImages()
                .stream()
                .map(image -> "/images/" + image.getId()) 
                .toList();

        return new RingDto(
            ring.getId(),
            ring.getName(),
            ring.getDescription(),
            ring.getMetalType(),
            ring.getSize(),
            images,
            ring.getUser() != null ? ring.getUser().getId() : null
        );
    }
    
   

    public Ring toEntity(RingCreateDto dto, User user) {
        Ring ring = new Ring();
        ring.setName(dto.getName());
        ring.setDescription(dto.getDescription());
        ring.setMetalType(dto.getMetalType());
        ring.setSize(dto.getSize());
        ring.setUser(user);
        return ring;
    }
    
    public Ring toEntity(RingDto dto, User user) {
        Ring ring = new Ring();
        ring.setName(dto.getName());
        ring.setDescription(dto.getDescription());
        ring.setMetalType(dto.getMetalType());
        ring.setSize(dto.getSize());
        ring.setUser(user);
        return ring;
    }
    
    
}