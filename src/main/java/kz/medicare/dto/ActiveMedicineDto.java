package kz.medicare.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveMedicineDto {
    private LocalDateTime date;
    private String medicineName;
    private Integer dose;

}
