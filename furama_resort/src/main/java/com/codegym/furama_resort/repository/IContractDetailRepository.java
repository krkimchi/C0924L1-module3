package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.ContractDetail;

import java.sql.SQLException;

public interface IContractDetailRepository {
    void addContractDetail(ContractDetail contractDetail) throws SQLException;
}