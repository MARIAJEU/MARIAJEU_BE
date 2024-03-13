package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Review.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Long> {
    List<RestaurantReview> findByUserId(Long userId);
}
