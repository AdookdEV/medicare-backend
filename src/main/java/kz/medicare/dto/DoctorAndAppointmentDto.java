package kz.medicare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorAndAppointmentDto {
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String speciality;
    private String email;
    private String phone;
    private List<Appointment> appointments;



    @Getter
    @Setter
    public static class Appointment {
        private String date;
        private String time;
    }


}
