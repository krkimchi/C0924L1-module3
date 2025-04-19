package com.codegym.furama_resort.service;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.repository.ContractRepository;
import com.codegym.furama_resort.repository.IContractRepository;

import java.sql.SQLException;
import java.util.List;

public class ContractService implements IContractService {
    private final IContractRepository contractRepository;

    public ContractService() {
        this.contractRepository = new ContractRepository();
    }

    @Override
    public void addContract(Contract contract) throws SQLException {
        contractRepository.addContract(contract);
    }

    @Override
    public List<Contract> findActiveContracts(String keyword, int page, int pageSize) throws SQLException {
        return contractRepository.findActiveContracts(keyword, page, pageSize);
    }

    @Override
    public int countActiveContracts(String keyword) throws SQLException {
        return contractRepository.countActiveContracts(keyword);
    }

    @Override
    public List<ContractDetail> findContractDetailsByContractId(int contractId) throws SQLException {
        return contractRepository.findContractDetailsByContractId(contractId);
    }
}