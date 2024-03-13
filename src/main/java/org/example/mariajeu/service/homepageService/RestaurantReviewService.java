package org.example.mariajeu.service.homepageService;

import org.example.mariajeu.domain.homepageDomain.Review.RestaurantReview;
import org.example.mariajeu.domain.homepageDomain.restaurantDomain.Restaurant;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.RestaurantReviewDTO;
import org.example.mariajeu.repository.homepageRepository.restaurantRepository.RestaurantRepository;
import org.example.mariajeu.repository.homepageRepository.RestaurantReviewRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantReviewService {

    @Autowired
    private RestaurantReviewRepository reviewRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    public List<RestaurantReviewDTO> getAllReviews() {
        List<RestaurantReview> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(RestaurantReviewDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RestaurantReviewDTO getReviewById(Long reviewId) {
        RestaurantReview review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found with id: " + reviewId));
        return RestaurantReviewDTO.fromEntity(review);
    }

    public RestaurantReviewDTO createReview(Long userId, Long restaurantId, RestaurantReviewDTO reviewDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with id: " + restaurantId));

        RestaurantReview review = RestaurantReview.builder()
                .user(user)
                .restaurant(restaurant)
                .content(reviewDTO.getContent())
                .photoUrl(reviewDTO.getPhotoUrl())
                .build();

        RestaurantReview savedReview = reviewRepository.save(review);
        return RestaurantReviewDTO.fromEntity(savedReview);
    }

    public void deleteReview(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found with id: " + reviewId, e);
        }
    }

}
