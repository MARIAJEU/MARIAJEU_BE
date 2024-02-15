package org.example.mariajeu.domain.homepageDomain.Review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.mariajeu.configuration.BaseTimeEntity;
import org.example.mariajeu.domain.homepageDomain.restaurantDomain.Restaurant;
import org.example.mariajeu.domain.userDomain.User;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "review")
public class RestaurantReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int likes;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replys = new ArrayList<>();

    @Column
    private String photoUrl;

    @Builder
    public RestaurantReview(User user, Restaurant restaurant, String content, String photoUrl) {
        this.user = user;
        this.restaurant = restaurant;
        this.content = content;
        this.photoUrl = photoUrl;
        this.likes = 0;
    }

    public void addLike() {
        this.likes++;
    }

    public void addReply(Reply reply) {
        this.replys.add(reply);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public List<Reply> getReplys() {
        return replys;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
