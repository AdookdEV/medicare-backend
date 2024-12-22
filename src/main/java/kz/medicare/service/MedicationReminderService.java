package kz.medicare.service;

import kz.medicare.dto.MedicationReminderDto;
import kz.medicare.entity.MedicationRemind;
import kz.medicare.entity.MedicationUnit;
import kz.medicare.entity.ScheduleData;
import kz.medicare.entity.User;
import kz.medicare.enums.ScheduleType;
import kz.medicare.repository.MedicationRemindRepository;
import kz.medicare.repository.MedicationUnitRepository;
import kz.medicare.repository.ScheduleDataRepository;
import kz.medicare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static kz.medicare.dto.MedicationReminderDto.*;


@Service
@RequiredArgsConstructor
public class MedicationReminderService {
    private final MedicationRemindRepository medicationRemindRepository;
    private final ScheduleDataRepository scheduleDataRepository;
    private final MedicationUnitRepository medicationUnitRepository;
    private final UserService userService;


    @Transactional
    public Map<String, Integer> addReminder(MedicationReminderDto dto) {
        User patient = userService.getUserFromSecurityContext();

        MedicationRemind medicationRemind = new MedicationRemind();
        medicationRemind.setPatient(patient);
        medicationRemind.setTitle(dto.getTitle());
        medicationRemind.setScheduleType(dto.getScheduleType());
        medicationRemind.setUnit(medicationUnitRepository
                .findById(dto.getUnitId())
                .orElseThrow()
        );

        var reminder = medicationRemindRepository.save(medicationRemind);

        for (Entry entry : dto.getEntries()) {
            ScheduleData scheduleData = new ScheduleData();
            scheduleData.setReminder(reminder);
            scheduleData.setDose(entry.getDose());
            scheduleData.setCron(entry.getCron());
            scheduleDataRepository.save(scheduleData);
            if (!dto.getScheduleType().equals(ScheduleType.MULTIPLE_TIMES_DAY)) {
                break;
            }
        }

        return Map.of("id", reminder.getId());
    }


    public List<MedicationReminderDto> getReminders() {
        User patient = userService.getUserFromSecurityContext();

        var reminds = medicationRemindRepository.findMedicationRemindsByPatient(patient);

        return reminds.stream().map(r -> {
            var dto = new MedicationReminderDto();
            dto.setId(r.getId());
            dto.setTitle(r.getTitle());
            dto.setScheduleType(r.getScheduleType());
            dto.setUnitId(r.getUnit().getId());
            var entries = new ArrayList<Entry>();

            scheduleDataRepository
                    .findScheduleDataByReminder(r)
                    .forEach(sd -> {
                        entries.add(new Entry(sd.getCron(), sd.getDose()));
                    });

            dto.setEntries(entries);
            return dto;
        }).toList();
    }


    public List<MedicationUnit> getMedicationUnits() {
        return medicationUnitRepository.findAll();
    }

    @Transactional
    public void deleteReminder(Integer id) {
        var remind = medicationRemindRepository.findById(id);
        if (remind.isPresent()) {
            var remindEntity = remind.get();
            scheduleDataRepository.deleteByReminder(remindEntity);
            medicationRemindRepository.deleteById(id);
        }
    }
}
