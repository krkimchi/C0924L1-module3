package com.codegym.thi_thuc_hanh.controller;

import com.codegym.thi_thuc_hanh.dto.BookLoanDto;
import com.codegym.thi_thuc_hanh.service.BookLoanService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookLoanController", value = "/book-loans")
public class BookLoanController extends HttpServlet {
    private BookLoanService bookLoanService;

    @Override
    public void init() {
        bookLoanService = new BookLoanService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String studentName = req.getParameter("studentName");

        List<BookLoanDto> activeLoans;
        if (bookName != null || studentName != null) {
            activeLoans = bookLoanService.searchActiveLoans(
                    bookName != null ? bookName : "",
                    studentName != null ? studentName : ""
            );
        } else {
            activeLoans = bookLoanService.getAllActiveBookLoans();
        }

        req.setAttribute("bookLoans", activeLoans);
        req.getRequestDispatcher("/view/book-loans.jsp").forward(req, resp);
    }
}