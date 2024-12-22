package kz.medicare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kz.medicare.enums.ScheduleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medication_reminds")
public class MedicationRemind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_type", length = 30)
    private ScheduleType scheduleType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false, referencedColumnName = "id")
    private MedicationUnit unit;

}