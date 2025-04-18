package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Contract;

import java.sql.SQLException;

public interface IContractService {
    void addContract(Contract contract) throws SQLException;
}