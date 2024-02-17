package org.example.mariajeu.service.homepageService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.homepageDomain.Comment.Comment;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.CommentDTO;
import org.example.mariajeu.repository.homepageRepository.CommentRepository;
import org.example.mariajeu.repository.homepageRepository.FoodArticleRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final FoodArticleRepository foodArticleRepository;
    private final UserRepository userRepository;

    // 특정 FoodArticle의 모든 댓글 가져오기
    public List<CommentDTO> getCommentsByFoodArticle(Long foodArticleId) {
        // FoodArticle 존재 여부 확인
        FoodArticle foodArticle = foodArticleRepository.findById(foodArticleId)
                .orElseThrow(() -> new EntityNotFoundException("FoodArticle not found with id: " + foodArticleId));

        // 해당 FoodArticle의 모든 댓글 조회
        List<Comment> comments = commentRepository.findByFoodArticle(foodArticle);

        // 댓글들을 DTO로 변환
        return comments.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    // 댓글 추가
    public CommentDTO addComment(Long foodArticleId, CommentDTO commentDto, String content) {
        // FoodArticle과 User 찾기
        FoodArticle foodArticle = foodArticleRepository.findById(foodArticleId)
                .orElseThrow(() -> new EntityNotFoundException("FoodArticle not found with id: " + foodArticleId));
        User user = userRepository.findByUsername(commentDto.getUsername());

        // 새로운 Comment 객체 생성
        Comment newComment = new Comment();
        newComment.setFoodArticle(foodArticle);
        newComment.setUser(user);
        newComment.setContent(content);

        // Comment 저장
        Comment savedComment = commentRepository.save(newComment);

        // CommentDTO 변환 및 반환
        return convertToDto(savedComment);
    }
    // 댓글 삭제
    public void deleteComment(Long foodArticleId, Long commentId, String username) {
        // 댓글을 찾습니다.
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        // 댓글 작성자와 현재 사용자가 일치하는지 확인합니다.
        if (comment.getUser() == null || !comment.getUser().getUserName().equals(username)) {
            throw new AccessDeniedException("You do not have permission to delete this comment");
        }

        // 댓글 삭제
        commentRepository.deleteById(commentId);
    }


    // Comment 엔티티를 CommentDTO로 변환
    private CommentDTO convertToDto(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getUser().getUserName(),
                comment.getContent(),
                comment.getCreatedAt(), // 댓글 생성 시간 필드
                (comment.getLikes() != null) ? comment.getLikes().size() : 0
        );
    }
}