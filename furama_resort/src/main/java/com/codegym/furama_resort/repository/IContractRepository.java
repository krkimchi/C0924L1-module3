package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.model.ContractDetail;

import java.sql.SQLException;
import java.util.List;

public interface IContractRepository {
    void addContract(Contract contract) throws SQLException;

    List<Contract> findActiveContracts(String keyword, int page, int pageSize) throws SQLException;

    int countActiveContracts(String keyword) throws SQLException;

    List<ContractDetail> findContractDetailsByContractId(int contractId) throws SQLException;
}