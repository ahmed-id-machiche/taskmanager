package com.leetjourny.taskmanager.service;

import com.leetjourny.taskmanager.entity.Task;
import com.leetjourny.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service //mark this class a service component
@Transactional // make sure the method done succesfuly if not it does a RollBack
public class TaskService {
 private final TaskRepository taskRepository;// l'acces a db

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();

    }

    public Optional<Task> getTaskById(Long id) {

        return taskRepository.findById(id);

    }


    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    public Optional<ResponseEntity<Task>> updateTask(Long id, Task updateTask) {
        return  taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(updateTask.getCompleted());
                    task.setTitle(updateTask.getTitle());
                    task.setDescription(updateTask.getDescription());
                    Task savedTask = taskRepository.save(task);
                    return ResponseEntity.ok(savedTask);
                });

    }

    public boolean deleteTask(Long id){
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }

    public List<Task>getCompletedTasksByStatus(boolean status) {
        return taskRepository.findByCompleted(status);
    }

    public List<Task> searchTaskByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}
