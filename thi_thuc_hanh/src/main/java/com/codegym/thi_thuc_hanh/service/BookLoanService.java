package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.dto.BookLoanDto;
import com.codegym.thi_thuc_hanh.model.Book;
import com.codegym.thi_thuc_hanh.model.BookLoan;
import com.codegym.thi_thuc_hanh.repository.BookLoanRepository;
import com.codegym.thi_thuc_hanh.repository.IBookLoanRepository;

import java.util.List;

public class BookLoanService implements IBookLoanService {
    private IBookLoanRepository bookLoanRepository;
    private BookService bookService;

    public BookLoanService() {
        this.bookLoanRepository = new BookLoanRepository();
        this.bookService = new BookService();
    }

    @Override
    public void borrowBook(BookLoan bookLoan) {
        Book book = bookService.getBookById(bookLoan.getBookId());
        if (book != null && book.getQuantity() > 0) {
            bookLoanRepository.borrowBook(bookLoan);
            int newQuantity = book.getQuantity() - 1;
            bookService.updateBookQuantity(bookLoan.getBookId(), newQuantity);
        } else {
            throw new IllegalArgumentException("Sách không có sẵn để mượn.");
        }
    }

    @Override
    public void returnBook(int loanId) {
        BookLoan bookLoan = bookLoanRepository.getBookLoanById(loanId);
        if (bookLoan != null) {
            bookLoanRepository.returnBook(loanId);

            Book book = bookService.getBookById(bookLoan.getBookId());
            if (book != null) {
                int newQuantity = book.getQuantity() + 1;
                bookService.updateBookQuantity(bookLoan.getBookId(), newQuantity);
            }
        } else {
            throw new IllegalArgumentException("Mã mượn sách không hợp lệ.");
        }
    }

    @Override
    public List<BookLoanDto> getAllActiveBookLoans() {
        return bookLoanRepository.getAllActiveBookLoans();
    }

    @Override
    public List<BookLoanDto> searchActiveLoans(String bookName, String studentName) {
        return bookLoanRepository.searchActiveLoans(bookName, studentName);
    }
}
