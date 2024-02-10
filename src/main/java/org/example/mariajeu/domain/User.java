package org.example.mariajeu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mariajeu.domain.community.Post;

import java.util.ArrayList;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column // User(1):Post(N)
    @OneToMany(mappedBy = "user")
    private ArrayList<Post> posts = new ArrayList<>();


    private String username;
    private String password;
}
