package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.model.Student;
import com.codegym.thi_thuc_hanh.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private StudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudent();
    }
}
