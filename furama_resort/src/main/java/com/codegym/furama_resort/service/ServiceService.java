package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Service;
import com.codegym.furama_resort.repository.IServiceRepository;
import com.codegym.furama_resort.repository.ServiceRepository;

import java.sql.SQLException;
import java.util.List;

public class ServiceService implements IServiceService {
    private final IServiceRepository serviceRepository;

    public ServiceService() {
        this.serviceRepository = new ServiceRepository();
    }

    @Override
    public void addService(Service service) throws SQLException {
        serviceRepository.addService(service);
    }

    @Override
    public List<Service> findAll() throws SQLException {
        return serviceRepository.findAll();
    }

    @Override
    public Service findById(int serviceId) throws SQLException {
        return serviceRepository.findById(serviceId);
    }

    @Override
    public void updateService(Service service) throws SQLException {
        serviceRepository.updateService(service);
    }

    @Override
    public void deleteService(int serviceId) throws SQLException {
        serviceRepository.deleteService(serviceId);
    }
}