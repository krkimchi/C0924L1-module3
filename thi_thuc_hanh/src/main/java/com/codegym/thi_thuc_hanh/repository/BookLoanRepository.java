package com.codegym.thi_thuc_hanh.repository;

import com.codegym.thi_thuc_hanh.dto.BookLoanDto;
import com.codegym.thi_thuc_hanh.model.BookLoan;
import com.codegym.thi_thuc_hanh.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookLoanRepository implements IBookLoanRepository {
    private Connection connection;

    public BookLoanRepository() {
        this.connection = BaseRepository.getConnection();
    }

    @Override
    public void borrowBook(BookLoan bookLoan) {
        String sql = "insert into book_loans (loan_code, book_id, student_id, status, loan_date, return_date) values (?, ?, ?, ?, ?, ?)";
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
        String sql = "update book_loans set status = false where loan_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookLoan getBookLoanById(int loanId) {
        String sql = "select * from book_loans where loan_id = ?";
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

    @Override
    public List<BookLoanDto> getAllActiveBookLoans() {
        String sql = "select bl.loan_id, bl.loan_code, bl.book_id, b.book_name, " +
                "bl.student_id, s.full_name as student_name, " +
                "bl.loan_date, bl.return_date, bl.status " +
                "from book_loans bl " +
                "join books b on bl.book_id = b.book_id " +
                "join students s on bl.student_id = s.student_id " +
                "where bl.status = true";

        List<BookLoanDto> loans = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookLoanDto loan = new BookLoanDto(
                        rs.getInt("loan_id"),
                        rs.getString("loan_code"),
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getDate("loan_date"),
                        rs.getDate("return_date"),
                        rs.getBoolean("status")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public List<BookLoanDto> searchActiveLoans(String bookName, String studentName) {
        String sql = "select bl.loan_id, bl.loan_code, bl.book_id, b.book_name, " +
                "bl.student_id, s.full_name as student_name, " +
                "bl.loan_date, bl.return_date, bl.status " +
                "from book_loans bl " +
                "join books b on bl.book_id = b.book_id " +
                "join students s on bl.student_id = s.student_id " +
                "where bl.status = true " +
                "and b.book_name like ? " +
                "and s.full_name like ?";

        List<BookLoanDto> loans = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + bookName + "%");
            stmt.setString(2, "%" + studentName + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BookLoanDto loan = new BookLoanDto(
                            rs.getInt("loan_id"),
                            rs.getString("loan_code"),
                            rs.getInt("book_id"),
                            rs.getString("book_name"),
                            rs.getInt("student_id"),
                            rs.getString("student_name"),
                            rs.getDate("loan_date"),
                            rs.getDate("return_date"),
                            rs.getBoolean("status")
                    );
                    loans.add(loan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
}
