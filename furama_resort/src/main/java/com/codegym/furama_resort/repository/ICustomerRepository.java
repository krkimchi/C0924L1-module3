package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerRepository {
    void saveCustomer(Customer customer) throws SQLException;

    List<Customer> findAll() throws SQLException;

    List<Customer> searchAndPaginate(String keyword, int page, int pageSize) throws SQLException;

    int countSearchResults(String keyword) throws SQLException;

    Customer findById(int customerId) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) throws SQLException;
}