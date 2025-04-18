package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContractRepository implements IContractRepository {
    private static final String INSERT_CONTRACT_SQL = "insert into contract (contract_start_date, contract_end_date, contract_deposit, contract_total_money, employee_id, customer_id, service_id) values (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void addContract(Contract contract) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTRACT_SQL)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(contract.getContractStartDate()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(contract.getContractEndDate()));
            preparedStatement.setDouble(3, contract.getContractDeposit());
            preparedStatement.setDouble(4, contract.getContractTotalMoney());
            preparedStatement.setInt(5, contract.getEmployeeId());
            preparedStatement.setInt(6, contract.getCustomerId());
            preparedStatement.setInt(7, contract.getServiceId());

            preparedStatement.executeUpdate();
        }
    }
}