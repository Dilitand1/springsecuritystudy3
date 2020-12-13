package ru.litvinov.springsecuritystudy2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.litvinov.springsecuritystudy2.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
