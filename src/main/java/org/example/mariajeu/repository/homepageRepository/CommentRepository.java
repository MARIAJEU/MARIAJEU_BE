package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Comment.Comment;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByFoodArticleId(Long foodArticleId);

    List<Comment> findByFoodArticle(FoodArticle foodArticle);
}