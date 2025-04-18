package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.repository.ContractRepository;
import com.codegym.furama_resort.repository.IContractRepository;

import java.sql.SQLException;

public class ContractService implements IContractService {
    private final IContractRepository contractRepository;

    public ContractService() {
        this.contractRepository = new ContractRepository();
    }

    @Override
    public void addContract(Contract contract) throws SQLException {
        contractRepository.addContract(contract);
    }
}