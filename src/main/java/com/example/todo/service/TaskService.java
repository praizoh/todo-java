package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.dto.mapper.TaskMapper;

import java.util.TreeSet;

public interface TaskService {
    public TaskDto createTask(TaskDto taskDto);

    public TreeSet<TaskMapper> fetchTasks();

//    public TaskDto updateTask(String taskId, TaskDto taskDto);


    public TaskDto fetchTaskById(String taskId);




}
