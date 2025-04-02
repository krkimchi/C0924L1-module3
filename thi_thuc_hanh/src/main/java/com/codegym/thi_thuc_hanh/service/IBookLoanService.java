package com.codegym.thi_thuc_hanh.service;

import com.codegym.thi_thuc_hanh.dto.BookLoanDto;
import com.codegym.thi_thuc_hanh.model.BookLoan;

import java.util.List;

public interface IBookLoanService {
    void borrowBook(BookLoan bookLoan);
    void returnBook(int loanId);
    List<BookLoanDto> getAllActiveBookLoans();
    List<BookLoanDto> searchActiveLoans(String bookName, String studentName);
}