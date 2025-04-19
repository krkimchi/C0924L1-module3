package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.ServiceType;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeRepository implements IServiceTypeRepository {
    private static final String SELECT_ALL_SERVICE_TYPES = "select service_type_id, service_type_name from service_type";

    @Override
    public List<ServiceType> getAllServiceTypes() throws SQLException {
        List<ServiceType> serviceTypes = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SERVICE_TYPES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("service_type_id");
                String name = resultSet.getString("service_type_name");
                serviceTypes.add(new ServiceType(id, name));
            }
        }
        return serviceTypes;
    }
}