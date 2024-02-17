package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Comment.Comment;
import org.example.mariajeu.domain.homepageDomain.Comment.CommentLikes;
import org.example.mariajeu.domain.userDomain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    // 특정 사용자와 댓글에 해당하는 좋아요 객체 찾기
    Optional<CommentLikes> findByUserAndComment(User user, Comment comment);

    // 특정 사용자와 댓글에 좋아요가 존재하는지 확인
    boolean existsByUserAndComment(User user, Comment comment);

    int countByCommentId(Long commentId);
}