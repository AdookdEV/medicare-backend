package kz.medicare.controller;

import jakarta.validation.Valid;
import kz.medicare.dto.MedicationHistoryDto;
import kz.medicare.entity.MedicationHistory;
import kz.medicare.repository.MedicationHistoryRepository;
import kz.medicare.repository.MedicationRemindRepository;
import kz.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medication-history")
public class MedicationHistoryController {

    private final MedicationHistoryRepository medicationHistoryRepository;
    private final UserService userService;
    private final MedicationRemindRepository medicationRemindRepository;

    @PostMapping
    public ResponseEntity<Void> createMedicationHistory(@Valid @RequestBody MedicationHistoryDto dto) {
        // Save the new MedicationHistory entity to the database
        var user = userService.getUserFromSecurityContext();
        var medic = medicationRemindRepository.findById(dto.getMedicationId()).orElseThrow();
        var entity = new MedicationHistory();
        entity.setTime(dto.getDate());
        entity.setState(dto.getState());
        entity.setUser(user);
        entity.setMedicationTitle(medic.getTitle());

        MedicationHistory savedMedicationHistory = medicationHistoryRepository.save(entity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<MedicationHistory>> getAllMedicationHistory() {
        return ResponseEntity.ok(medicationHistoryRepository.findByUser(userService.getUserFromSecurityContext()));
    }
}
