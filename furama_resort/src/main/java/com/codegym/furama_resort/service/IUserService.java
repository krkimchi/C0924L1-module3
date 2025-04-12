package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    void registerCustomer(User user, String customerName, String customerEmail, String customerPhone, int customerTypeId) throws SQLException;

    User login(String username, String password) throws SQLException;

    boolean usernameExists(String username) throws SQLException;

    List<String> getUserRoles(String username) throws SQLException;
}