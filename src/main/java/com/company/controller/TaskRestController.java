package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.TaskDTO;
import com.company.service.inter.TaskServiceInter;
import com.company.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.TaskCtrl.CTRL)
public class TaskRestController {

    private final TaskServiceInter taskServiceInter;

    public TaskRestController(TaskServiceInter taskServiceInter) {
        this.taskServiceInter = taskServiceInter;
    }

    @GetMapping("/task")
    public ResponseEntity<ResponseDTO> getTasks(){


        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createTask(TaskDTO task){
       TaskDTO taskDTO = taskServiceInter.save(task);

       return ResponseEntity.ok(ResponseDTO.of(taskDTO,"Successfully added"));
    }


}
