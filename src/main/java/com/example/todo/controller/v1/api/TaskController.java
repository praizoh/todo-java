package com.example.todo.controller.v1.api;

import com.example.todo.controller.v1.request.CreateTaskRequest;
import com.example.todo.dto.TaskDto;
import com.example.todo.dto.mapper.TaskMapper;
import com.example.todo.dto.response.Response;
import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * Handles the incoming POST API "/v1/tasks"
     *
     * @param createTaskRequest
     * @return
     */
    @PostMapping("/tasks")
    public Response createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
       TaskDto taskDto = new TaskDto()
                .setTitle(createTaskRequest.getTitle())
                .setDescription(createTaskRequest.getDescription());
        TaskDto saveTask = taskService.createTask(taskDto);
        return Response.ok().setPayload(saveTask);
    }

    @GetMapping("/tasks")
    public  Response fetchTasks(){
        TreeSet<TaskMapper> tasks = taskService.fetchTasks();
        return Response.ok().setPayload(tasks);
    }
}
