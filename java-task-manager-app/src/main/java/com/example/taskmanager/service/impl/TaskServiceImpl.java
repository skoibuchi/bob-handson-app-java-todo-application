package com.example.taskmanager.service.impl;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("タスクが見つかりません: ID=" + id));
    }
    
    @Override
    public Task save(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        task.setPriority(TaskPriority.valueOf(taskDto.getPriority()));
        task.setDueDate(taskDto.getDueDate());
        
        return taskRepository.save(task);
    }
    
    @Override
    public Task update(Long id, TaskDto taskDto) {
        Task task = findById(id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
        task.setPriority(TaskPriority.valueOf(taskDto.getPriority()));
        task.setDueDate(taskDto.getDueDate());
        
        return taskRepository.save(task);
    }
    
    @Override
    public void deleteById(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}

// Made with Bob
