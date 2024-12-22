package kz.medicare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
