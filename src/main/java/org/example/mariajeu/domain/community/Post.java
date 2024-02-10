package org.example.mariajeu.domain.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Post(N):User(1)
    @JoinColumn(name = "author", nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // Post(1):Comment(N)
    @JoinColumn(name = "post_id", nullable = true)
    @OneToMany
    @Builder.Default
    private ArrayList<Comment> comments = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    public void dateTime() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }



}
