// AppointmentService.java
package kz.medicare.service;

import kz.medicare.entity.Appointment;
import kz.medicare.entity.Doctor;
import kz.medicare.repository.AppointmentRepository;
import kz.medicare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Appointment createAppointment(Long doctorId, Appointment appointment) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            appointment.setDoctor(doctor);
            return appointmentRepository.save(appointment);
        }
        return null; // or throw exception
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
