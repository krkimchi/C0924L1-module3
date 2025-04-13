package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.RentType;

import java.sql.SQLException;
import java.util.List;

public interface IRentTypeRepository {
    List<RentType> getAllRentTypes() throws SQLException;
}
