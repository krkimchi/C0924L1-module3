package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Division;

import java.sql.SQLException;
import java.util.List;

public interface IDivisionRepository {
    List<Division> findAll() throws SQLException;
}
