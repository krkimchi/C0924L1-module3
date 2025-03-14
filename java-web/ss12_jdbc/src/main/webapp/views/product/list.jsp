<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 3/10/2025
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
  <title>Danh sách sản phẩm</title>
</head>
<body>
<h1>Danh sách sản phẩm</h1>

<form method="get" action="/products">
  <input type="hidden" name="action" value="search">
  <input type="text" name="name" placeholder="Nhập tên sản phẩm">
  <input type="submit" value="Tìm kiếm">
</form>

<a href="/products?action=create">Thêm sản phẩm mới</a>

<c:if test="${not empty message}">
  <p style="color: green">${message}</p>
</c:if>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Tên sản phẩm</th>
    <th>Giá</th>
    <th>Mô tả</th>
    <th>Nhà sản xuất</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td>${product.description}</td>
      <td>${product.manufacturer}</td>
      <td>
        <a href="products?action=view&id=${product.id}">Xem</a> |
        <a href="products?action=edit&id=${product.id}">Sửa</a> |
        <a href="products?action=delete&id=${product.id}"
           onclick="return confirm('Bạn có chắc chắn muốn xoá sản phẩm này?');">Xoá</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>