package com.codegym.thi_thuc_hanh.model;

import java.sql.Date;

public class BookLoan {
    private int loanId;
    private String loanCode;
    private int bookId;
    private int studentId;
    private Date loanDate;
    private Date returnDate;
    private boolean status;

    public BookLoan(String loanCode, int bookId, int studentId, Date loanDate, Date returnDate, boolean status) {
        this.loanCode = loanCode;
        this.bookId = bookId;
        this.studentId = studentId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
