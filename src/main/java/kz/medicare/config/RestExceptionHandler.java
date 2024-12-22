package kz.medicare.config;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import kz.medicare.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<ErrorResponse> handleEntityExistsException(EntityExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(401).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode().value()).body(new ErrorResponse(e.getMessage()));
    }
}
