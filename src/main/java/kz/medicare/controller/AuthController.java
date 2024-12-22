package kz.medicare.controller;


import jakarta.validation.Valid;
import kz.medicare.dto.LoginDto;
import kz.medicare.dto.LoginResponseDto;
import kz.medicare.dto.RegistrationDto;
import kz.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationDto request) {
        log.info("Registering user: {}", request);
        userService.registerPatient(request);
        return ResponseEntity.ok().build();
    }
}
