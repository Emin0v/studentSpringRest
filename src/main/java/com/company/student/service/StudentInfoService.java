package com.company.student.service;

import com.company.student.domain.Student;
import com.company.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentInfoService {

    private final StudentRepository studentRepository;

    public void createStudent(Student student){
        studentRepository.save(student);
    }

}
