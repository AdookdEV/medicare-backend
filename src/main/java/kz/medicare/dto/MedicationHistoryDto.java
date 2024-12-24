package kz.medicare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicationHistoryDto {
    private Integer medicationId;
    private String state;
    private LocalDateTime date;
}
