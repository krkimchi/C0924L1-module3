package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Service;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements IServiceRepository {
    private static final String INSERT_SERVICE_SQL =
            "insert into service (service_name, service_area, service_cost, service_capacity, rent_type_id, service_type_id, standard_room, description_other_convenience, pool_area, number_of_floors, free_service) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SERVICES_SQL =
            "select * from service";
    private static final String SELECT_SERVICE_BY_ID_SQL =
            "select * from service where service_id = ?";
    private static final String UPDATE_SERVICE_SQL =
            "update service set service_name = ?, service_area = ?, service_cost = ?, service_capacity = ?, rent_type_id = ?, service_type_id = ?, standard_room = ?, description_other_convenience = ?, pool_area = ?, number_of_floors = ?, free_service = ? where service_id = ?";
    private static final String DELETE_SERVICE_SQL =
            "delete from service where service_id = ?";

    @Override
    public void addService(Service service) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_SQL)) {
            preparedStatement.setString(1, service.getServiceName());
            preparedStatement.setInt(2, service.getServiceArea());
            preparedStatement.setDouble(3, service.getServiceCost());
            preparedStatement.setInt(4, service.getServiceCapacity());
            preparedStatement.setInt(5, service.getRentTypeId());
            preparedStatement.setInt(6, service.getServiceTypeId());
            preparedStatement.setString(7, service.getStandardRoom());
            preparedStatement.setString(8, service.getDescriptionOtherConvenience());
            preparedStatement.setDouble(9, service.getPoolArea());
            preparedStatement.setInt(10, service.getNumberOfFloors());
            preparedStatement.setString(11, service.getFreeService());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Service> findAll() throws SQLException {
        List<Service> services = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service();
                service.setServiceId(resultSet.getInt("service_id"));
                service.setServiceName(resultSet.getString("service_name"));
                service.setServiceArea(resultSet.getInt("service_area"));
                service.setServiceCost(resultSet.getDouble("service_cost"));
                service.setServiceCapacity(resultSet.getInt("service_capacity"));
                service.setRentTypeId(resultSet.getInt("rent_type_id"));
                service.setServiceTypeId(resultSet.getInt("service_type_id"));
                service.setStandardRoom(resultSet.getString("standard_room"));
                service.setDescriptionOtherConvenience(resultSet.getString("description_other_convenience"));
                service.setPoolArea(resultSet.getDouble("pool_area"));
                service.setNumberOfFloors(resultSet.getInt("number_of_floors"));
                service.setFreeService(resultSet.getString("free_service"));
                services.add(service);
            }
        }
        return services;
    }

    @Override
    public Service findById(int serviceId) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_ID_SQL)) {
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Service service = new Service();
                service.setServiceId(resultSet.getInt("service_id"));
                service.setServiceName(resultSet.getString("service_name"));
                service.setServiceArea(resultSet.getInt("service_area"));
                service.setServiceCost(resultSet.getDouble("service_cost"));
                service.setServiceCapacity(resultSet.getInt("service_capacity"));
                service.setRentTypeId(resultSet.getInt("rent_type_id"));
                service.setServiceTypeId(resultSet.getInt("service_type_id"));
                service.setStandardRoom(resultSet.getString("standard_room"));
                service.setDescriptionOtherConvenience(resultSet.getString("description_other_convenience"));
                service.setPoolArea(resultSet.getDouble("pool_area"));
                service.setNumberOfFloors(resultSet.getInt("number_of_floors"));
                service.setFreeService(resultSet.getString("free_service"));
                return service;
            }
        }
        return null;
    }

    @Override
    public void updateService(Service service) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_SQL)) {
            preparedStatement.setString(1, service.getServiceName());
            preparedStatement.setInt(2, service.getServiceArea());
            preparedStatement.setDouble(3, service.getServiceCost());
            preparedStatement.setInt(4, service.getServiceCapacity());
            preparedStatement.setInt(5, service.getRentTypeId());
            preparedStatement.setInt(6, service.getServiceTypeId());
            preparedStatement.setString(7, service.getStandardRoom());
            preparedStatement.setString(8, service.getDescriptionOtherConvenience());
            preparedStatement.setDouble(9, service.getPoolArea());
            preparedStatement.setInt(10, service.getNumberOfFloors());
            preparedStatement.setString(11, service.getFreeService());
            preparedStatement.setInt(12, service.getServiceId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteService(int serviceId) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVICE_SQL)) {
            preparedStatement.setInt(1, serviceId);
            preparedStatement.executeUpdate();
        }
    }
}