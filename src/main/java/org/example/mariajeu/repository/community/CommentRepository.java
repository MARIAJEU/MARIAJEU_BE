package org.example.mariajeu.repository.community;

import org.example.mariajeu.domain.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
