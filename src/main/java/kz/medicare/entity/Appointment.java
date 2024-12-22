
package kz.medicare.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDate;
    private String note;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
