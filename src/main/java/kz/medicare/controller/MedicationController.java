package kz.medicare.controller;

import kz.medicare.dto.MedicationReminderDto;
import kz.medicare.entity.MedicationUnit;
import kz.medicare.service.MedicationReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MedicationController {

    private final MedicationReminderService medicationReminderService;

    @PostMapping("/medication-reminder")
    public ResponseEntity<Map<String, Integer>> addReminder(@RequestBody MedicationReminderDto request) {
        return ResponseEntity.ok(medicationReminderService.addReminder(request));
    }

    @GetMapping("/medication-reminder")
    public ResponseEntity<List<MedicationReminderDto>> getReminders() {
        return ResponseEntity.ok(medicationReminderService.getReminders());
    }

    @GetMapping("/medication-units")
    public ResponseEntity<List<MedicationUnit>> getMedicationUnits() {
        return ResponseEntity.ok(medicationReminderService.getMedicationUnits());
    }
}
