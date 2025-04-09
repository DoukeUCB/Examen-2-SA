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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuario", description = "API para la gestión de usuarios")
public class UserController {
    
    private final UserUseCase userUseCase;
    
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }
    
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un usuario con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = mapToEntity(userRequestDto);
        User createdUser = userUseCase.createUser(user);
        return new ResponseEntity<>(mapToResponseDto(createdUser), HttpStatus.CREATED);
    }
    
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(
            @Parameter(description = "ID del usuario", required = true) @PathVariable Long id) {
        User user = userUseCase.getUserById(id);
        return ResponseEntity.ok(mapToResponseDto(user));
    }
    
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios recuperada exitosamente")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userUseCase.getAllUsers();
        List<UserResponseDto> userDtos = users.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
    
    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @Parameter(description = "ID del usuario", required = true) @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto) {
        User user = mapToEntity(userRequestDto);
        User updatedUser = userUseCase.updateUser(id, user);
        return ResponseEntity.ok(mapToResponseDto(updatedUser));
    }
    
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario", required = true) @PathVariable Long id) {
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
