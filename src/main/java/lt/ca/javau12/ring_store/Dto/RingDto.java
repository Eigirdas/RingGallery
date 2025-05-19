package lt.ca.javau12.ring_store.Dto;

import java.time.LocalDateTime;
import java.util.List;


public record RingDto(Long id, String description, String name,String metalType,double Size,List<String> imageUrls,String submittedByName,LocalDateTime createdAt) {

}
