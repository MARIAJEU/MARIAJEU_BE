package org.example.mariajeu.controller.homepageController;

import org.example.mariajeu.dto.homepageDto.RestaurantReviewDTO;
import org.example.mariajeu.service.homepageService.RestaurantReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class RestaurantReviewController {

    @Autowired
    private RestaurantReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<RestaurantReviewDTO>> getAllReviews() {
        List<RestaurantReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<RestaurantReviewDTO> getReviewById(@PathVariable Long reviewId) {
        RestaurantReviewDTO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/{userId}/{restaurantId}")
    public ResponseEntity<RestaurantReviewDTO> createReview(
            @PathVariable Long userId,
            @PathVariable Long restaurantId,
            @RequestBody RestaurantReviewDTO reviewDTO) {
        RestaurantReviewDTO createdReview = reviewService.createReview(userId, restaurantId, reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
