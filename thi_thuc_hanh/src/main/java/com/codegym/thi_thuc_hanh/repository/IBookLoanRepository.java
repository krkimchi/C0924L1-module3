package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.model.BookLoan;

public interface IBookLoanRepository {
    void borrowBook(BookLoan bookLoan);
    void returnBook(int loanId);
    BookLoan getBookLoanById(int loanId);
}
