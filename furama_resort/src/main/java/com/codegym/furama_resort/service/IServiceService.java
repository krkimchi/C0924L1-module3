package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Service;

import java.sql.SQLException;

public interface IServiceService {
    void addService(Service service) throws SQLException;
}
