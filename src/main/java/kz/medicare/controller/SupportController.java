package kz.medicare.controller;

import jakarta.validation.Valid;
import kz.medicare.dto.DoctorAndAppointmentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class SupportController {


    @PostMapping
    public void addDoctorWithAppointment(@RequestBody @Valid DoctorAndAppointmentDto request) {

    }

    @DeleteMapping("/{id}")
    public void deleteDoctorWithAppointment(@PathVariable Integer id) {

    }
}
