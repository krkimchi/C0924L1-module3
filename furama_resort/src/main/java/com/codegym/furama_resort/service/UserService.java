package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.repository.IUserRepository;
import com.codegym.furama_resort.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    private IUserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public void registerCustomer(User user, String customerName, String customerEmail, String customerPhone, int customerTypeId) throws SQLException {
        userRepository.registerCustomer(user, customerName, customerEmail, customerPhone, customerTypeId);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        return userRepository.authenticate(username, password);
    }

    @Override
    public boolean usernameExists(String username) throws SQLException {
        return userRepository.usernameExists(username);
    }

    @Override
    public List<String> getUserRoles(String username) throws SQLException {
        return userRepository.getUserRoles(username);
    }
}