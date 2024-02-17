package org.example.mariajeu.service.homepageService;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.homepageDomain.Comment.Comment;
import org.example.mariajeu.domain.homepageDomain.Comment.CommentLikes;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.CommentDTO;
import org.example.mariajeu.repository.homepageRepository.CommentLikesRepository;
import org.example.mariajeu.repository.homepageRepository.CommentRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    // 특정 FoodArticle의 댓글 목록 조회
    @GetMapping("/{foodArticleId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long foodArticleId) {
        List<CommentDTO> comments = commentService.getCommentsByFoodArticle(foodArticleId);
        return ResponseEntity.ok(comments);
    }

    // 특정 댓글의 좋아요 수 반환
    public int getLikesCountByCommentId(Long commentId) {
        // CommentLikesRepository에서 특정 댓글 ID에 대한 좋아요 수 조회
        return commentLikesRepository.countByCommentId(commentId);
    }


    // 댓글에 좋아요 추가
    public void addLike(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 이미 좋아요가 있는지 확인
        if (commentLikesRepository.existsByUserAndComment(user, comment)) {
            throw new RuntimeException("Like already exists");
        }

        CommentLikes commentLikes = new CommentLikes();
        commentLikes.setUser(user);
        commentLikes.setComment(comment);
        commentLikesRepository.save(commentLikes);
    }

    // 댓글에 달린 좋아요 삭제
    public void removeLike(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // 좋아요 객체 찾기
        CommentLikes commentLikes = commentLikesRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        commentLikesRepository.delete(commentLikes);
    }

    // 특정 댓글에 대한 사용자의 좋아요 여부 확인
    public boolean isLikedByUser(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return commentLikesRepository.existsByUserAndComment(user, comment);
    }
}
