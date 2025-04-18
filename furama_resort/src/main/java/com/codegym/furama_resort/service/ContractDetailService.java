package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.repository.ContractDetailRepository;
import com.codegym.furama_resort.repository.IContractDetailRepository;

import java.sql.SQLException;

public class ContractDetailService implements IContractDetailService {
    private final IContractDetailRepository contractDetailRepository;

    public ContractDetailService() {
        this.contractDetailRepository = new ContractDetailRepository();
    }

    @Override
    public void addContractDetail(ContractDetail contractDetail) throws SQLException {
        contractDetailRepository.addContractDetail(contractDetail);
    }
}