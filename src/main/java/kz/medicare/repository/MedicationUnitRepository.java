package kz.medicare.repository;

import kz.medicare.entity.MedicationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationUnitRepository extends JpaRepository<MedicationUnit, Integer> {
}
