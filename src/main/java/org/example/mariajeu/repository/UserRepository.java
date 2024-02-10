package org.example.mariajeu.repository;

import org.example.mariajeu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
