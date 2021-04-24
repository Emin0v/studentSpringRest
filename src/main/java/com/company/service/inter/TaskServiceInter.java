package com.company.service.inter;

import com.company.dto.TaskDTO;

public interface TaskServiceInter {

    TaskDTO save(TaskDTO task);

    TaskDTO getById(Integer id);

    Boolean delete(Integer id);

    TaskDTO update(TaskDTO task);
}
