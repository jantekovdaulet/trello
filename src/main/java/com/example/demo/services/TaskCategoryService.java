package com.example.demo.services;


import com.example.demo.entities.TaskCategories;

import java.util.List;

public interface TaskCategoryService {

    TaskCategories getTaskCategory(Long id);
    List<TaskCategories> getAllTaskCategories();
    TaskCategories addTaskCategory(TaskCategories taskCategory);
    TaskCategories saveTaskCategory(TaskCategories taskCategory);
    void deleteTaskCategory(Long id);

}
