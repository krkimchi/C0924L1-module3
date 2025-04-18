package com.codegym.furama_resort.controller;

import com.codegym.furama_resort.model.User;
import com.codegym.furama_resort.service.IUserService;
import com.codegym.furama_resort.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.login(username, password);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                String userType = user.getType();
                System.out.println("User type: " + userType);
                switch (userType) {
                    case "employee":
                        response.sendRedirect(request.getContextPath() + "/dashboard");
                        break;
                    case "customer":
                        response.sendRedirect(request.getContextPath() + "/home");
                        break;
                    default:
                        response.sendRedirect(request.getContextPath() + "/");
                        break;
                }

            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/view/login/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("/view/login/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login/login.jsp").forward(req, resp);
    }
}

