package com.leetjourny.taskmanager.controller;

import com.leetjourny.taskmanager.entity.Task;

import com.leetjourny.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController //@Controller and @ResponseBody "every method return data not use data "
@RequestMapping("/api/v1/tasks") //base path of all endpoints

public class TaskController {
    private final TaskService taskService;

    //Constructor Injection
    //@Autowired // Spring managing auoto
    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping
     public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}") //get endpoint
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) { // PathVariable will extract the ids
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
      return taskService.updateTask(id,updatedTask)
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build()).getBody();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

   @GetMapping("/completed/{status}")
    public List<Task>getTaskByCompletions(@PathVariable boolean status) {
        return taskService.getCompletedTasksByStatus(status);
   }

   @GetMapping("/search")
    public List<Task> searchTaskByTitle(@RequestParam String title) {
        return taskService.searchTaskByTitle(title);
   }


    /*
    *200 ok
    *201 Created
    * 404 Not Found - Ressource doesnt exist
    * 400 bad request - invalid data
    *
    *
     */
}




