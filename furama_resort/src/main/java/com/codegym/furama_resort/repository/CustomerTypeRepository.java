package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.CustomerType;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepository implements ICustomerTypeRepository {
    @Override
    public List<CustomerType> findAll() throws SQLException {
        List<CustomerType> customerTypes = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        String sql = "select * from customer_type";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CustomerType customerType = new CustomerType(
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_type_name")
                );
                customerTypes.add(customerType);
            }
        } finally {
            connection.close();
        }
        return customerTypes;
    }
}