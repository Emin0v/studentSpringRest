package com.company.service.inter;

import com.company.dto.TaskDTO;
import com.company.dto.TaskDetailDTO;
import com.company.dto.TaskUpdateDTO;

import java.util.List;

public interface ITaskService {

    TaskDTO save(TaskDTO task);

    TaskDetailDTO finishTask(Integer id);

    TaskDetailDTO getById(Integer id);

    List<TaskDetailDTO> getAll();

    Boolean delete(Integer id);

    TaskDetailDTO update(Integer id, TaskUpdateDTO task);
}
