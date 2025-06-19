package lt.ca.javau12.ring_store.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lt.ca.javau12.ring_store.Dto.UserCreateDto;
import lt.ca.javau12.ring_store.Dto.UserDto;
import lt.ca.javau12.ring_store.Dto.UserUpdateDto;
import lt.ca.javau12.ring_store.services.UserManagementService;
import lt.ca.javau12.ring_store.services.UserService;

@RestController
@RequestMapping("admin/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserController {
	private final UserService userService;
	private final UserManagementService userManagementService;
	
	public AdminUserController(UserService userService,UserManagementService userManagementService) {
		this.userService = userService;
		this.userManagementService = userManagementService;
	}
	
	@GetMapping
	public List<UserDto> getAll(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
		
	}
	
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody UserCreateDto dto) {
    	UserDto created = userService.registerNewUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
	

	
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserUpdateDto dto, @PathVariable Long id) {
        UserDto updated = userService.editUser(id, dto);
        return ResponseEntity.ok(updated);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		// handle the delete of the user and set rings to null
		userManagementService.deleteUserLeaveRings(id);
		return ResponseEntity.noContent().build();
	}
	
	

}