package com.yunushamod.blog.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String username;
    @NotNull
    private String password;
    @OneToMany
    private List<Post> posts;

    public static User user1 = new User(null, "a@interswitchng.com", "abcdef", List.of());
    public static User user2 = new User(null, "b@interswitchng.com", "fedcba", List.of());
}
