package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.RentType;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentTypeRepository implements IRentTypeRepository {
    private static final String SELECT_ALL_RENT_TYPES = "select rent_type_id, rent_type_name from rent_type";

    @Override
    public List<RentType> getAllRentTypes() throws SQLException {
        List<RentType> rentTypes = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_RENT_TYPES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("rent_type_id");
                String name = resultSet.getString("rent_type_name");
                rentTypes.add(new RentType(id, name));
            }
        }
        return rentTypes;
    }
}
