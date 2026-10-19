package com.leetjourny.taskmanager.repository;

import com.leetjourny.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Spring data will automaticly create a class
//a bean is automaticly been created
//a Bean is an object that is created , managed and wired together by Spring IoC container
// spring is gonna create a singleton which is a design pattern
//where onmyone instance of a class exist and is shared throught the app

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Method name Query
    //Select * Fromtasks where completed
    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

      // for complex queries
    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> FindTasksByCompletionStatus(@Param("completed") boolean completed);




}
