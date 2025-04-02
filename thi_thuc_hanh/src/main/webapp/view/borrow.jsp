<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mượn Sách</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
        .btn-custom {
            margin-top: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Mượn Sách</h2>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <form method="post" action="/borrow">
        <div class="form-group">
            <label for="loanCode">Mã Mượn Sách (MS-XXXX)</label>
            <input type="text" name="loanCode" class="form-control" id="loanCode" required pattern="MS-\d{4}"
                   placeholder="Ví dụ: MS-0001">
        </div>

        <div class="form-group">
            <label for="bookName">Tên Sách</label>
            <input type="text" class="form-control" id="bookName" value="${book.bookName}" readonly>
        </div>

        <div class="form-group">
            <label for="studentId">Tên Học Sinh</label>
            <select name="studentId" class="form-control" id="studentId" required>
                <option value="">-- Chọn học sinh --</option>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.fullName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="loanDate">Ngày Mượn</label>
            <input type="date" name="loanDate" class="form-control" id="loanDate"
                   value="<fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" />" required>
        </div>

        <div class="form-group">
            <label for="returnDate">Ngày Trả Sách</label>
            <input type="date" name="returnDate" class="form-control" id="returnDate" required
                   min="<fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" />">
        </div>

        <input type="hidden" name="bookId" value="${book.bookId}">

        <button type="submit" class="btn btn-primary btn-custom">Mượn Sách</button>
        <a href="/books" class="btn btn-secondary btn-custom">Trở về danh sách</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.querySelector('form').addEventListener('submit', function (event) {
        const loanDate = document.getElementById('loanDate').value;
        const returnDate = document.getElementById('returnDate').value;

        if (new Date(returnDate) < new Date(loanDate)) {
            event.preventDefault();
            alert("Ngày trả sách không được trước ngày mượn sách!");
            return false;
        }

        if (!document.getElementById('studentId').value) {
            event.preventDefault();
            alert("Vui lòng chọn học sinh!");
            return false;
        }

        return true;
    });
</script>
</body>
</html>