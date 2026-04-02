package com.example.taskmanager.enums;

public enum TaskPriority {
    HIGH("高"),
    MEDIUM("中"),
    LOW("低");
    
    private final String displayName;
    
    TaskPriority(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

// Made with Bob
