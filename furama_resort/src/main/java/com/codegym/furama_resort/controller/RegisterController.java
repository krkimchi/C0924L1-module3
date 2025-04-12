package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.service.IUserService;
import com.codegym.furama_resort.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        String fullName = req.getParameter("full_name");
        String birthdayStr = req.getParameter("birthday");
        String gender = req.getParameter("gender");
        String idNumber = req.getParameter("id_number");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int customerTypeId = 5;

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Passwords do not match");
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
            return;
        }

        if (username == null || username.trim().isEmpty() ||
                fullName == null || fullName.trim().isEmpty() ||
                birthdayStr == null || birthdayStr.trim().isEmpty() ||
                gender == null || gender.trim().isEmpty() ||
                idNumber == null || idNumber.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                address == null || address.trim().isEmpty()) {
            req.setAttribute("error", "All fields are required");
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
            return;
        }

        LocalDate birthday;
        try {
            birthday = LocalDate.parse(birthdayStr);
            if (birthday.isAfter(LocalDate.now())) {
                req.setAttribute("error", "Birthday cannot be in the future");
                req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
                return;
            }
        } catch (DateTimeParseException e) {
            req.setAttribute("error", "Invalid birthday format");
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
            return;
        }

        if (!gender.equals("male") && !gender.equals("female")) {
            req.setAttribute("error", "Invalid gender selection");
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
            return;
        }

        try {
            if (userService.usernameExists(username)) {
                req.setAttribute("error", "Username already exists");
                req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
                return;
            }

            User user = new User(username, password, fullName, "customer", birthday, gender, idNumber, phone, email, address);
            userService.registerCustomer(user, fullName, email, phone, customerTypeId);

            req.setAttribute("success", "Registration successful! Please login.");
            req.getRequestDispatcher("/view/login/login.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Registration failed: " + e.getMessage());
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
        }
    }
}