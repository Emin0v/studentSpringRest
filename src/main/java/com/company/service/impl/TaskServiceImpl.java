package com.company.service.impl;

import com.company.dto.TaskDTO;
import com.company.entity.Task;
import com.company.entity.TaskStatus;
import com.company.entity.User;
import com.company.repository.TaskRepository;
import com.company.repository.UserRepository;
import com.company.service.inter.TaskServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskServiceInter {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            throw new UnauthorizedUserException("Please log in");
        }
//        taskDTO.setRank(10);
//        taskDTO.setStatus(TaskStatus.PENDING);
//
//        LocalDate deadline = LocalDate.now().plusDays(10);
//        taskDTO.setDeadline(deadline);

//        Task task = modelMapper.map(taskDTO, Task.class);
        Task task = new Task();
        task.setRank(10);
        task.setStatus(TaskStatus.PENDING);

        System.out.println("1");

        LocalDate deadline = LocalDate.now().plusDays(10);
        task.setDeadline(deadline);
        task.setContent(taskDTO.getContent());
        System.out.println("2");
        Optional<User> user = userRepository.findByUsername(authentication.getName());
        task.setAssignedBy(user.get());
        System.out.println("3");
        task.setAssignedTo(userRepository.findById(taskDTO.getStudent_id()).get());
        System.out.println("4");
        task = taskRepository.save(task);

        System.out.println("5");
        taskDTO.setId(task.getId());

        System.out.println("6");

        return taskDTO;
    }

    @Override
    public TaskDTO getById(Integer id) {
        Task task = taskRepository.getOne(id);
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public Boolean delete(Integer id) {
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public TaskDTO update(TaskDTO task) {
        return null;
    }
}
