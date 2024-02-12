package org.example.mariajeu.domain.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('POST_LIKES_NOTICE','POST_COMMENT_NOTICE','COMMNENT_RECOMMENT_NOTICE','FOLLOW_REQUEST_NOTICE','FOLLOW_ALLOWED_NOTICE')")
    private NotificationType notificationType;
    //     public void fs() {
    //        System.out.printf("NotificationType.POST_LIKES_NOTICE");
    //    }

    @JoinColumn(name = "follow_id")
    @ManyToOne
    private Follow followId;

    @JoinColumn(name = "likes_id")
    @ManyToOne
    private Likes likes_id;

    @JoinColumn(name = "comment_id")
    @ManyToOne
    private Comment comment_id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post postId;

}
