package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.model.BookLoan;
import com.codegym.thi_thuc_hanh.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLoanRepository implements IBookLoanRepository {
    private Connection connection;

    public BookLoanRepository() {
        this.connection = BaseRepository.getConnection();
    }

    @Override
    public void borrowBook(BookLoan bookLoan) {
        String sql = "insert into (loan_code, book_id, student_id, status, loan_date, return_date) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookLoan.getLoanCode());
            stmt.setInt(2, bookLoan.getBookId());
            stmt.setInt(3, bookLoan.getStudentId());
            stmt.setBoolean(4, bookLoan.isStatus());
            stmt.setDate(5, bookLoan.getLoanDate());
            stmt.setDate(6, bookLoan.getReturnDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(int loanId) {
        String sql = "update book_loans set status = 'đã trả' where loan_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookLoan getBookLoanById(int loanId) {
        String sql = "SELECT * FROM book_loans WHERE loan_id = ?";
        BookLoan bookLoan = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                bookLoan = new BookLoan(
                        rs.getString("loan_code"),
                        rs.getInt("book_id"),
                        rs.getInt("student_id"),
                        rs.getDate("loan_date"),
                        rs.getDate("return_date"),
                        rs.getBoolean("status")
                );
                bookLoan.setLoanId(rs.getInt("loan_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookLoan;
    }

}
