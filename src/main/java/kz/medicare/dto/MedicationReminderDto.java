package kz.medicare.dto;

import kz.medicare.enums.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicationReminderDto {
    private ScheduleType scheduleType;
    private List<Entry> entries;
    private String title;
    private Integer unitId;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Entry {
        private String cron;
        private Integer dose;
    }
}
