package com.codegym.thi_thuc_hanh.dto;

import java.sql.Date;

public class BookLoanDto {
    private int loanId;
    private String loanCode;
    private int bookId;
    private String bookName;
    private int studentId;
    private String studentName;
    private Date loanDate;
    private Date returnDate;
    private boolean status;

    private String author;

    public BookLoanDto(int loanId, String loanCode, int bookId, String bookName, int studentId, String studentName, Date loanDate, Date returnDate, boolean status) {
        this.loanId = loanId;
        this.loanCode = loanCode;
        this.bookId = bookId;
        this.bookName = bookName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BookLoanDto() {
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}