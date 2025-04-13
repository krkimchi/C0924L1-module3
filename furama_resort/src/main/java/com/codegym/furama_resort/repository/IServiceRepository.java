package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Service;

import java.sql.SQLException;

public interface IServiceRepository {
    void addService(Service service) throws SQLException;
}
