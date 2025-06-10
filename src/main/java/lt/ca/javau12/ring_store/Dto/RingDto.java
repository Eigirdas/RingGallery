package lt.ca.javau12.ring_store.Dto;

import java.util.List;

public class RingDto {
    private Long id;
    private String name;
    private String description;
    private String metalType;
    private double size;
    private List<String> images;
    private Long userId;

    public RingDto(Long id, String name, String description, String metalType, double size, List<String> images, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.metalType = metalType;
        this.size = size;
        this.images = images;
        this.userId = userId;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetalType() {
		return metalType;
	}

	public void setMetalType(String metalType) {
		this.metalType = metalType;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

    
}