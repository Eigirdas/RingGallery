
package lt.ca.javau12.ring_store.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lt.ca.javau12.ring_store.security.models.Role;

public class UserDto {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	private String email;

	private Role role;
	
	public UserDto() {
	}
	
	public UserDto(Long id,String name,String email,Role role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
	
}
