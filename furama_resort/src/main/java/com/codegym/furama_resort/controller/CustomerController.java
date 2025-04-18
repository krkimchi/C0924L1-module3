package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.Customer;
import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.service.CustomerService;
import com.codegym.furama_resort.service.ICustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    private ICustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^(090|091|\\(84\\)\\+90|\\(84\\)\\+91)\\d{7}$");
    }

    private boolean isValidIdCard(String idCard) {
        return idCard != null && idCard.matches("^\\d{9}(\\d{3})?$");
    }

    private boolean isValidCustomerCode(String username) {
        return username != null && username.matches("^KH-\\d{4}$");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "delete":
                deleteCustomer(req, resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            listCustomers(req, resp);
            return;
        }

        switch (action) {
            case "add":
                addCustomer(req, resp);
                break;
            case "update":
                updateCustomer(req, resp);
                break;
            case "search":
                listCustomers(req, resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    private void listCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("search");
            int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
            int pageSize = 10;

            List<Customer> customers = customerService.searchAndPaginate(keyword, page, pageSize);
            int totalRecords = customerService.countSearchResults(keyword);
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            req.setAttribute("customers", customers);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("searchQuery", keyword);
            req.setAttribute("customerTypes", customerService.getAllCustomerTypes());
            req.getRequestDispatcher("/view/user/customer/customer.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error loading customers: " + e.getMessage());
            req.getRequestDispatcher("/view/user/customer/customer.jsp").forward(req, resp);
        }
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String fullName = req.getParameter("full_name");
            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");
            String customerAddress = req.getParameter("address");
            String customerIdCard = req.getParameter("id_card");
            String customerBirthdayStr = req.getParameter("birthday");
            String customerGenderStr = req.getParameter("gender");
            String customerTypeIdStr = req.getParameter("customer_type_id");
            String currentPage = req.getParameter("currentPage");
            String searchQuery = req.getParameter("search");

            if (username == null || username.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    fullName == null || fullName.trim().isEmpty() ||
                    customerTypeIdStr == null || customerTypeIdStr.trim().isEmpty()) {
                req.setAttribute("error", "Username, password, full name, and customer type are required");
                listCustomers(req, resp);
                return;
            }

            if (!isValidCustomerCode(username)) {
                req.setAttribute("error", "Invalid username. Must follow format KH-XXXX (e.g., KH-0001)");
                listCustomers(req, resp);
                return;
            }

            if (!isValidPhone(customerPhone)) {
                req.setAttribute("error", "Invalid phone format. Must be 090xxxxxxx, 091xxxxxxx, (84)+90xxxxxxx or (84)+91xxxxxxx");
                listCustomers(req, resp);
                return;
            }

            if (!isValidIdCard(customerIdCard)) {
                req.setAttribute("error", "Invalid ID Card format. Must be 9 or 12 digits");
                listCustomers(req, resp);
                return;
            }

            int customerTypeId;
            try {
                customerTypeId = Integer.parseInt(customerTypeIdStr);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid customer type");
                listCustomers(req, resp);
                return;
            }

            LocalDate customerBirthday = null;
            if (customerBirthdayStr != null && !customerBirthdayStr.isEmpty()) {
                try {
                    customerBirthday = LocalDate.parse(customerBirthdayStr);
                } catch (DateTimeParseException e) {
                    req.setAttribute("error", "Invalid birthday format");
                    listCustomers(req, resp);
                    return;
                }
            }

            Boolean customerGender = null;
            if (customerGenderStr != null && !customerGenderStr.isEmpty()) {
                customerGender = customerGenderStr.equalsIgnoreCase("Male");
            }

            User user = new User(username, password, fullName, "customer");
            Customer customer = new Customer();
            customer.setCustomerTypeId(customerTypeId);
            customer.setCustomerName(fullName);
            customer.setCustomerEmail(customerEmail);
            customer.setCustomerPhone(customerPhone);
            customer.setCustomerAddress(customerAddress);
            customer.setCustomerIdCard(customerIdCard);
            customer.setCustomerBirthday(customerBirthday);
            customer.setCustomerGender(customerGender);
            customer.setUsername(username);

            customerService.saveCustomer(customer, user);
            resp.sendRedirect(req.getContextPath() + "/customer?page=" + currentPage + "&search=" + (searchQuery != null ? searchQuery : ""));
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error adding customer: " + e.getMessage());
            listCustomers(req, resp);
        }
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(req.getParameter("id"));
            String customerName = req.getParameter("full_name");
            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");
            String customerAddress = req.getParameter("address");
            String customerIdCard = req.getParameter("id_card");
            String customerBirthdayStr = req.getParameter("birthday");
            String customerGenderStr = req.getParameter("gender");
            int customerTypeId = Integer.parseInt(req.getParameter("customer_type_id"));
            String currentPage = req.getParameter("currentPage");
            String searchQuery = req.getParameter("search");

            if (customerName == null || customerName.trim().isEmpty()) {
                req.setAttribute("error", "Customer name is required");
                listCustomers(req, resp);
                return;
            }

            if (!isValidPhone(customerPhone)) {
                req.setAttribute("error", "Invalid phone format. Must be 090xxxxxxx, 091xxxxxxx, (84)+90xxxxxxx or (84)+91xxxxxxx");
                listCustomers(req, resp);
                return;
            }

            if (!isValidIdCard(customerIdCard)) {
                req.setAttribute("error", "Invalid ID Card format. Must be 9 or 12 digits");
                listCustomers(req, resp);
                return;
            }

            LocalDate customerBirthday = null;
            if (customerBirthdayStr != null && !customerBirthdayStr.isEmpty()) {
                try {
                    customerBirthday = LocalDate.parse(customerBirthdayStr);
                } catch (DateTimeParseException e) {
                    req.setAttribute("error", "Invalid birthday format");
                    listCustomers(req, resp);
                    return;
                }
            }

            Boolean customerGender = null;
            if (customerGenderStr != null && !customerGenderStr.isEmpty()) {
                customerGender = customerGenderStr.equalsIgnoreCase("Male");
            }

            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setCustomerTypeId(customerTypeId);
            customer.setCustomerName(customerName);
            customer.setCustomerEmail(customerEmail);
            customer.setCustomerPhone(customerPhone);
            customer.setCustomerAddress(customerAddress);
            customer.setCustomerIdCard(customerIdCard);
            customer.setCustomerBirthday(customerBirthday);
            customer.setCustomerGender(customerGender);

            customerService.updateCustomer(customer);
            resp.sendRedirect(req.getContextPath() + "/customer?page=" + currentPage + "&search=" + (searchQuery != null ? searchQuery : ""));
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error updating customer: " + e.getMessage());
            listCustomers(req, resp);
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(req.getParameter("id"));
            String currentPage = req.getParameter("currentPage");
            String searchQuery = req.getParameter("search");

            customerService.deleteCustomer(customerId);
            resp.sendRedirect(req.getContextPath() + "/customer?page=" + currentPage + "&search=" + (searchQuery != null ? searchQuery : ""));
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error deleting customer: " + e.getMessage());
            listCustomers(req, resp);
        }
    }
}