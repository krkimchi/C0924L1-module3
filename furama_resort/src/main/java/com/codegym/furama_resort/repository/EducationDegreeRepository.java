package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.EducationDegree;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationDegreeRepository implements IEducationDegreeRepository {
    @Override
    public List<EducationDegree> findAll() throws SQLException {
        List<EducationDegree> list = new ArrayList<>();
        Connection conn = BaseRepository.getConnectDB();
        String sql = "select * from education_degree";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EducationDegree(rs.getInt(1), rs.getString(2)));
            }
        } finally {
            conn.close();
        }
        return list;
    }
}
