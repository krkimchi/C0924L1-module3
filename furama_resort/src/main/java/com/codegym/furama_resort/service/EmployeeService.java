package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Employee;
import com.codegym.furama_resort.repository.EmployeeRepository;
import com.codegym.furama_resort.repository.IEmployeeRepository;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public void save(Employee employee) throws SQLException {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) throws SQLException {
        return employeeRepository.findById(id);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        employeeRepository.update(employee);
    }

    @Override
    public void delete(int id) throws SQLException {
        employeeRepository.delete(id);
    }
}
