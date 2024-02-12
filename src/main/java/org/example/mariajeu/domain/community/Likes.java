package org.example.mariajeu.domain.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.userDomain.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_unique_key",
                        columnNames = {"from_user_id","to_post_id"}
                )
        }
)
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "to_post_id")
    @ManyToOne
    private Post toPost;

    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt(){ this.createdAt = LocalDateTime.now();}
}
