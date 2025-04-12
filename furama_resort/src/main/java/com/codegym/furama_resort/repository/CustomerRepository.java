package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    @Override
    public void saveCustomer(Customer customer) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "insert into customer (customer_type_id, customer_name, customer_birthday, customer_gender, customer_id_card, customer_phone, customer_email, customer_address, username) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getCustomerName());
            stmt.setObject(3, customer.getCustomerBirthday());
            stmt.setBoolean(4, customer.getCustomerGender() != null ? customer.getCustomerGender() : false);
            stmt.setString(5, customer.getCustomerIdCard());
            stmt.setString(6, customer.getCustomerPhone());
            stmt.setString(7, customer.getCustomerEmail());
            stmt.setString(8, customer.getCustomerAddress());
            stmt.setString(9, customer.getUsername());
            stmt.executeUpdate();
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        String sql = "select c.*, u.full_name from customer c join user u on c.username = u.username";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_name"),
                        rs.getDate("customer_birthday") != null ? rs.getDate("customer_birthday").toLocalDate() : null,
                        rs.getBoolean("customer_gender") ? "Male" : "Female",
                        rs.getString("customer_id_card"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getString("customer_address"),
                        rs.getString("username")
                );
                customers.add(customer);
            }
        } finally {
            connection.close();
        }
        return customers;
    }

    @Override
    public List<Customer> searchAndPaginate(String keyword, int page, int pageSize) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        String sql = "select c.*, u.full_name from customer c join user u on c.username = u.username " +
                "where c.customer_name like ? or c.customer_email like ? " +
                "limit ? offset ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String searchPattern = "%" + (keyword != null ? keyword : "") + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setInt(3, pageSize);
            stmt.setInt(4, (page - 1) * pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_name"),
                        rs.getDate("customer_birthday") != null ? rs.getDate("customer_birthday").toLocalDate() : null,
                        rs.getBoolean("customer_gender") ? "Male" : "Female",
                        rs.getString("customer_id_card"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getString("customer_address"),
                        rs.getString("username")
                );
                customers.add(customer);
            }
        } finally {
            connection.close();
        }
        return customers;
    }

    @Override
    public int countSearchResults(String keyword) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "select count(*) from customer where customer_name like ? or customer_email like ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String searchPattern = "%" + (keyword != null ? keyword : "") + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            connection.close();
        }
        return 0;
    }

    @Override
    public Customer findById(int customerId) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "select c.*, u.full_name from customer c join user u on c.username = u.username where c.customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("customer_type_id"),
                        rs.getString("customer_name"),
                        rs.getDate("customer_birthday") != null ? rs.getDate("customer_birthday").toLocalDate() : null,
                        rs.getBoolean("customer_gender") ? "Male" : "Female",
                        rs.getString("customer_id_card"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getString("customer_address"),
                        rs.getString("username")
                );
            }
        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "update customer set customer_type_id = ?, customer_name = ?, customer_birthday = ?, customer_gender = ?, customer_id_card = ?, customer_phone = ?, customer_email = ?, customer_address = ? where customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getCustomerName());
            stmt.setObject(3, customer.getCustomerBirthday());
            stmt.setBoolean(4, customer.getCustomerGender() != null ? customer.getCustomerGender() : false);
            stmt.setString(5, customer.getCustomerIdCard());
            stmt.setString(6, customer.getCustomerPhone());
            stmt.setString(7, customer.getCustomerEmail());
            stmt.setString(8, customer.getCustomerAddress());
            stmt.setInt(9, customer.getCustomerId());
            stmt.executeUpdate();
        } finally {
            connection.close();
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Connection connection = BaseRepository.getConnectDB();
        String sql = "delete from customer where customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        } finally {
            connection.close();
        }
    }


}