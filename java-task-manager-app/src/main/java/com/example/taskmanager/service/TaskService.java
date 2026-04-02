package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;

import java.util.List;

public interface TaskService {
    
    List<Task> findAll();
    
    Task findById(Long id);
    
    Task save(TaskDto taskDto);
    
    Task update(Long id, TaskDto taskDto);
    
    void deleteById(Long id);
}

// Made with Bob
