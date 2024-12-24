package kz.medicare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "medication_history")
public class MedicationHistory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 20)
    @NotNull
    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @NotNull
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Size(max = 100)
    @NotNull
    @Column(name = "medication_title", nullable = false, length = 100)
    private String medicationTitle;

}