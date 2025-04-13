package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Service;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceRepository implements IServiceRepository {
    private static final String INSERT_SERVICE_SQL = "INSERT INTO service (service_name, service_area, service_cost, service_max_people, rent_type_id, service_type_id, standard_room, description_other_convenience, pool_area, number_of_floors, free_service) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
}
