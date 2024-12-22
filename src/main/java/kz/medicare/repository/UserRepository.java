package kz.medicare.repository;

import kz.medicare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);
}
