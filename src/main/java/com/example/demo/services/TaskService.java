package com.example.demo.services;


import com.example.demo.entities.Tasks;

import java.util.List;

public interface TaskService {

    Tasks getTask(Long id);
    List<Tasks> getAllTasks();
    Tasks addTask(Tasks task);
    Tasks saveTask(Tasks task);
    void deleteTask(Long id);
    void deleteTasksByFolderId(Long id);
    List<Tasks> getTasksByFolderId(Long id);

}
