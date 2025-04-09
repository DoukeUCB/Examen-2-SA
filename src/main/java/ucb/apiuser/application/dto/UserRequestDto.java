package ucb.apiuser.application.dto;

import java.time.LocalDate;

public class UserRequestDto {
    private String name;
    private String lastname;
    private String phoneNumber;
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
