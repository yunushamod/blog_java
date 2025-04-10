package com.yunushamod.blog.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String username;
    @NotNull
    private String password;
    @OneToMany
    private List<Post> posts;

    public static User user1 = new User(1L, "a@interswitchng.com", "abcdef", List.of());
    public static User user2 = new User(2L, "b@interswitchng.com", "fedcba", List.of());
}
