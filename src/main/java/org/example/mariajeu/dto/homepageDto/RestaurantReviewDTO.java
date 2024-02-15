package org.example.mariajeu.dto.homepageDto;

import lombok.Builder;
import lombok.Data;
import org.example.mariajeu.domain.homepageDomain.Review.RestaurantReview;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReviewDTO {

    private Long id;
    private UserDTO user; // 유저 정보 추가
    private String content;
    private int likes;
    private List<ReplyDTO> replies;
    private String photoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewDTO fromEntity(RestaurantReview review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .user(UserDTO.fromEntity(review.getUser()))
                .content(review.getContent())
                .likes(review.getLikes())
                .replies(ReplyDTO.fromEntities(review.getReplys()))
                .photoUrl(review.getPhotoUrl())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
