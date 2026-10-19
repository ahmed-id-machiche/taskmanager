package com.leetjourny.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long message) {
        super(message);
    }
}
