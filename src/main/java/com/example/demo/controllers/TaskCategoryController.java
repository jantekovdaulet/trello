package com.example.demo.controllers;

import com.example.demo.entities.TaskCategories;
import com.example.demo.services.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping(value = "/categories")
    public String getCategories(Model model) {

        List<TaskCategories> categories = taskCategoryService.getAllTaskCategories();
        model.addAttribute("taskCategories", categories);
        return "categories";
    }

    @PostMapping(value = "/addcategory")
    public String addCategory(@RequestParam(name = "categoryName") String categoryName) {

        TaskCategories category = new TaskCategories();
        category.setName(categoryName);
        taskCategoryService.addTaskCategory(category);
        return "redirect:/categories";
    }

    @PostMapping(value = "/deletecategory")
    public String deleteCategory(@RequestParam(name = "categoryId") Long categoryId) {

        taskCategoryService.deleteTaskCategory(categoryId);
        return "redirect:/categories";
    }

    @PostMapping(value = "/savecategory")
    public String saveCategory(@RequestParam(name = "categoryName") String categoryName,
                               @RequestParam(name = "categoryId") Long categoryId) {

        TaskCategories category = taskCategoryService.getTaskCategory(categoryId);
        if (category != null) {

            category.setName(categoryName);
            taskCategoryService.saveTaskCategory(category);

        }
        return "redirect:/categories";
    }

    @GetMapping(value = "/editcategory/{id}")
    public String getEditCategory(Model model,
                                  @PathVariable(name = "id") Long id){

        TaskCategories taskCategory = taskCategoryService.getTaskCategory(id);
        model.addAttribute("cat", taskCategory);
        return "editcategory";
    }

}
