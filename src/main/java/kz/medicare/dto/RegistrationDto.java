package kz.medicare.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
