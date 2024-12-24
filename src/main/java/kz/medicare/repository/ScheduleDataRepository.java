package kz.medicare.repository;

import jakarta.validation.constraints.NotNull;
import kz.medicare.entity.MedicationRemind;
import kz.medicare.entity.ScheduleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ScheduleDataRepository extends JpaRepository<ScheduleData, Long> {

    List<ScheduleData> findScheduleDataByReminder(@NotNull MedicationRemind reminder);

    void deleteByReminder(@NotNull MedicationRemind reminder);

    List<ScheduleData> findScheduleDataByReminderIn(Collection<MedicationRemind> reminders);

     Optional<ScheduleData> findScheduleDataById(Integer id);
}
