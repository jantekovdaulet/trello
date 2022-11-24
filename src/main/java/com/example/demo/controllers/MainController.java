package com.example.demo.controllers;

import com.example.demo.entities.Comments;
import com.example.demo.entities.Folders;
import com.example.demo.entities.TaskCategories;
import com.example.demo.entities.Tasks;
import com.example.demo.services.CommentService;
import com.example.demo.services.FolderService;
import com.example.demo.services.TaskCategoryService;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private TaskCategoryService taskCategoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private Folders folder;

    @Autowired
    private Tasks task;

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        List<Folders> folders = folderService.getAllFolders();
        model.addAttribute("folders", folders);

        model.addAttribute("newFolder", folder);

        return "index";
    }

    @PostMapping(value = "/addfolder")
    public String addFolder(@ModelAttribute(name = "newFolder") Folders newFolder) {

        folderService.addFolder(newFolder);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String getDetails(Model model,
                             @PathVariable(name = "id") Long id) {

        Folders folder = folderService.getFolder(id);
        model.addAttribute("folder", folder);

        List<TaskCategories> categories = taskCategoryService.getAllTaskCategories();
        categories.removeAll(folder.getTaskCategories());
        model.addAttribute("taskCategories", categories);

        List<Tasks> tasks = taskService.getTasksByFolderId(id);
        model.addAttribute("tasks", tasks);

        model.addAttribute("newTask", task);

        return "details";
    }

    @PostMapping(value = "/addfoldertaskcategory")
    public String addTaskCategoryOfFolder(@RequestParam(name = "taskCategoryId") Long taskCategoryId,
                                          @RequestParam(name = "folderId") Long folderId) {

        TaskCategories taskCategory = taskCategoryService.getTaskCategory(taskCategoryId);
        if (taskCategory != null) {

            Folders folder = folderService.getFolder(folderId);
            if (folder != null) {

                List<TaskCategories> taskCategories = folder.getTaskCategories();
                taskCategories.add(taskCategory);
                folder.setTaskCategories(taskCategories);
                folderService.saveFolder(folder);

                return "redirect:/details/" + folderId;
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deletefoldertaskcategory")
    public String deleteTaskCategoryOfFolder(@RequestParam(name = "folderId") Long folderId,
                                             @RequestParam(name = "taskCategoryId") Long taskCategoryId) {

        TaskCategories taskCategory = taskCategoryService.getTaskCategory(taskCategoryId);
        if (taskCategory != null) {

            Folders folder = folderService.getFolder(folderId);
            if (folder != null) {

                List<TaskCategories> taskCategories = folder.getTaskCategories();
                taskCategories.remove(taskCategory);
                folder.setTaskCategories(taskCategories);
                folderService.saveFolder(folder);

                return "redirect:/details/" + folderId;
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/addfoldertask")
    public String addTaskOfFolder(@ModelAttribute(name = "newTask") Tasks newTask,
                                  @RequestParam(name = "folderId") Long folderId) {

        newTask.setFolder(folderService.getFolder(folderId));
        taskService.addTask(newTask);
        return "redirect:/details/" + newTask.getFolder().getId();
    }

    @PostMapping(value = "/deletefolder/{id}")
    public String deleteFolder(@PathVariable(name = "id") Long id) {

        List<Tasks> tasks = taskService.getTasksByFolderId(id);
        for (Tasks task1 : tasks) {
            commentService.deleteAllCommentByTaskId(task1.getId());
        }
        
        taskService.deleteTasksByFolderId(id);
        folderService.deleteFolder(id);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{folderId}/edittask/{taskId}")
    public String getEditTask(Model model,
                              @PathVariable(name = "taskId") Long taskId,
                              @PathVariable(name = "folderId") Long folderId) {

        Folders folder = folderService.getFolder(folderId);
        model.addAttribute("folder", folder);

        Tasks task = taskService.getTask(taskId);
        model.addAttribute("task", task);

        List<Comments> comments = commentService.getCommentsByTaskId(taskId);
        model.addAttribute("comments", comments);

        return "edittask";
    }

    @PostMapping(value = "/savetask")
    public String saveTask(@RequestParam(name = "taskId") Long taskId,
                           @RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description,
                           @RequestParam(name = "status") int status) {

        Tasks task = taskService.getTask(taskId);
        if (task != null) {

            task.setTitle(title);
            task.setDescription(description);
            task.setStatus(status);
            taskService.saveTask(task);

        }
        return "redirect:/details/" + task.getFolder().getId();
    }

    @PostMapping(value = "/deletetask")
    public String deleteTask(@RequestParam(name = "taskId") Long taskId,
                             @RequestParam(name = "folderId") Long folderId) {

        commentService.deleteAllCommentByTaskId(taskId);
        taskService.deleteTask(taskId);
        return "redirect:/details/" + folderId;
    }
}
