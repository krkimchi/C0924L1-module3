package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Contract;

import java.sql.SQLException;

public interface IContractRepository {
    void addContract(Contract contract) throws SQLException;
}