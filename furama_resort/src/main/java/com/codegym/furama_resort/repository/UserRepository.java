package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String SELECT_ROLE_ID_SQL = "select role_id from role where role_name = ?";

    @Override
    public void registerCustomer(User user, String customerName, String customerEmail, String customerPhone, int customerTypeId) throws SQLException {
        Connection connection = null;
        try {
            connection = BaseRepository.getConnectDB();
            connection.setAutoCommit(false);

            String insertUserSql = "insert into user (username, password, full_name, type) values (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertUserSql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFullName());
                stmt.setString(4, user.getType());
                stmt.executeUpdate();
            }

            int roleId = 0;
            try (PreparedStatement roleStmt = connection.prepareStatement(SELECT_ROLE_ID_SQL)) {
                roleStmt.setString(1, user.getType());
                ResultSet roleRs = roleStmt.executeQuery();
                if (roleRs.next()) {
                    roleId = roleRs.getInt("role_id");
                } else {
                    throw new SQLException("Role not found for type: " + user.getType());
                }
            }

            String insertUserRoleSql = "insert into user_role (role_id, username) values (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertUserRoleSql)) {
                stmt.setInt(1, roleId);
                stmt.setString(2, user.getUsername());
                stmt.executeUpdate();
            }

            String insertCustomerSql = "insert into customer (username, customer_type_id, customer_name, customer_birthday, customer_gender, customer_id_card, customer_phone, customer_email, customer_address) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertCustomerSql)) {
                stmt.setString(1, user.getUsername());
                stmt.setInt(2, customerTypeId);
                stmt.setString(3, customerName);
                stmt.setDate(4, user.getBirthday() != null ? java.sql.Date.valueOf(user.getBirthday()) : null);
                stmt.setBoolean(5, "male".equalsIgnoreCase(user.getGender()));
                stmt.setString(6, user.getIdNumber());
                stmt.setString(7, customerPhone);
                stmt.setString(8, customerEmail);
                stmt.setString(9, user.getAddress());
                stmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    @Override
    public User authenticate(String username, String password) throws SQLException {
        String sql = "select * from user where username = ? and password = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("type")
                );
            }
        }
        return null;
    }

    @Override
    public boolean usernameExists(String username) throws SQLException {
        String sql = "select count(*) from user where username = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public List<String> getUserRoles(String username) throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "select r.role_name from role r join user_role ur on r.role_id = ur.role_id where ur.username = ?";
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                roles.add(rs.getString("role_name"));
            }
        }
        return roles;
    }
}