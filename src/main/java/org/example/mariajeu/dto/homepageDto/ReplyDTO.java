package org.example.mariajeu.dto.homepageDto;

import lombok.Builder;
import lombok.Data;
import org.example.mariajeu.domain.homepageDomain.Review.Reply;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReplyDTO {

    private Long id;
    private UserDTO user; // 유저 정보 추가
    private RestaurantReviewDTO review; // 리뷰 정보 추가
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReplyDTO fromEntity(Reply reply) {
        return ReplyDTO.builder()
                .id(reply.getId())
                .user(UserDTO.fromEntity(reply.getUser()))
                .review(RestaurantReviewDTO.fromEntity(reply.getReview()))
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    public static List<ReplyDTO> fromEntities(List<Reply> replies) {
        return replies.stream().map(ReplyDTO::fromEntity).collect(Collectors.toList());
    }
}
