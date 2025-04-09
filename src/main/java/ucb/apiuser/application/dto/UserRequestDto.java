package ucb.apiuser.application.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos para la creación o actualización de un usuario")
public class UserRequestDto {
    
    @Schema(description = "Nombre del usuario", example = "Juan")
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @Schema(description = "Apellido del usuario", example = "Pérez")
    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;
    
    @Schema(description = "Número de teléfono del usuario", example = "+591 77712345")
    @Pattern(regexp = "^\\+?[0-9\\s]+$", message = "El formato del número de teléfono no es válido")
    private String phoneNumber;
    
    @Schema(description = "Fecha de nacimiento del usuario", example = "1990-01-01")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate bornDate;

    public UserRequestDto() {
    }

    public UserRequestDto(String name, String lastname, String phoneNumber, LocalDate bornDate) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
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
