package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.model.BookLoan;

public interface IBookLoanService {
    void borrowBook(BookLoan bookLoan);
    void returnBook(int loanId);
}
