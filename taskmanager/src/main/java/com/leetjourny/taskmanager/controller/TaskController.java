package com.leetjourny.taskmanager.controller;

import com.leetjourny.taskmanager.entity.Task;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController //@Controller and @ResponseBody "every method return data not use data "
@RequestMapping("/api/v1/tasks") //base path of all endpoints

public class TaskController {

    //Temporary in-memory task list
    private List <Task> tasks = new ArrayList<>();
    private Long nextId =  1L;
    @GetMapping List<Task> getAllTasks(){
        return tasks;
    }
    @PostMapping
    public Task createTask(@RequestBody Task task){
           task.setId(nextId++);
           task.setCreatedAt(LocalDateTime.now());
           task.setCompletedAt(false);
           tasks.add(task);
           return task;

    }
}


