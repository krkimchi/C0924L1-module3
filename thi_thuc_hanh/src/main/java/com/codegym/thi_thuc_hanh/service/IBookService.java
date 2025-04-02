package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void updateBookQuantity(int bookId, int newQuantity);
}
