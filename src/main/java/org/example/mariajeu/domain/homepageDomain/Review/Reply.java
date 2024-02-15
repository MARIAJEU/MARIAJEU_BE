package org.example.mariajeu.domain.homepageDomain.Review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.mariajeu.configuration.BaseTimeEntity;
import org.example.mariajeu.domain.userDomain.User;

@NoArgsConstructor
@Entity
@Table(name = "review_reply")
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private RestaurantReview review;

    @Column(nullable = false)
    private String content;

    @Builder
    public Reply(User user, RestaurantReview review, String content) {
        this.user = user;
        this.review = review;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RestaurantReview getReview() {
        return review;
    }

    public String getContent() {
        return content;
    }
}
