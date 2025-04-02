package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.dto.BookLoanDto;
import com.codegym.thi_thuc_hanh.model.BookLoan;

import java.util.List;

public interface IBookLoanRepository {
    void borrowBook(BookLoan bookLoan);
    void returnBook(int loanId);
    BookLoan getBookLoanById(int loanId);
    List<BookLoanDto> getAllActiveBookLoans();
    List<BookLoanDto> searchActiveLoans(String bookName, String studentName);
}