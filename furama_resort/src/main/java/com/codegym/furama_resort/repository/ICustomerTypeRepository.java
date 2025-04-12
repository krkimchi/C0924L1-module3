package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.CustomerType;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerTypeRepository {
    List<CustomerType> findAll() throws SQLException;
}