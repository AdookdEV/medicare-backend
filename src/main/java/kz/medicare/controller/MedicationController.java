package kz.medicare.controller;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import kz.medicare.dto.MedicationReminderDto;
import kz.medicare.entity.MedicationUnit;
import kz.medicare.repository.ScheduleDataRepository;
import kz.medicare.service.MedicationReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MedicationController {

    private final MedicationReminderService medicationReminderService;
    private final ScheduleDataRepository scheduleDataRepository;


    @PostMapping("/medication-reminder")
    public ResponseEntity<Map<String, Integer>> addReminder(@RequestBody MedicationReminderDto request) {
        return ResponseEntity.ok(medicationReminderService.addReminder(request));
    }

    @GetMapping("/medication-reminder")
    public ResponseEntity<List<MedicationReminderDto>> getReminders() {
        return ResponseEntity.ok(medicationReminderService.getReminders());
    }

    @GetMapping("/medication-reminder/today")
    public ResponseEntity<List<MedicationReminderDto>> getRemindersToday() {
        return ResponseEntity.ok(medicationReminderService.getRemindersToday());
    }

    @DeleteMapping("/medication-reminder/{id}")
    public ResponseEntity<Void> getReminders(@PathVariable Integer id) {
        medicationReminderService.deleteReminder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/medication-units")
    public ResponseEntity<List<MedicationUnit>> getMedicationUnits() {
        return ResponseEntity.ok(medicationReminderService.getMedicationUnits());
    }

    @PutMapping("/schedule-data/{id}")
    public ResponseEntity<Map<String, Integer>> updateNextDateTime(@PathVariable Integer id) {
        var scheduleData = scheduleDataRepository.findScheduleDataById(id).orElseThrow();


        var now =  ZonedDateTime.of(scheduleData.getNextDateTime(), ZoneId.systemDefault());

        var parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
        var executionTime = ExecutionTime.forCron(parser.parse(scheduleData.getCron()));
        var nextDateTime = executionTime.nextExecution(now).orElseThrow();

        scheduleData.setNextDateTime(nextDateTime.toLocalDateTime());

        scheduleDataRepository.save(scheduleData);
        return ResponseEntity.ok().build();
    }
}
