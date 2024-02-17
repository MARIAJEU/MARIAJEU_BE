package org.example.mariajeu.domain.homepageDomain.Comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.mariajeu.domain.userDomain.User;

@Entity
@Getter
@Setter
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

