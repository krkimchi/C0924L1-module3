package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.model.Student;
import com.codegym.thi_thuc_hanh.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    @Override
    public List<Student> getAllStudent() {
        String sql = "select student_id, full_name, class from students order by full_name";
        List<Student> students = new ArrayList<>();

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("class")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching students", e);
        }
        return students;
    }
}
