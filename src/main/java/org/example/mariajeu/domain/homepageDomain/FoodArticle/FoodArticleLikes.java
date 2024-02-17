package org.example.mariajeu.domain.homepageDomain.FoodArticle;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.mariajeu.domain.userDomain.User;

@Entity
@Getter
@Setter
@Table(name = "food_article_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "foodArticle_id"})
})
public class FoodArticleLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodArticle_id")
    private FoodArticle foodArticle;
}