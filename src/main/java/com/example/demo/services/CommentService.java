package com.example.demo.services;


import com.example.demo.entities.Comments;

import java.util.List;

public interface CommentService {

    Comments getComment(Long id);
    List<Comments> getAllComments();
    Comments addComment(Comments comment);
    Comments saveComment(Comments comment);
    void deleteComment(Long id);
    void deleteAllCommentByTaskId(Long id);
    List<Comments> getCommentsByTaskId(Long id);
}
