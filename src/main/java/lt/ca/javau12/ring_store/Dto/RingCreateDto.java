package lt.ca.javau12.ring_store.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class RingCreateDto {

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private String metalType;

    @Positive
    private double size;

    public RingCreateDto() {}

    public RingCreateDto(String name, String description, String metalType, double size) {
        this.name = name;
        this.description = description;
        this.metalType = metalType;
        this.size = size;
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
    
    
}