package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.TaskDTO;
import com.company.dto.TaskDetailDTO;
import com.company.dto.TaskUpdateDTO;
import com.company.service.inter.ITaskService;
import com.company.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TaskCtrl.CTRL)
public class TaskRestController {

    private final ITaskService taskService;

    public TaskRestController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getTasks() {
        List<TaskDetailDTO> list = taskService.getAll();
        return ResponseEntity.ok(ResponseDTO.of(list));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createTask(@RequestBody TaskDTO task) {
        TaskDTO taskDTO = taskService.save(task);

        return ResponseEntity.ok(ResponseDTO.of(taskDTO, "Successfully added"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTask(@PathVariable(value = "id", required = true) Integer id, @RequestBody TaskUpdateDTO taskDto) {
        TaskDetailDTO detailDTO = taskService.update(id, taskDto);
        return ResponseEntity.ok(ResponseDTO.of(detailDTO, "Successfully updated"));
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<ResponseDTO> finishTask(@PathVariable(value = "id", required = true) Integer id) {
        TaskDetailDTO detailDTO = taskService.finishTask(id);
        return ResponseEntity.ok(ResponseDTO.of(detailDTO, "The task was completed successfully"));
    }

}
