package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.EducationDegree;
import java.sql.SQLException;
import java.util.List;

public interface IEducationDegreeRepository {
    List<EducationDegree> findAll() throws SQLException;
}
