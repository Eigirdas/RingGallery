package lt.ca.javau12.ring_store.entities;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ring {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String name;
	private String metalType;
	private double size;
	private String editToken;
	
	@OneToMany(mappedBy = "ring", cascade = CascadeType.ALL, orphanRemoval = true) // is ring is deleted images deleted
	private List<RingImage> images = new ArrayList<>();
	
	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = true) // if user is deleted, rings should be kept
	 private User user;
	
	public Ring() {
		editToken = UUID.randomUUID().toString();
	}

	public Ring(Long id, String description, String name, String metalType, double size, List<RingImage> images) {
		this.id = id;
		this.description = description;
		this.name = name;
		this.metalType = metalType;
		this.size = size;
		this.images = images;
	}
	
	

	public String getEditToken() {
		return editToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<RingImage> getImages() {
		return images;
	}

	public void setImages(List<RingImage> images) {
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	



	
	
	
	
	
}