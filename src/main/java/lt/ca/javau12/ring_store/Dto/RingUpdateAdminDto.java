package lt.ca.javau12.ring_store.Dto;

import java.util.List;

public class RingUpdateAdminDto {
	private String name;
	private String description;
	private String metalType;
	private double size;
	private List<String> remainingImages;
	private Long userId;
	
	
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
	public List<String> getRemainingImages() {
		return remainingImages;
	}
	public void setRemainingImages(List<String> remainingImages) {
		this.remainingImages = remainingImages;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	

}
