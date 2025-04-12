package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    void save(Employee employee) throws SQLException;

    List<Employee> findAll() throws SQLException;

    Employee findById(int id) throws SQLException;

    void update(Employee employee) throws SQLException;

    void delete(int id) throws SQLException;
}
