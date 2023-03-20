package com.example.todo.repository;

import com.example.todo.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByTitle(String title);
}
