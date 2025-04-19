package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.ServiceType;

import java.sql.SQLException;
import java.util.List;

public interface IServiceTypeRepository {
    List<ServiceType> getAllServiceTypes() throws SQLException;
}