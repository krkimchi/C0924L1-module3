package com.codegym.thi_thuc_hanh.controller;

import com.codegym.thi_thuc_hanh.model.Book;
import com.codegym.thi_thuc_hanh.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet (name = "BookController", value = "/books")
public class BookController extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() {
        bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        req.setAttribute("books", books);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/book-list.jsp");
        dispatcher.forward(req, resp);
    }
}
