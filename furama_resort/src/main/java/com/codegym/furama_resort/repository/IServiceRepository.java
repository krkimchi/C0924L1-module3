package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface IServiceRepository {
    void addService(Service service) throws SQLException;
    List<Service> findAll() throws SQLException;
}
