package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Employee;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public void save(Employee e) throws SQLException {
        Connection conn = BaseRepository.getConnectDB();
        String sql = "insert into employee (employee_name, employee_birthday, employee_id_card, employee_phone, employee_email, employee_salary, education_degree_id, position_id, division_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getEmployeeName());
            ps.setDate(2, Date.valueOf(e.getEmployeeBirthday()));
            ps.setString(3, e.getEmployeeIdCard());
            ps.setString(4, e.getEmployeePhone());
            ps.setString(5, e.getEmployeeEmail());
            ps.setDouble(6, e.getEmployeeSalary());
            ps.setInt(7, e.getEducationDegreeId());
            ps.setInt(8, e.getPositionId());
            ps.setInt(9, e.getDivisionId());
            ps.executeUpdate();
        } finally {
            conn.close();
        }
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        Connection conn = BaseRepository.getConnectDB();
        String sql = "select e.*, ed.education_degree_name, p.position_name, d.division_name " +
                "from employee e " +
                "join education_degree ed on e.education_degree_id = ed.education_degree_id " +
                "join employee_position p on e.position_id = p.position_id " +
                "join division d on e.division_id = d.division_id";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDate("employee_birthday").toLocalDate(),
                        rs.getString("employee_id_card"),
                        rs.getString("employee_phone"),
                        rs.getString("employee_email"),
                        rs.getDouble("employee_salary"),
                        rs.getInt("education_degree_id"),
                        rs.getInt("position_id"),
                        rs.getInt("division_id")
                );
                e.setEducationName(rs.getString("education_degree_name"));
                e.setPositionName(rs.getString("position_name"));
                e.setDivisionName(rs.getString("division_name"));
                list.add(e);
            }
        } finally {
            conn.close();
        }
        return list;
    }

    @Override
    public Employee findById(int id) throws SQLException {
        Connection conn = BaseRepository.getConnectDB();
        String sql = "select * from employee where employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getDate("employee_birthday").toLocalDate(),
                        rs.getString("employee_id_card"),
                        rs.getString("employee_phone"),
                        rs.getString("employee_email"),
                        rs.getDouble("employee_salary"),
                        rs.getInt("education_degree_id"),
                        rs.getInt("position_id"),
                        rs.getInt("division_id")
                );
            }
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public void update(Employee e) throws SQLException {
        Connection conn = BaseRepository.getConnectDB();
        String sql = "update employee set employee_name = ?, employee_birthday = ?, employee_id_card = ?, employee_phone = ?, employee_email = ?, employee_salary = ?, education_degree_id = ?, position_id = ?, division_id = ? where employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getEmployeeName());
            ps.setDate(2, Date.valueOf(e.getEmployeeBirthday()));
            ps.setString(3, e.getEmployeeIdCard());
            ps.setString(4, e.getEmployeePhone());
            ps.setString(5, e.getEmployeeEmail());
            ps.setDouble(6, e.getEmployeeSalary());
            ps.setInt(7, e.getEducationDegreeId());
            ps.setInt(8, e.getPositionId());
            ps.setInt(9, e.getDivisionId());
            ps.setInt(10, e.getEmployeeId());
            ps.executeUpdate();
        } finally {
            conn.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection conn = BaseRepository.getConnectDB();
        String sql = "delete from employee where employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            conn.close();
        }
    }
}
