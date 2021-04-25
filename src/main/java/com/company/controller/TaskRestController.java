package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.TaskDTO;
import com.company.dto.TaskDetailDTO;
import com.company.dto.TaskUpdateDTO;
import com.company.service.inter.TaskServiceInter;
import com.company.util.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TaskCtrl.CTRL)
public class TaskRestController {

    private final TaskServiceInter taskServiceInter;

    public TaskRestController(TaskServiceInter taskServiceInter) {
        this.taskServiceInter = taskServiceInter;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getTasks() {
        List<TaskDetailDTO> list = taskServiceInter.getAll();
        return ResponseEntity.ok(ResponseDTO.of(list));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> createTask(@RequestBody TaskDTO task) {
        TaskDTO taskDTO = taskServiceInter.save(task);

        return ResponseEntity.ok(ResponseDTO.of(taskDTO, "Successfully added"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateTask(@PathVariable(value = "id", required = true) Integer id, @RequestBody TaskUpdateDTO taskDto) {
        TaskDetailDTO detailDTO = taskServiceInter.update(id, taskDto);
        return ResponseEntity.ok(ResponseDTO.of(detailDTO, "Successfully updated"));
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<ResponseDTO> finishTask(@PathVariable(value = "id", required = true) Integer id) {
        //....

        return null;
    }

}
