package org.example.mariajeu.repository.community;

import org.example.mariajeu.domain.community.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
