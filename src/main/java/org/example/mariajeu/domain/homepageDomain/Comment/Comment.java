package org.example.mariajeu.domain.homepageDomain.Comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.mariajeu.domain.homepageDomain.FoodArticle.FoodArticle;
import org.example.mariajeu.domain.userDomain.User;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@Entity
public class Comment {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodArticle_id")
    private FoodArticle foodArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @OneToMany(mappedBy = "comment")
    private Set<CommentLikes> likes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column
    private LocalDateTime createdAt; // 댓글 생성 시간

    public int getLikesCount() {
        return likes.size();
    }
}
