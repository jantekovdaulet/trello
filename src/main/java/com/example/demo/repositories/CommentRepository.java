package com.example.demo.repositories;

import com.example.demo.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comments, Long> {
    void deleteAllByTaskId(Long id);

    List<Comments> getCommentsByTaskId(Long id);
}
