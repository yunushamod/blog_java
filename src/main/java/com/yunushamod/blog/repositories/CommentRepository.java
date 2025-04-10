package com.yunushamod.blog.repositories;

import com.yunushamod.blog.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
