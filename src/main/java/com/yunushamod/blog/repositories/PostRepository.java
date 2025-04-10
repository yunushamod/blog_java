package com.yunushamod.blog.repositories;

import com.yunushamod.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
