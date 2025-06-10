package lt.ca.javau12.ring_store.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserCreateDto{
	@NotEmpty private String username;
	@Email @NotEmpty private  String email;
	@NotEmpty private String firstPassword;
	@NotEmpty private String secondPassword;
	
	public UserCreateDto(@NotEmpty String username, @Email @NotEmpty String email, @NotEmpty String firstPassword,@NotEmpty String secondPassword) {
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

