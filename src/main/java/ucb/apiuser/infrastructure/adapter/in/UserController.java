package ucb.apiuser.infrastructure.adapter.in;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucb.apiuser.application.dto.UserRequestDto;
import ucb.apiuser.application.dto.UserResponseDto;
import ucb.apiuser.domain.model.User;
import ucb.apiuser.domain.port.in.UserUseCase;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserUseCase userUseCase;
    
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }
    
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = mapToEntity(userRequestDto);
        User createdUser = userUseCase.createUser(user);
        return new ResponseEntity<>(mapToResponseDto(createdUser), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userUseCase.getUserById(id);
        return ResponseEntity.ok(mapToResponseDto(user));
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userUseCase.getAllUsers();
        List<UserResponseDto> userDtos = users.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        User user = mapToEntity(userRequestDto);
        User updatedUser = userUseCase.updateUser(id, user);
        return ResponseEntity.ok(mapToResponseDto(updatedUser));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    private User mapToEntity(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBornDate(dto.getBornDate());
        return user;
    }
    
    private UserResponseDto mapToResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getBornDate()
        );
    }
}
