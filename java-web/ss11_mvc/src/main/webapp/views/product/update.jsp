<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/10/2025
  Time: 10:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
</head>
<body>
<h1>Chỉnh sửa sản phẩm</h1>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form method="post" action="/products?action=edit">
    <input type="hidden" name="id" value="${product.id}">

    Tên sản phẩm: <input type="text" name="name" value="${product.name}" required><br>
    Giá: <input type="text" name="price" value="${product.price}" required><br>
    Mô tả: <input type="text" name="description" value="${product.description}"><br>
    Nhà sản xuất: <input type="text" name="manufacturer" value="${product.manufacturer}"><br>

    <input type="submit" value="Cập nhật">
</form>

<br>
<a href="/products">Quay lại danh sách sản phẩm</a>
</body>
</html>

