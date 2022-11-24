package com.example.demo.controllers;

import com.example.demo.entities.Comments;
import com.example.demo.entities.Tasks;
import com.example.demo.services.CommentService;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/addcomment")
    public String addComment(@RequestParam(name = "textComment") String textComment,
                             @RequestParam(name = "taskId") Long taskId) {

        Tasks task = taskService.getTask(taskId);
        if (task != null) {

            Comments comment = new Comments();
            comment.setComment(textComment);
            comment.setTask(task);
            commentService.saveComment(comment);

        }

        return "redirect:/details/" + task.getFolder().getId() + "/edittask/" + taskId;
    }

    @PostMapping(value = "deletecomment/{id}")
    public String deleteComment(@PathVariable(name = "id") Long commentId,
                                @RequestParam(name = "taskId") Long taskId){

        commentService.deleteComment(commentId);
        return "redirect:/details/" + taskService.getTask(taskId).getFolder().getId() + "/edittask/" + taskId;
    }

}
