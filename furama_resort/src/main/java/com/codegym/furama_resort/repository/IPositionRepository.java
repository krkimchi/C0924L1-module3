package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Position;

import java.sql.SQLException;
import java.util.List;

public interface IPositionRepository {
    List<Position> findAll() throws SQLException;
}
