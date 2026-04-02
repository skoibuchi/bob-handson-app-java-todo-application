package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // 作成日時の降順でタスクを取得
    List<Task> findAllByOrderByCreatedAtDesc();
    
    // ステータスでタスクを検索
    List<Task> findByStatus(TaskStatus status);
}

// Made with Bob
