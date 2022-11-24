package com.example.demo.services.impl;

import com.example.demo.entities.TaskCategories;
import com.example.demo.repositories.TaskCategoryRepository;
import com.example.demo.services.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public TaskCategories getTaskCategory(Long id) {
        return taskCategoryRepository.getOne(id);
    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public TaskCategories addTaskCategory(TaskCategories taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public TaskCategories saveTaskCategory(TaskCategories taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategory(Long id) {
        taskCategoryRepository.deleteById(id);
    }
}
