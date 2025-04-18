package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContractDetailRepository implements IContractDetailRepository {
    private static final String INSERT_CONTRACT_DETAIL_SQL = "insert into contract_detail (contract_id, attach_service_id, quantity) values (?, ?, ?)";

    @Override
    public void addContractDetail(ContractDetail contractDetail) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTRACT_DETAIL_SQL)) {
            preparedStatement.setInt(1, contractDetail.getContractId());
            preparedStatement.setInt(2, contractDetail.getAttachServiceId());
            preparedStatement.setInt(3, contractDetail.getQuantity());

            preparedStatement.executeUpdate();
        }
    }
}