package com.codegym.thi_thuc_hanh.controller;

import com.codegym.thi_thuc_hanh.service.BookLoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReturnBookController", value = "/return")
public class ReturnBookController extends HttpServlet {
    private BookLoanService bookLoanService;

    @Override
    public void init() {
        bookLoanService = new BookLoanService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int loanId = Integer.parseInt(req.getParameter("loanId"));
        bookLoanService.returnBook(loanId);

        resp.sendRedirect("/books");
    }
}