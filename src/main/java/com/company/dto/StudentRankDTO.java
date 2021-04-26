package com.company.dto;

import com.company.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentRankDTO {

    private Integer id;
    private int rank;
    private UserDTO studentId;
}
