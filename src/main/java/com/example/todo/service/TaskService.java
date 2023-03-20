package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.model.Task;
import java.util.List;

public interface TaskService {
    public TaskDto createTask(TaskDto taskDto);

    public List<TaskDto> fetchTasks();

    public TaskDto updateTask(String taskId, TaskDto taskDto);


    public TaskDto fetchTaskById(String taskId);




}
