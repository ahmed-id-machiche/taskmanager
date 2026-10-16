package com.leetjourny.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data // geters and setters
@NoArgsConstructor // create Constructers
@AllArgsConstructor
public class Task {
    private long id;
    private String title;
    private String description;
    private boolean completedAt;
    private LocalDateTime createdAt;
}
