package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticleLikes;
import org.example.mariajeu.domain.userDomain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodArticleLikesRepository extends JpaRepository<FoodArticleLikes,Long> {
    List<FoodArticleLikes> findByFoodArticleId(Long foodArticleId);
    void deleteByUserAndFoodArticle(User user, FoodArticle foodArticle);

    // 특정 유저가 특정 FoodArticle에 대한 좋아요를 눌렀는지 확인
    boolean existsByUserAndFoodArticle(User user, FoodArticle foodArticle);

    // 특정 유저가 특정 FoodArticle에 누른 좋아요 찾기
    Optional<FoodArticleLikes> findByUserAndFoodArticle(User user, FoodArticle foodArticle);
}
