package com.example.todo.dto.mapper;

import com.example.todo.dto.TaskDto;
import com.example.todo.model.Task;

public class TaskMapper {

    public static TaskDto toTaskDto(Task task) {
        return new TaskDto()
                .setTitle(task.getTitle())
                .setDescription(task.getDescription());
    }
}
