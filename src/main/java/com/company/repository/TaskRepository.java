package com.company.repository;

import com.company.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByAssignedToOrAssignedBy(Integer id);

}
