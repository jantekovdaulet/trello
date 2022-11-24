package com.example.demo.services.impl;

import com.example.demo.entities.Comments;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comments getComment(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comments addComment(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comments saveComment(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllCommentByTaskId(Long id) {
        commentRepository.deleteAllByTaskId(id);
    }

    @Override
    public List<Comments> getCommentsByTaskId(Long id) {
        return commentRepository.getCommentsByTaskId(id);
    }
}
