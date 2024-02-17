package org.example.mariajeu.controller.homepageController;

import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.CommentDTO;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.example.mariajeu.service.homepageService.CommentLikesService;
import org.example.mariajeu.service.homepageService.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/FoodArticle/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentLikesService commentLikesService;
    private final UserRepository userRepository;

    // 특정 FoodArticle의 댓글 목록 조회
    @GetMapping("/{foodArticleId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long foodArticleId) {
        List<CommentDTO> comments = commentService.getCommentsByFoodArticle(foodArticleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 추가
    @PostMapping("{foodArticleId}")
    public ResponseEntity<?> addComment(@PathVariable Long foodArticleId,
                                        @RequestBody CommentDTO commentDto,
                                        Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Long userId = user.getId();

        CommentDTO savedComment = commentService.addComment(foodArticleId, commentDto, commentDto.getContent());
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    // 댓글 삭제
    @DeleteMapping("/{foodArticleId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long foodArticleId,
                                           @PathVariable Long commentId,
                                           Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        commentService.deleteComment(foodArticleId, commentId, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 좋아요 갯수 세기
    @GetMapping("/likes/count/{commentId}")
    public ResponseEntity<?> getLikesCount(@PathVariable Long commentId) {
        int likesCount = commentLikesService.getLikesCountByCommentId(commentId);
        return ResponseEntity.ok(Collections.singletonMap("likesCount", likesCount));
    }

    // 댓글에 좋아요 추가
    @PostMapping("/likes/{commentId}")
    public ResponseEntity<?> addLikeToComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Long userId = user.getId();

        commentLikesService.addLike(userId, commentId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 댓글에 좋아요 삭제
    @DeleteMapping("/likes/{commentId}")
    public ResponseEntity<?> removeLikeFromComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Long userId = user.getId();

        commentLikesService.removeLike(userId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
