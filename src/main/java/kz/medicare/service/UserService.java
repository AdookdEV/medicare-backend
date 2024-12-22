package kz.medicare.service;

import jakarta.persistence.EntityExistsException;
import kz.medicare.dto.LoginDto;
import kz.medicare.dto.LoginResponseDto;
import kz.medicare.dto.RegistrationDto;
import kz.medicare.entity.User;
import kz.medicare.repository.UserRepository;
import kz.medicare.security.enums.Role;
import kz.medicare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public void registerPatient(RegistrationDto registrationDto) {
        userRepository.findByEmail(registrationDto.getEmail()).ifPresent(user -> {
            throw new EntityExistsException("User already exists");
        });

        var user = new User();
        user.setBlocked(false);
        user.setFirstname(registrationDto.getFirstName());
        user.setLastname(registrationDto.getLastName());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setEmail(registrationDto.getEmail());
        user.setRole(Role.PATIENT);

        userRepository.save(user);
    }
    
    public LoginResponseDto login(LoginDto loginDto) {
        var userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
        if (!passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String accessToken = jwtUtil.generateAccessToken(loginDto.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(loginDto.getEmail());

        return new LoginResponseDto(accessToken, refreshToken);
    }

    public User getUserFromSecurityContext() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
