package com.yunushamod.blog.repositories;

import com.yunushamod.blog.models.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(@NotNull String username);
}
