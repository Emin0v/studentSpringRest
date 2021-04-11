package com.company.student.controller;


import com.company.student.config.AppConfig;
import com.company.student.domain.Student;
import com.company.student.service.StudentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class StudentController {

    private final AppConfig app;

    private final StudentInfoService service;

    @GetMapping("/details")
    public String getAppDetails(){
        return app.getName()+" Version : "+app.getVersion() ;
    }

    @PostMapping
    public void createStudent(@RequestBody Student student){
        service.createStudent(student);
    }

}
