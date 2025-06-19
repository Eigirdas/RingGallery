package lt.ca.javau12.ring_store.Dto;


import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lt.ca.javau12.ring_store.security.models.Role;

public class UserUpdateDto {
	@NotBlank(message = "Name is required")
	private String name;
	

	private String password;
	
	@Email(message = "Email should be valid")
	private String email;
	private Role role;
	
	
	public UserUpdateDto() {
	}
	
	
	
	public UserUpdateDto(String name, String password, String email, Role role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
