package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.dto.mapper.TaskMapper;
import com.example.todo.exception.TodoException;
import com.example.todo.exception.EntityType;
import com.example.todo.exception.ExceptionType;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.modelmapper.ModelMapper;


import static com.example.todo.exception.EntityType.TASK;
import static com.example.todo.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.example.todo.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task =taskRepository.findByTitle(taskDto.getTitle());
        if (task == null) {
            task = new Task()
                    .setTitle(taskDto.getTitle())
                    .setDescription(taskDto.getDescription());
            return TaskMapper.toTaskDto(taskRepository.save(task));
        }
        throw exception(TASK, DUPLICATE_ENTITY, taskDto.getTitle());
    }

    @Override
    public TreeSet<TaskMapper> fetchTasks() {
        return StreamSupport
                .stream(taskRepository.findAll().spliterator(), false)
                .map(stop -> modelMapper.map(stop, TaskMapper.class))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public TaskDto fetchTaskById(String id){
        Optional<Task> task = Optional.ofNullable(taskRepository.findByTitle(id));
        if (task.isPresent()) {
            return modelMapper.map(task.get(), TaskDto.class);
        }
        throw exception(TASK, ENTITY_NOT_FOUND, id);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return TodoException.throwException(entityType, exceptionType, args);
    }
}
