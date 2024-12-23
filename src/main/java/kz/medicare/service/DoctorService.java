// DoctorService.java
package kz.medicare.service;

import kz.medicare.entity.Doctor;
import kz.medicare.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentService appointmentService;


    public Doctor createDoctor(Doctor doctor) {

        doctor = doctorRepository.save(doctor);
        appointmentService.createAppointment(doctor.getId(), doctor.getAppointments().get(0));
        return doctor;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
