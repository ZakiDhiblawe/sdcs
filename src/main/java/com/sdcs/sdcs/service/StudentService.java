package com.sdcs.sdcs.service;

import com.sdcs.sdcs.model.Student;
import com.sdcs.sdcs.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setSchoolName(updatedStudent.getSchoolName());
            return studentRepository.save(student);
        }).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}