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
        String fullName = req.getParameter("fullName");
        String customerEmail = req.getParameter("customerEmail");
        String customerPhone = req.getParameter("customerPhone");
        int customerTypeId = 5;

        try {
            if (userService.usernameExists(username)) {
                req.setAttribute("error", "Username already exists");
                req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
                return;
            }

            User user = new User(username, password, fullName, "customer");
            userService.registerCustomer(user, fullName, customerEmail, customerPhone, customerTypeId);

            req.setAttribute("success", "Registration successful! Please login.");
            req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Registration failed: " + e.getMessage());
            req.getRequestDispatcher("/view/register/register.jsp").forward(req, resp);
        }
    }
}