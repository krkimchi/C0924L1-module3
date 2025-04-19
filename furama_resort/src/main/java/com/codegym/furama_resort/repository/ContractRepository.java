package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository implements IContractRepository {
    private static final String INSERT_CONTRACT_SQL =
            "insert into contract (contract_start_date, contract_end_date, contract_deposit, contract_total_money, employee_id, customer_id, service_id) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ACTIVE_CONTRACTS_SQL =
            "select c.contract_id, c.contract_start_date, c.contract_end_date, c.customer_id, c.service_id, s.service_name " +
                    "from contract c " +
                    "join service s on c.service_id = s.service_id " +
                    "where c.contract_end_date >= current_date " +
                    "and (s.service_name like ? or exists (select 1 from customer cu where cu.customer_id = c.customer_id and cu.customer_name like ?)) " +
                    "limit ? offset ?";

    private static final String COUNT_ACTIVE_CONTRACTS_SQL =
            "select count(*) " +
                    "from contract c " +
                    "join service s on c.service_id = s.service_id " +
                    "where c.contract_end_date >= current_date " +
                    "and (s.service_name like ? or exists (select 1 from customer cu where cu.customer_id = c.customer_id and cu.customer_name like ?))";

    private static final String SELECT_CONTRACT_DETAILS_SQL =
            "select cd.contract_detail_id, cd.contract_id, cd.attach_service_id, cd.quantity, a.attach_service_name " +
                    "from contract_detail cd " +
                    "join attach_service a on cd.attach_service_id = a.attach_service_id " +
                    "where cd.contract_id = ?";

    @Override
    public void addContract(Contract contract) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTRACT_SQL)) {
            preparedStatement.setTimestamp(1, Timestamp.valueOf(contract.getContractStartDate()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(contract.getContractEndDate()));
            preparedStatement.setDouble(3, contract.getContractDeposit());
            preparedStatement.setDouble(4, 0.0);
            preparedStatement.setInt(5, contract.getEmployeeId());
            preparedStatement.setInt(6, contract.getCustomerId());
            preparedStatement.setInt(7, contract.getServiceId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Contract> findActiveContracts(String keyword, int page, int pageSize) throws SQLException {
        List<Contract> contracts = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ACTIVE_CONTRACTS_SQL)) {
            String searchPattern = "%" + (keyword != null ? keyword : "") + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setInt(3, pageSize);
            stmt.setInt(4, (page - 1) * pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract();
                contract.setContractId(rs.getInt("contract_id"));
                Timestamp startTimestamp = rs.getTimestamp("contract_start_date");
                Timestamp endTimestamp = rs.getTimestamp("contract_end_date");
                contract.setContractStartDate(startTimestamp != null ? startTimestamp.toLocalDateTime() : null);
                contract.setContractEndDate(endTimestamp != null ? endTimestamp.toLocalDateTime() : null);
                contract.setCustomerId(rs.getInt("customer_id"));
                contract.setServiceId(rs.getInt("service_id"));
                contract.setServiceName(rs.getString("service_name"));
                contracts.add(contract);
            }
        }
        return contracts;
    }

    @Override
    public int countActiveContracts(String keyword) throws SQLException {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(COUNT_ACTIVE_CONTRACTS_SQL)) {
            String searchPattern = "%" + (keyword != null ? keyword : "") + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public List<ContractDetail> findContractDetailsByContractId(int contractId) throws SQLException {
        List<ContractDetail> details = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement stmt = connection.prepareStatement(SELECT_CONTRACT_DETAILS_SQL)) {
            stmt.setInt(1, contractId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ContractDetail detail = new ContractDetail();
                detail.setContractDetailId(rs.getInt("contract_detail_id"));
                detail.setContractId(rs.getInt("contract_id"));
                detail.setAttachServiceId(rs.getInt("attach_service_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setAttachServiceName(rs.getString("attach_service_name"));
                details.add(detail);
            }
        }
        return details;
    }
}
