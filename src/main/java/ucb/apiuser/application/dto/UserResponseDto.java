package ucb.apiuser.application.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del usuario")
public class UserResponseDto {
    
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;
    
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String name;
    
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String lastname;
    
    @Schema(description = "Número de teléfono del usuario", example = "+591 77712345")
    private String phoneNumber;
    
    @Schema(description = "Fecha de nacimiento del usuario", example = "1990-01-01")
    private LocalDate bornDate;

    public UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String lastname, String phoneNumber, LocalDate bornDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }
}
