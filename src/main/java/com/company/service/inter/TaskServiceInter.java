package com.company.service.inter;

import com.company.dto.TaskDTO;
import com.company.dto.TaskDetailDTO;
import com.company.dto.TaskUpdateDTO;

import java.util.List;

public interface TaskServiceInter {

    TaskDTO save(TaskDTO task);

    TaskDetailDTO getById(Integer id);

    public List<TaskDetailDTO> getAll();

    Boolean delete(Integer id);

    TaskDetailDTO update(Integer id, TaskUpdateDTO task);
}
