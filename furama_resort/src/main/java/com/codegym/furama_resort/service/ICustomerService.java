package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.model.CustomerType;
import com.codegym.furama_resort.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    void saveCustomer(Customer customer, User user) throws SQLException;

    List<Customer> findAll() throws SQLException;

    List<Customer> searchAndPaginate(String keyword, int page, int pageSize) throws SQLException;

    int countSearchResults(String keyword) throws SQLException;

    Customer findById(int customerId) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) throws SQLException;

    List<CustomerType> getAllCustomerTypes() throws SQLException;
}