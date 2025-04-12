package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Division;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisionRepository implements IDivisionRepository {
    @Override
    public List<Division> findAll() throws SQLException {
        List<Division> list = new ArrayList<>();
        Connection conn = BaseRepository.getConnectDB();
        String sql = "select * from division";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Division(rs.getInt(1), rs.getString(2)));
            }
        } finally {
            conn.close();
        }
        return list;
    }
}
