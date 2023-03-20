package com.example.todo.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskRequest {
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String title;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private String description;
}
