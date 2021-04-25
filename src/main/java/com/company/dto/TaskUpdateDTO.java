package com.company.dto;

import com.company.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDTO {

    private Integer id;
    private String content;
    private int rank;
    private LocalDate deadline;
    private TaskStatus status;
    private Integer assignedTo;
    private Integer assignedBy;
}