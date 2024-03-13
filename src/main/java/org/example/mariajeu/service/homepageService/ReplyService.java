package org.example.mariajeu.service.homepageService;

import org.example.mariajeu.domain.homepageDomain.Review.Reply;
import org.example.mariajeu.domain.homepageDomain.Review.RestaurantReview;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.ReplyDTO;
import org.example.mariajeu.repository.homepageRepository.ReplyRepository;
import org.example.mariajeu.repository.homepageRepository.RestaurantReviewRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private RestaurantReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReplyDTO> getRepliesByReviewId(Long reviewId) {
        List<Reply> replies = replyRepository.findByReviewId(reviewId);
        return replies.stream()
                .map(ReplyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ReplyDTO createReply(Long reviewId, ReplyDTO replyDTO) {
        RestaurantReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found with id: " + reviewId));

        User user = userRepository.findById(replyDTO.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + replyDTO.getUser().getId()));

        Reply reply = Reply.builder()
                .user(user)
                .review(review)
                .content(replyDTO.getContent())
                .build();

        Reply savedReply = replyRepository.save(reply);
        return ReplyDTO.fromEntity(savedReply);
    }

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
