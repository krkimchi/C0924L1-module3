package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.RentType;
import com.codegym.furama_resort.repository.IRentTypeRepository;
import com.codegym.furama_resort.repository.RentTypeRepository;

import java.sql.SQLException;
import java.util.List;

public class RentTypeService implements IRentTypeService {
    private final IRentTypeRepository rentTypeRepository;

    public RentTypeService() {
        this.rentTypeRepository = new RentTypeRepository();
    }

    @Override
    public List<RentType> getAllRentTypes() throws SQLException {
        return rentTypeRepository.getAllRentTypes();
    }
}