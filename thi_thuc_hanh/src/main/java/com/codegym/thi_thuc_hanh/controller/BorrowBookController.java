package com.codegym.thi_thuc_hanh.controller;

import com.codegym.thi_thuc_hanh.model.Book;
import com.codegym.thi_thuc_hanh.model.BookLoan;
import com.codegym.thi_thuc_hanh.model.Student;
import com.codegym.thi_thuc_hanh.service.BookLoanService;
import com.codegym.thi_thuc_hanh.service.BookService;
import com.codegym.thi_thuc_hanh.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "BorrowBookController", value = "/borrow")
public class BorrowBookController extends HttpServlet {
    private BookLoanService bookLoanService;
    private BookService bookService;
    private StudentService studentService;

    @Override
    public void init() {
        bookLoanService = new BookLoanService();
        bookService = new BookService();
        studentService = new StudentService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanCode = req.getParameter("loanCode");
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));

        Date loanDate = new Date(System.currentTimeMillis());
        Date returnDate = Date.valueOf(req.getParameter("returnDate"));

        Book book = bookService.getBookById(bookId);
        if (book != null && book.getQuantity() > 0) {
            BookLoan bookLoan = new BookLoan(loanCode, bookId, studentId, loanDate, returnDate, true);
            bookLoanService.borrowBook(bookLoan);
            resp.sendRedirect("/books");
        } else {
            req.setAttribute("errorMessage", "Sách không có sẵn để mượn.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            Book book = bookService.getBookById(bookId);

            if (book == null) {
                resp.sendRedirect("/books?error=book_not_found");
                return;
            }

            List<Student> students = studentService.getAllStudents();
            req.setAttribute("book", book);
            req.setAttribute("students", students);
            req.setAttribute("currentDate", new java.sql.Date(System.currentTimeMillis()));

            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/borrow.jsp");
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect("/books?error=invalid_book_id");
        }
    }
}
