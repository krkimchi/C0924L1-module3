package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Service;

import java.sql.SQLException;
import java.util.List;

public interface IServiceService {
    void addService(Service service) throws SQLException;

    List<Service> findAll() throws SQLException;

    Service findById(int serviceId) throws SQLException;

    void updateService(Service service) throws SQLException;

    void deleteService(int serviceId) throws SQLException;
}