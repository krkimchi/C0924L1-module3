package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Position;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionRepository implements IPositionRepository {
    @Override
    public List<Position> findAll() throws SQLException {
        List<Position> list = new ArrayList<>();
        Connection conn = BaseRepository.getConnectDB();
        String sql = "select * from employee_position";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Position(rs.getInt(1), rs.getString(2)));
            }
        } finally {
            conn.close();
        }
        return list;
    }
}
