package com.company.controller;

import com.company.dto.TaskDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {

    @GetMapping("/task")
    public TaskDTO getTasks(){


        return null;
    }

    @PostMapping("/add/task")
    public void createTask(TaskDTO task){



    }


}
