<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 4/2/2025
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống Kê Sách Đang Mượn</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }

        .search-form {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 5px;
        }

        .table th, .table td {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Thống Kê Sách Đang Mượn</h2>

    <!-- Form tìm kiếm -->
    <div class="search-form">
        <form method="get" action="/book-loans" class="form-inline">
            <div class="form-group mr-3">
                <label for="bookName" class="mr-2">Tên sách:</label>
                <input type="text" name="bookName" id="bookName" class="form-control"
                       value="${param.bookName}" placeholder="Nhập tên sách">
            </div>
            <div class="form-group mr-3">
                <label for="studentName" class="mr-2">Tên học sinh:</label>
                <input type="text" name="studentName" id="studentName" class="form-control"
                       value="${param.studentName}" placeholder="Nhập tên học sinh">
            </div>
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/book-loans" class="btn btn-secondary ml-2">Xóa tìm kiếm</a>
        </form>
    </div>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Mã Mượn Sách</th>
            <th>Tên Sách</th>
            <th>Tác Giả</th>
            <th>Tên Học Sinh</th>
            <th>Ngày Mượn</th>
            <th>Ngày Trả</th>
            <th>Thao Tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="loan" items="${bookLoans}">
            <tr>
                <td>${loan.loanCode}</td>
                <td>${loan.bookName}</td>
                <td>${loan.author}</td>
                <td>${loan.studentName}</td>
                <td>${loan.loanDate}</td>
                <td>${loan.returnDate}</td>
                <td>
                    <button onclick="confirmReturn(${loan.loanId})"
                            class="btn btn-success">Trả Sách
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function confirmReturn(loanId) {
        if (confirm('Bạn có chắc chắn muốn trả sách này?')) {
            window.location.href = '/return?loanId=' + loanId;
        }
    }
</script>
</body>
</html>