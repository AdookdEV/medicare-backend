package kz.medicare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "schedule_data")
public class ScheduleData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reminder_id", nullable = false)
    private MedicationRemind reminder;

    @Size(max = 50)
    @Column(name = "cron", length = 50)
    private String cron;

    @NotNull
    @Column(name = "dose", nullable = false)
    private Integer dose;

    @Column(nullable = false)
    private LocalDateTime nextDateTime;

}