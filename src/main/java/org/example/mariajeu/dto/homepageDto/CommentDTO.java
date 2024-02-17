package org.example.mariajeu.dto.homepageDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private Long id; // 댓글 ID
    private Long userId; // 댓글을 작성한 사용자 ID
    private String username; // 댓글을 작성한 사용자 이름
    private String content; // 댓글 내용
    private LocalDateTime createdAt; // 댓글 생성 시간
    private int likesCount; // 댓글의 좋아요 수

    // 기본 생성자
    public CommentDTO() {
    }

    // 매개변수를 받는 생성자
    public CommentDTO(Long id, Long userId, String username, String content, LocalDateTime createdAt, int likesCount) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
    }
}