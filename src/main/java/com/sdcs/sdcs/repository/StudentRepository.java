package com.sdcs.sdcs.repository;

import com.sdcs.sdcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}