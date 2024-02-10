package org.example.mariajeu.repository.community;

import org.example.mariajeu.domain.community.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
