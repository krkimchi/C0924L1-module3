package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.model.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    void updateBookQuantity(int bookId, int newQuantity);
}
