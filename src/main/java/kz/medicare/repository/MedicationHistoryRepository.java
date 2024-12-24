package kz.medicare.repository;

import kz.medicare.entity.MedicationHistory;
import kz.medicare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
MedicationHistoryRepository extends JpaRepository<MedicationHistory, Integer> {

    List<MedicationHistory> findByUser(User user);
}
