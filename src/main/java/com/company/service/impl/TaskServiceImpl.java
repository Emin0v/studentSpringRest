package com.company.service.impl;

import com.company.dto.TaskDTO;
import com.company.dto.TaskDetailDTO;
import com.company.dto.TaskUpdateDTO;
import com.company.entity.StudentRank;
import com.company.entity.Task;
import com.company.entity.TaskStatus;
import com.company.entity.User;
import com.company.repository.StudentRankRepository;
import com.company.repository.TaskRepository;
import com.company.repository.UserRepository;
import com.company.service.inter.IAuthenticationFacade;
import com.company.service.inter.ITaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final IAuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final StudentRankRepository studentRankRepository;
    private final ModelMapper modelMapper;


    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        Authentication authentication = authenticationFacade.getAuthentication();
        Optional<User> user = userRepository.findByUsername(authentication.getName());

        int rank = studentRankRepository.findByStudentId(taskDTO.getStudent_id()).getRank();
        if (rank == 0) {
            throw new IllegalArgumentException("this user received rank of 0 he cannot be assigned any tasks");
        }
        taskDTO.setStatus(TaskStatus.PENDING);

        LocalDate deadline = LocalDate.now().plusDays(10);
        taskDTO.setDeadline(deadline);

        Task task = modelMapper.map(taskDTO, Task.class);

        task.setAssignedBy(user.get());
        User assignedTo = userRepository.getOne(taskDTO.getStudent_id());
        task.setAssignedTo(assignedTo);
        task = taskRepository.save(task);

        taskDTO.setId(task.getId());
        return taskDTO;
    }

    @Override
    public TaskDetailDTO getById(Integer id) {
        Task task = taskRepository.getOne(id);
        return modelMapper.map(task, TaskDetailDTO.class);
    }

    @Override
    public List<TaskDetailDTO> getAll() {
        User user = authenticationFacade.getCurrentUser();
        List<Task> data = taskRepository.findAllByAssignedToOrAssignedBy(user,user);
        return Arrays.asList(modelMapper.map(data, TaskDetailDTO[].class));
    }

    @Override
    public Boolean delete(Integer id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public TaskDetailDTO update(Integer id, TaskUpdateDTO task) {
        Task taskDb = taskRepository.getOne(id);

        User assignedBy = userRepository.getOne(task.getAssignedBy());
        User assignedTo = userRepository.getOne(task.getAssignedTo());

        taskDb.setAssignedTo(assignedTo);
        taskDb.setAssignedBy(assignedBy);
        taskDb.setId(task.getId());
        taskDb.setContent(task.getContent());
        taskDb.setDeadline(task.getDeadline());
        taskDb.setStatus(task.getStatus());

        Task updatedTask = taskRepository.save(taskDb);

        return modelMapper.map(updatedTask, TaskDetailDTO.class);
    }

    @Override
    public TaskDetailDTO finishTask(Integer id) {
        Task taskDb = taskRepository.getOne(id);
        if (taskDb == null)
            throw new IllegalArgumentException("Task Does Not Exist ID:" + id);

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(taskDb.getDeadline())) {
            Authentication authentication = authenticationFacade.getAuthentication();
            Optional<User> user = userRepository.findByUsername(authentication.getName());
            StudentRank sr = studentRankRepository.findByStudentId(user.get().getId());
            sr.setRank(sr.getRank() - 1);
            studentRankRepository.save(sr);

        } else {
            taskDb.setStatus(TaskStatus.SUCCESSFUL);
            taskDb = taskRepository.save(taskDb);
        }

        return modelMapper.map(taskDb, TaskDetailDTO.class);
    }


}
