package org.example.mariajeu.domain.homepageDomain.FoodArticle;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.mariajeu.domain.homepageDomain.Comment.Comment;
import org.example.mariajeu.domain.homepageDomain.Food;
import org.example.mariajeu.domain.homepageDomain.Wine;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodArticle {
    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodArticle_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    private Wine wine;


    @OneToMany(mappedBy = "foodArticle")
    private Set<Comment> comments;

    @Column
    @OneToMany(mappedBy = "foodArticle")
    private Set<FoodArticleLikes> likes;

    @ColumnDefault("0")
    @Column(name="views",nullable = false)
    private int views;

    @Builder
    public FoodArticle(Food food, Wine wine) {
        this.food = food;
        this.wine = wine;
    }
    public int getLikesCount() {
        return likes.size();
    }
}