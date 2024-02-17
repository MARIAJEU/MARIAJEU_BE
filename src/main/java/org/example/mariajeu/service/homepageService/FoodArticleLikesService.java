package org.example.mariajeu.service.homepageService;

import jakarta.transaction.Transactional;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticleLikes;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.repository.homepageRepository.FoodArticleLikesRepository;
import org.example.mariajeu.repository.homepageRepository.FoodArticleRepository;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodArticleLikesService {

    private final FoodArticleLikesRepository foodArticleLikesRepository;
    private final FoodArticleRepository foodArticleRepository;
    private final UserRepository userRepository;

    @Autowired
    public FoodArticleLikesService(FoodArticleLikesRepository foodArticleLikesRepository, FoodArticleRepository foodArticleRepository, UserRepository userRepository) {
        this.foodArticleLikesRepository = foodArticleLikesRepository;
        this.foodArticleRepository = foodArticleRepository;
        this.userRepository = userRepository;
    }

    // 좋아요 추가
    public void addLike(Long userId, Long foodArticleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FoodArticle foodArticle = foodArticleRepository.findById(foodArticleId)
                .orElseThrow(() -> new RuntimeException("FoodArticle not found"));

        // 이미 좋아요를 눌렀는지 확인
        boolean alreadyLiked = foodArticleLikesRepository.existsByUserAndFoodArticle(user, foodArticle);
        if (!alreadyLiked) {
            FoodArticleLikes foodArticleLikes = new FoodArticleLikes();
            foodArticleLikes.setUser(user);
            foodArticleLikes.setFoodArticle(foodArticle);
            foodArticleLikesRepository.save(foodArticleLikes);
        }
    }

    // 좋아요 삭제
    @Transactional
    public void removeLike(Long userId, Long foodArticleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        FoodArticle foodArticle = foodArticleRepository.findById(foodArticleId)
                .orElseThrow(() -> new RuntimeException("FoodArticle not found"));

        // 좋아요 존재 여부 확인 후 삭제
        FoodArticleLikes like = foodArticleLikesRepository.findByUserAndFoodArticle(user, foodArticle)
                .orElseThrow(() -> new RuntimeException("Like not found"));
        foodArticleLikesRepository.delete(like);
    }
}