package kz.medicare.controller;

import kz.medicare.entity.Appointment;
import kz.medicare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/doctor/{doctorId}")
    public ResponseEntity<Appointment> createAppointment(
            @PathVariable Long doctorId,
            @RequestBody Appointment appointmentRequest) {
        try {
            Appointment appointment = appointmentService.createAppointment(doctorId, appointmentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
