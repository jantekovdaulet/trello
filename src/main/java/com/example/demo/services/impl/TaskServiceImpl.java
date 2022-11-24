package com.example.demo.services.impl;

import com.example.demo.entities.Tasks;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Tasks getTask(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Tasks addTask(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public Tasks saveTask(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Tasks> getTasksByFolderId(Long id) {
        return taskRepository.findTasksByFolder_Id(id);
    }

    @Override
    public void deleteTasksByFolderId(Long id) {
        taskRepository.deleteAllByFolder_Id(id);
    }
}
