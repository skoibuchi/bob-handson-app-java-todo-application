package com.example.taskmanager.enums;

public enum TaskStatus {
    TODO("未着手"),
    IN_PROGRESS("進行中"),
    ON_HOLD("保留"),
    DONE("完了");
    
    private final String displayName;
    
    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

// Made with Bob
