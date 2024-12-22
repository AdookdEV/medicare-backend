package kz.medicare.repository;

import jakarta.validation.constraints.NotNull;
import kz.medicare.entity.MedicationRemind;
import kz.medicare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRemindRepository extends JpaRepository<MedicationRemind, Integer> {

    List<MedicationRemind> findMedicationRemindsByPatient(@NotNull User patient);
}
