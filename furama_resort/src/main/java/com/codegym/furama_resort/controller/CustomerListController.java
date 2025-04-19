package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.dto.ContractDTO;
import com.codegym.furama_resort.dto.ContractDetailDTO;
import com.codegym.furama_resort.dto.CustomerDTO;
import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.model.ContractDetail;
import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.service.IContractService;
import com.codegym.furama_resort.service.ICustomerService;
import com.codegym.furama_resort.service.ContractService;
import com.codegym.furama_resort.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CustomerListController", value = "/customer-list")
public class CustomerListController extends HttpServlet {
    private ICustomerService customerService;
    private IContractService contractService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        contractService = new ContractService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("search");
            int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
            int pageSize = 10;

            List<CustomerDTO> customerDTOs = new ArrayList<>();
            List<Contract> contracts = contractService.findActiveContracts(keyword, page, pageSize);
            int totalRecords = contractService.countActiveContracts(keyword);
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            for (Contract contract : contracts) {
                Customer customer = customerService.findById(contract.getCustomerId());
                if (customer != null) {
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setCustomerId(customer.getCustomerId());
                    customerDTO.setCustomerName(customer.getCustomerName());

                    ContractDTO contractDTO = new ContractDTO();
                    contractDTO.setContractId(contract.getContractId());
                    contractDTO.setStartDate(contract.getContractStartDate() != null ? contract.getContractStartDate().toLocalDate() : null);
                    contractDTO.setEndDate(contract.getContractEndDate() != null ? contract.getContractEndDate().toLocalDate() : null);
                    contractDTO.setServiceId(contract.getServiceId());
                    contractDTO.setServiceName(contract.getServiceName());

                    List<ContractDetail> details = contractService.findContractDetailsByContractId(contract.getContractId());
                    List<ContractDetailDTO> detailDTOs = details.stream().map(detail -> {
                        ContractDetailDTO detailDTO = new ContractDetailDTO();
                        detailDTO.setContractDetailId(detail.getContractDetailId());
                        detailDTO.setContractId(detail.getContractId());
                        detailDTO.setAttachServiceId(detail.getAttachServiceId());
                        detailDTO.setAttachServiceName(detail.getAttachServiceName());
                        detailDTO.setQuantity(detail.getQuantity());
                        return detailDTO;
                    }).collect(Collectors.toList());

                    contractDTO.setContractDetails(detailDTOs);
                    customerDTO.setContract(contractDTO);
                    customerDTOs.add(customerDTO);
                }
            }

            req.setAttribute("customers", customerDTOs);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("searchQuery", keyword);
            req.getRequestDispatcher("/view/user/customer/customer_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error loading customer list: " + e.getMessage());
            req.getRequestDispatcher("/view/user/customer/customer_list.jsp").forward(req, resp);
        }
    }
}
