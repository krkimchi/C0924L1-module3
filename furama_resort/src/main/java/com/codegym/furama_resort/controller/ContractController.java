package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.Contract;
import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.model.Employee;
import com.codegym.furama_resort.model.Service;
import com.codegym.furama_resort.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ContractController", value = "/contract")
public class ContractController extends HttpServlet {
    private IContractService contractService;

    @Override
    public void init() throws ServletException {
        contractService = new ContractService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Employee> employeeList = new EmployeeService().findAll();
            List<Customer> customerList = new CustomerService().findAll();
            List<Service> serviceList = new ServiceService().findAll();

            request.setAttribute("employees", employeeList);
            request.setAttribute("customers", customerList);
            request.setAttribute("services", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/contract/contract.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addContract(request, response);
    }

    private void addContract(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(request.getParameter("contractStartDate"), formatter);
            LocalDateTime endDate = LocalDateTime.parse(request.getParameter("contractEndDate"), formatter);
            double deposit = Double.parseDouble(request.getParameter("contractDeposit"));
            double totalMoney = Double.parseDouble(request.getParameter("contractTotalMoney"));
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));

            Contract contract = new Contract();
            contract.setContractStartDate(startDate);
            contract.setContractEndDate(endDate);
            contract.setContractDeposit(deposit);
            contract.setContractTotalMoney(totalMoney);
            contract.setEmployeeId(employeeId);
            contract.setCustomerId(customerId);
            contract.setServiceId(serviceId);

            contractService.addContract(contract);

            response.sendRedirect("contract.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("contract.jsp?error=true");
        }
    }
}
