package org.example.mariajeu.domain.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.User;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_id")
    private Long post_id;

    @JoinColumn(name = "author", nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    public void dateTime() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }



}
