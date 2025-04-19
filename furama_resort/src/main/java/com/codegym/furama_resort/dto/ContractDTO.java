package com.codegym.furama_resort.dto;

import java.time.LocalDate;
import java.util.List;

public class ContractDTO {
    private int contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int serviceId;
    private String serviceName;
    private List<ContractDetailDTO> contractDetails;

    public ContractDTO() {}

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<ContractDetailDTO> getContractDetails() {
        return contractDetails;
    }

    public void setContractDetails(List<ContractDetailDTO> contractDetails) {
        this.contractDetails = contractDetails;
    }
}