package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.model.Book;
import com.codegym.thi_thuc_hanh.repository.BookRepository;
import com.codegym.thi_thuc_hanh.repository.IBookRepository;

import java.util.List;

public class BookService implements IBookService {
    private IBookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public void updateBookQuantity(int bookId, int newQuantity) {
        bookRepository.updateBookQuantity(bookId, newQuantity);
    }
}
