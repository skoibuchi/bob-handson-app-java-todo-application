package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.TaskPriority;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    @GetMapping
    public String listTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        
        // ステータスごとにタスクをグループ化
        Map<TaskStatus, List<Task>> tasksByStatus = tasks.stream()
            .collect(Collectors.groupingBy(Task::getStatus));
        
        model.addAttribute("tasksByStatus", tasksByStatus);
        model.addAttribute("statuses", TaskStatus.values());
        return "task/list";
    }
    
    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task/detail";
    }
    
    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("taskDto", new TaskDto());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("priorities", TaskPriority.values());
        return "task/create";
    }
    
    @PostMapping
    public String createTask(@Valid @ModelAttribute TaskDto taskDto,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("priorities", TaskPriority.values());
            return "task/create";
        }
        
        taskService.save(taskDto);
        redirectAttributes.addFlashAttribute("message", "タスクを作成しました");
        return "redirect:/tasks";
    }
    
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus().name());
        taskDto.setPriority(task.getPriority().name());
        taskDto.setDueDate(task.getDueDate());
        
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("taskId", id);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("priorities", TaskPriority.values());
        return "task/edit";
    }
    
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id,
                           @Valid @ModelAttribute TaskDto taskDto,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("taskId", id);
            model.addAttribute("statuses", TaskStatus.values());
            model.addAttribute("priorities", TaskPriority.values());
            return "task/edit";
        }
        
        taskService.update(id, taskDto);
        redirectAttributes.addFlashAttribute("message", "タスクを更新しました");
        return "redirect:/tasks/" + id;
    }
    
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "タスクを削除しました");
        return "redirect:/tasks";
    }
}

// Made with Bob
