package lt.ca.javau12.ring_store.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserCreateDto{
	
	@NotEmpty
	private String username;
	
	@Email
	@NotEmpty
	private  String email;
	
	@NotEmpty
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String firstPassword;
	
	@NotEmpty
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String secondPassword;
	
	public UserCreateDto(String username,String email,String firstPassword,String secondPassword) {
		this.username = username;
		this.email = email;
		this.firstPassword = firstPassword;
		this.secondPassword = secondPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getFirstPassword() {
		return firstPassword;
	}

	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	
	
	
	
}

