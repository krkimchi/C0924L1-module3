package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.ContractDetail;

import java.sql.SQLException;

public interface IContractDetailService {
    void addContractDetail(ContractDetail contractDetail) throws SQLException;
}