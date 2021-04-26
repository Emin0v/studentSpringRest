package com.company.repository;

import com.company.entity.StudentRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRankRepository extends JpaRepository<StudentRank,Integer> {

    StudentRank findByStudentId(Integer id);
}
