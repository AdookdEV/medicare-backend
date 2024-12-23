package kz.medicare.controller;

import kz.medicare.dto.ActiveMedicineDto;
import kz.medicare.entity.ScheduleData;
import kz.medicare.repository.MedicationRemindRepository;
import kz.medicare.repository.ScheduleDataRepository;
import kz.medicare.service.UserService;
import kz.medicare.utils.CronUtilsHelper;
import kz.medicare.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/today")
public class ActiveMedicineController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ScheduleDataRepository scheduleDataRepository;
    private final MedicationRemindRepository medicationRemindRepository;


    public ActiveMedicineController(JwtUtil jwtUtil, UserService userService, ScheduleDataRepository scheduleDataRepository, MedicationRemindRepository medicationRemindRepository) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.scheduleDataRepository = scheduleDataRepository;
        this.medicationRemindRepository = medicationRemindRepository;
    }

    @GetMapping("/medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<ActiveMedicineDto> getActiveMedicines(Principal principal) {
        var user = userService.getUserFromSecurityContext();
        var reminders = medicationRemindRepository.findMedicationRemindsByPatient(user);
        List<ScheduleData> schedules = scheduleDataRepository.findScheduleDataByReminders(reminders);

//        schedules.stream()
//                .filter((sc) -> CronUtilsHelper.matchesCronExpression(sc.getCron()))
//                .map(sc -> ActiveMedicineDto
//                        .builder()
//                        .medicineName()
//                        .dose(sc.getDose())
//                        .date()
//                )

        return List.of();

    }
}
