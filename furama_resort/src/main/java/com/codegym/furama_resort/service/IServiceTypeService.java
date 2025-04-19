package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.ServiceType;

import java.sql.SQLException;
import java.util.List;

public interface IServiceTypeService {
    List<ServiceType> getAllServiceTypes() throws SQLException;
}