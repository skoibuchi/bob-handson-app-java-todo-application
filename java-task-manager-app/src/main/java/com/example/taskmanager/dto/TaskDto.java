package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    
    @NotBlank(message = "タイトルは必須です")
    @Size(max = 200, message = "タイトルは200文字以内で入力してください")
    private String title;
    
    @Size(max = 5000, message = "説明は5000文字以内で入力してください")
    private String description;
    
    @NotBlank(message = "ステータスは必須です")
    private String status;
    
    @NotBlank(message = "優先度は必須です")
    private String priority;
    
    private LocalDate dueDate;
}

// Made with Bob
