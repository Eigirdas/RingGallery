package lt.ca.javau12.ring_store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class RingImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;
	
	// Vienas ziedas gali turet daug nuotrauku, bet viena nuotrauka priklauso vienam ziedui
	@ManyToOne
	@JoinColumn(name = "ring_id")
	private Ring ring;
	
	

	public RingImage() {
	}
	
	

	public RingImage(byte[] image, Ring ring) {
		this.image = image;
		this.ring = ring;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Ring getRing() {
		return ring;
	}

	public void setRing(Ring ring) {
		this.ring = ring;
	}
	
	
	
}
