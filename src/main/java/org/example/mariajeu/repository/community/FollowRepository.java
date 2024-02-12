package org.example.mariajeu.repository.community;

import org.example.mariajeu.domain.community.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
