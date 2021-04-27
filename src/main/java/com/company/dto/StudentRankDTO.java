package com.company.dto;

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
