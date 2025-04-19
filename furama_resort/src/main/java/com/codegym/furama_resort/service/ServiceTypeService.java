package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.ServiceType;
import com.codegym.furama_resort.repository.IServiceTypeRepository;
import com.codegym.furama_resort.repository.ServiceTypeRepository;

import java.sql.SQLException;
import java.util.List;

public class ServiceTypeService implements IServiceTypeService {
    private final IServiceTypeRepository serviceTypeRepository;

    public ServiceTypeService() {
        this.serviceTypeRepository = new ServiceTypeRepository();
    }

    @Override
    public List<ServiceType> getAllServiceTypes() throws SQLException {
        return serviceTypeRepository.getAllServiceTypes();
    }
}