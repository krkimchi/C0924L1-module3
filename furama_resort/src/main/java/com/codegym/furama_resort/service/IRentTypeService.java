package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.RentType;

import java.sql.SQLException;
import java.util.List;

public interface IRentTypeService {
    List<RentType> getAllRentTypes() throws SQLException;
}
