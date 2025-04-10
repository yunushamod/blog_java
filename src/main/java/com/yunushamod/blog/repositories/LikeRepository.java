package com.yunushamod.blog.repositories;

import com.yunushamod.blog.models.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
}
