package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.model.CustomerType;
import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.repository.CustomerRepository;
import com.codegym.furama_resort.repository.CustomerTypeRepository;
import com.codegym.furama_resort.repository.ICustomerRepository;
import com.codegym.furama_resort.repository.ICustomerTypeRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository = new CustomerRepository();
    private ICustomerTypeRepository customerTypeRepository = new CustomerTypeRepository();
    private IUserService userService = new UserService();

    @Override
    public void saveCustomer(Customer customer, User user) throws SQLException {
        if (userService.usernameExists(user.getUsername())) {
            throw new SQLException("Username already exists");
        }

        user.setType("customer");
        userService.registerCustomer(user, customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerPhone(), customer.getCustomerTypeId());
        customerRepository.saveCustomer(customer);
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchAndPaginate(String keyword, int page, int pageSize) throws SQLException {
        return customerRepository.searchAndPaginate(keyword, page, pageSize);
    }

    @Override
    public int countSearchResults(String keyword) throws SQLException {
        return customerRepository.countSearchResults(keyword);
    }

    @Override
    public Customer findById(int customerId) throws SQLException {
        return customerRepository.findById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        customerRepository.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customerRepository.deleteCustomer(customerId);
    }

    @Override
    public List<CustomerType> getAllCustomerTypes() throws SQLException {
        return customerTypeRepository.findAll();
    }
}