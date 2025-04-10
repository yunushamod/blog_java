package com.yunushamod.blog.helpers;

import com.yunushamod.blog.models.User;
import com.yunushamod.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0){
            userRepository.saveAll(List.of(User.user1, User.user2));
        }
    }
}
